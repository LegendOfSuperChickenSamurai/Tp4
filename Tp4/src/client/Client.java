package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import client.Interface.ChatRoom;
import javafx.application.Application;

public class Client
{
    public static void main (String[] args)
    {
    	Application.launch(ChatRoom.class, args);
    }
    public void connectToServer()
    {
    	try
        {
            Socket socket = new Socket("localhost", 4444);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            String input = "";
            Boolean done = false;

            do
            {
                System.out.println("Enter something here : ");
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                input = bufferRead.readLine();

                if (input.equals(new String("Q")))
                {
                	done = true;
                }
                else
                {
                	dOut.writeByte(1);
                    dOut.writeUTF(input);
                    dOut.flush(); // Send off the data
                }
                
            }while (!done);

            // Send the exit message
            dOut.writeByte(-1);
            dOut.flush();

            dOut.close();
            socket.close();
        }
        catch (IOException ioe)
        {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}
