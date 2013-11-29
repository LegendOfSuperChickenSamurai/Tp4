package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client
{
    public static void main (String[] args)
    {
        try
        {
            Socket socket = new Socket("localhost", 4444);
            DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

            String input = "";

            do
            {
                System.out.println("Enter something here : ");
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                input = bufferRead.readLine();

                dOut.writeByte(1);
                dOut.writeUTF(input);
                dOut.flush(); // Send off the data

            }while (input != "Q");

            // Send the exit message
            dOut.writeByte(-1);
            dOut.flush();

            dOut.close();

        }
        catch (IOException ioe)
        {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}
