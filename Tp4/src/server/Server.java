package server;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Vector;


public class Server
{
    private static LinkedList<DataOutputStream> tabClients = new LinkedList<DataOutputStream>();
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
        tabClients.addLast(dOut);
        return tabClients.size() - 1;
    }

    static synchronized public void removeClient(int position)
    {
        if (tabClients.size() > position)
        {
            tabClients.remove(position);
            nbOfClient--;
        }
    }

    static synchronized public void sendAll(String message)
    {
        for (DataOutputStream dOut : tabClients)
        {
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