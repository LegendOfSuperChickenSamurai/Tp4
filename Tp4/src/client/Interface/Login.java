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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Login extends Stage
{
	private final String LOGIN = "Login";
	BorderPane borderpane = new BorderPane();
	
	Button connect = new Button("Connect");
	Button cancel = new Button("Cancel");
	Client client;
	TextField ipAddressField = new TextField();
	TextField portField = new TextField();
	TextField usernameField = new TextField();
	
	public Login()
	{
		//TODO
		super();
		this.initScene();
		this.initElements();
		this.client = new Client();
	}
	
	private void initElements()
	{
	 GridPane upGrid = new GridPane();
	 upGrid.setVgap(6);
	 upGrid.setHgap(6);
	 upGrid.add(new Text("Nom d'utilisateur"), 0, 0);
	 upGrid.add(this.usernameField, 1, 0);
	 upGrid.add(new Text("Adresse IP"), 0, 1);
	 upGrid.add(this.ipAddressField, 1, 1);
	 upGrid.add(new Text("Port"), 0, 2);
	 upGrid.add(this.portField, 1, 2);
	 this.borderpane.setTop(upGrid);
	
	 GridPane buttonGrid = new GridPane();
	 buttonGrid.setHgap(6);
	 buttonGrid.add(this.connect, 0, 0);
	 buttonGrid.add(this.cancel, 1, 0);
	 buttonGrid.setAlignment(Pos.CENTER);
	 BorderPane.setMargin(buttonGrid, new Insets(15));
	 this.borderpane.setBottom(buttonGrid);
	 
	 this.cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {			
				usernameField.setText("");
				ipAddressField.setText("");
				portField.setText("");
				
				Login.this.close();
			}
		});
	 
	 this.connect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {			
				client.setIp(ipAddressField.getText());
				Integer integer = new Integer(portField.getText());
				client.setPort(integer);
				client.setUsername(usernameField.getText());
				
				Login.this.close();
			}
		});
	}
	
	private void initScene()
	{
		Scene myDialogScene = new Scene(VBoxBuilder.create().children(borderpane).alignment(Pos.CENTER).padding(new Insets(10)).build());
		
		this.setHeight(200);
		this.setWidth(300);
		this.setX(35);
		this.setY(32);

		myDialogScene.setFill(Color.web("0xC0C0C0"));
		this.initModality(Modality.APPLICATION_MODAL);
		this.initStyle(StageStyle.UTILITY);
		this.setTitle(LOGIN);
		this.setScene(myDialogScene);
	}
	
	public void showDialog()
	{
		this.showAndWait();
	}
}
