package client.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login extends Application
{
	BorderPane borderpane = new BorderPane();
	
	Button connect = new Button();
	Button cancel = new Button();
	
	TextField ipAddressField = new TextField();
	TextField portField = new TextField();
	TextField usernameField = new TextField();
	
	private final String LOGIN = "Login";
	
	
	private void initElements()
	{
	 GridPane upGrid = new GridPane();
	 upGrid.setVgap(6);
	 upGrid.setHgap(6);
	 upGrid.add(new Text("Nom d'utilisateur"), 0, 0);
	 upGrid.add(usernameField, 1, 0);
	 upGrid.add(new Text("Adresse IP"), 0, 1);
	 upGrid.add(ipAddressField, 1, 1);
	 upGrid.add(new Text("Port"), 0, 2);
	 upGrid.add(portField, 1, 2);
	 this.borderpane.setTop(upGrid);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = new Scene(borderpane);
		stage.setTitle(LOGIN);
		stage.setScene(scene);
		initElements();
		stage.show();
		
	}

}
