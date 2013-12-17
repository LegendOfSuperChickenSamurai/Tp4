package client.Interface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ChatRoom
{
    public ChatRoom()
    {
        final BorderPane borderpane = new BorderPane();
        final TextArea output = new TextArea();
        final TextField input = new TextField();

        Button disconnect = new Button("Déconnecter");
        GridPane rightGrid = new GridPane();
        rightGrid.setVgap(6);
        rightGrid.setHgap(6);
        rightGrid.add(new Text("Nom d'utilisateur: " + MainApp.client.getUsername()), 0, 0);
        rightGrid.add(new Text("Adresse Ip: " + MainApp.client.getIp()), 0, 1);
        rightGrid.add(new Text("Port: " + MainApp.client.getPort()), 0, 2);
        rightGrid.add(disconnect, 0, 3);
        borderpane.setRight(rightGrid);
        BorderPane.setMargin(rightGrid, new Insets(3));

        disconnect.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                MainApp.client.logOut();
                Login login = new Login();
            }
        });

        GridPane leftGrid = new GridPane();
        leftGrid.setVgap(10);
        leftGrid.setHgap(6);
        leftGrid.add(output, 0 ,0);
        borderpane.setLeft(leftGrid);
        BorderPane.setMargin(leftGrid, new Insets(3));

        Button addText = new Button("Envoyer");
        GridPane bottomGrid = new GridPane();
        bottomGrid.setVgap(10);
        bottomGrid.setHgap(6);
        bottomGrid.add(new Text("Message: "), 0 ,0);
        bottomGrid.add(input, 1, 0);
        bottomGrid.add(addText, 2, 0);
        borderpane.setBottom(bottomGrid);
        BorderPane.setMargin(bottomGrid, new Insets(3));

        output.setEditable(false);
        output.setStyle("-fx-border-style: none");
        output.setFocusTraversable(false);

        input.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode().equals(KeyCode.ENTER))
                {
                    MainApp.client.sendText(MainApp.client.getUsername() + ": " + input.getText());
                    input.setText("");
                }
            }
        });
        input.requestFocus();

        addText.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                MainApp.client.sendText(MainApp.client.getUsername() + ": " + input.getText());
                input.setText("");
            }
        });


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String temp = MainApp.client.receiveText();


                if (!temp.equals(""))
                {
                    output.appendText(temp + "\n");
                }
            }
        }));
        timeline.playFromStart();


        Scene scene = new Scene(borderpane);
        scene.setFill(Color.web("0xC0C0C0"));

        MainApp.stage.setTitle("Chat Room");
        MainApp.stage.setScene(scene);
        MainApp.stage.show();
    }
}