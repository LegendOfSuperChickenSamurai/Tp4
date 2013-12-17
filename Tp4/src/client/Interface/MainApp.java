package client.Interface;

import client.Client;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application
{
    static Client client;
    static Stage stage;

    public static void main (String[] args)
    {
        Application.launch(MainApp.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        Login login = new Login();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent windowEvent)
            {
                if (client != null)
                {
                    client.logOut();
                }

            }
        });

    }
}
