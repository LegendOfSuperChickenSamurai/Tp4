package client.Interface;

import client.Client;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChatRoom extends Application
{

	private final String CHATROOM = "chat room";
	private final String IP = "Adresse Ip: ";
	private final String PORT = "Port: ";
	private final String USERNAME = "Nom d'utilisateur: ";
	private final String TEXT = "Message: ";
	private final String CONNECT = "Connecter";
	private final String SEND = "Envoyer";
	
	BorderPane borderpane;
	Login login;
	TextArea output;
	TextField input;
	Client client;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		newElements();
		Scene scene = new Scene(this.borderpane);
		scene.setFill(Color.web("0xC0C0C0"));
		stage.setTitle(this.CHATROOM);
		stage.setScene(scene);
		initElements();
		stage.show();
	}

	private void initElements()
	{
		showLogin(); 

		Button connect = new Button(this.CONNECT);
		GridPane rightGrid = new GridPane();
		rightGrid.setVgap(6);
		rightGrid.setHgap(6);
		rightGrid.add(new Text(this.USERNAME + client.getUsername()), 0, 0);
		rightGrid.add(new Text(this.IP + client.getIp()), 0, 1);
		rightGrid.add(new Text(this.PORT + client.getPort()), 0, 2);
		rightGrid.add(connect, 0, 3);
		this.borderpane.setRight(rightGrid);
		BorderPane.setMargin(rightGrid, new Insets(3));
		
		GridPane leftGrid = new GridPane();
		leftGrid.setVgap(10);
		leftGrid.setHgap(6);
		leftGrid.add(this.output, 0 ,0);
		this.borderpane.setLeft(leftGrid);
		BorderPane.setMargin(leftGrid, new Insets(3));
		
		Button addText = new Button(this.SEND);
		GridPane bottomGrid = new GridPane();
		bottomGrid.setVgap(10);
		bottomGrid.setHgap(6);
		bottomGrid.add(new Text(this.TEXT), 0 ,0);
		bottomGrid.add(this.input, 1, 0);
		bottomGrid.add(addText, 2, 0);
		this.borderpane.setBottom(bottomGrid);
		BorderPane.setMargin(bottomGrid, new Insets(3));
	}

	private void showLogin()
	{
		this.login.showDialog();
	}
	
	private void newElements()
	{
		this.borderpane = new BorderPane();
		this.output = new TextArea();
		this.input = new TextField();
		this.client = new Client();
		this.login = new Login();
	}
	
	private void addTextToOutput()
	{
		
	}
	
	private void initInputAndOutput()
	{
		
	}

}
