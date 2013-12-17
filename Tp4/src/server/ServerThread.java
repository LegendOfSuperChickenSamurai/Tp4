package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable
{
    private Socket server;

    public ServerThread(Socket server)
    {
        this.server = server;
    }

    public void run()
    {
        try
        {
            DataInputStream dIn = new DataInputStream(server.getInputStream());
            DataOutputStream dOut = new DataOutputStream(server.getOutputStream());
            int position = Server.addClient(dOut);
            boolean done = false;

            while (!done)
            {
                switch (dIn.readByte())
                {
                    case 1:
                        Server.sendAll(dIn.readUTF());
                        break;
                    default:
                        Server.removeClient(position);
                        done = true;
                }
            }
            dIn.close();
            Server.removeClient(position);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
