package client.Interface;

import client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login
{

    public Login()
    {
        BorderPane borderpane = new BorderPane();

        final TextField ipAddressField = new TextField();
        final TextField portField = new TextField();
        final TextField usernameField = new TextField();

        Button btnConnect = new Button("Connect");
        Button btnReset = new Button("Reset");

        GridPane upGrid = new GridPane();
        upGrid.setVgap(6);
        upGrid.setHgap(6);
        upGrid.add(new Text("Nom d'utilisateur"), 0, 0);
        upGrid.add(usernameField, 1, 0);
        upGrid.add(new Text("Adresse IP"), 0, 1);
        upGrid.add(ipAddressField, 1, 1);
        upGrid.add(new Text("Port"), 0, 2);
        upGrid.add(portField, 1, 2);
        borderpane.setTop(upGrid);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(6);
        buttonGrid.add(btnConnect, 0, 0);
        buttonGrid.add(btnReset, 1, 0);
        buttonGrid.setAlignment(Pos.CENTER);
        BorderPane.setMargin(buttonGrid, new Insets(15));
        borderpane.setBottom(buttonGrid);


        btnReset.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                usernameField.setText("");
                ipAddressField.setText("");
                portField.setText("");
            }
        });

        btnConnect.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(!usernameField.getText().isEmpty() && !ipAddressField.getText().isEmpty() && !portField.getText().isEmpty())
                {
                	MainApp.client = new Client(usernameField.getText(), ipAddressField.getText(), Integer.parseInt(portField.getText()));
                    ChatRoom chatRoom = new ChatRoom();
                }
            }
        });


        Scene scene = new Scene(VBoxBuilder.create().children(borderpane).alignment(Pos.CENTER).padding(new Insets(10)).build());
        scene.setFill(Color.web("0xC0C0C0"));

        MainApp.stage.setTitle("Login");
        MainApp.stage.setScene(scene);
        MainApp.stage.show();
    }
}