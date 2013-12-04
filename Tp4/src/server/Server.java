package server;

import java.io.*;
import java.net.*;


public class Server
{
    private static int port = 4444, maxConnections = 2;

    // Listen for incoming connections and handle them
    public static void main(String[] args)
    {
        int i = 0;

        try
        {
            ServerSocket listener = new ServerSocket(port);
            Socket server;

            while ((i++ < maxConnections) || (maxConnections == 0))
            {
                server = listener.accept();
                doComms conn_c = new doComms(server);
                Thread t = new Thread(conn_c);
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

}

class doComms implements Runnable
{
    private Socket server;

    public doComms(Socket server)
    {
        this.server = server;
    }

    public void run()
    {
        DataInputStream dIn = null;
        try
        {
            dIn = new DataInputStream(server.getInputStream());

            boolean done = false;
            while (!done)
            {
                byte messageType = dIn.readByte();

                switch (messageType)
                {
                    case 1: // Type A
                        System.out.println("I got : " + dIn.readUTF());
                        break;
                    default:
                        done = true;
                }
            }
            dIn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

