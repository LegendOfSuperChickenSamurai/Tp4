package server;

import java.io.*;
import java.net.*;
import java.util.Vector;


public class Server
{
    private static Vector<DataOutputStream> tabClients = new Vector<DataOutputStream>();
    private static int port = 4444;
    private static int maxConnections = 10;
    private static int nbOfClient = 0;


    public static void main(String[] args)
    {
        try
        {
            ServerSocket listener = new ServerSocket(port);
            Socket server;

            while (nbOfClient <= maxConnections)
            {
                server = listener.accept();
                ServerThread temp = new ServerThread(server);
                Thread t = new Thread(temp);
                t.start();
            }
            listener.close();
        }
        catch (IOException ioe)
        {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }

    static synchronized public int addClient(DataOutputStream dOut)
    {
        nbOfClient++;
        tabClients.addElement(dOut);
        return tabClients.size() - 1;
    }

    static synchronized public void removeClient(int position)
    {
        nbOfClient--;
        if (tabClients.elementAt(position) != null)
        {
            tabClients.removeElementAt(position);
        }
    }

    static synchronized public void sendAll(String message)
    {
        DataOutputStream dOut;
        for (int i = 0; i < tabClients.size(); i++)
        {
            dOut = tabClients.elementAt(i);
            if (dOut != null)
            {
                try
                {
                    dOut.writeUTF(message);
                    dOut.flush();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}