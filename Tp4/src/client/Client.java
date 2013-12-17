package client;

import java.io.*;
import java.net.Socket;

public class Client
{
    private DataOutputStream dOut;
    private DataInputStream dIn;
    private Socket socket;

    private String ip;
    private int port;
    private String username;

    public Client(String username, String ip, int port)
    {
        this.username = username;
        this.ip = ip;
        this.port = port;

        try
        {
            this.socket = new Socket(this.ip, this.port);
            this.dOut = new DataOutputStream(this.socket.getOutputStream());
            this.dIn = new DataInputStream(this.socket.getInputStream());
            sendText(this.username);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sendText(String textToSend)
    {
        try
        {
            dOut.writeByte(1);
            dOut.writeUTF(textToSend);
            dOut.flush(); // Send off the data
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String receiveText()
    {
        try
        {
            if(dIn.available() > 0)
            {
                return dIn.readUTF();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public void logOut()
    {
        try
        {
            dOut.writeByte(0);
            dOut.flush(); // Send off the data
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getIp()
    {
        return ip;
    }

    public int getPort()
    {
        return port;
    }

    public String getUsername()
    {
        return username;
    }
}