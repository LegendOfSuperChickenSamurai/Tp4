package client.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChatRoom extends Application
{
	
	private final String CHATROOM = "chat room";
	BorderPane borderpane = new BorderPane();
	Login login;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = new Scene(borderpane);
		stage.setTitle(CHATROOM);
		stage.setScene(scene);
		initElements();
		stage.show();
	}
	
	private void initElements()
	{
		showLogin(); 
		GridPane upGrid = new GridPane();
		 upGrid.setVgap(6);
		 upGrid.setHgap(6);
		 upGrid.add(new Text("Nom d'utilisateur"), 0, 0);
		 borderpane.setTop(upGrid);
	}
	
	private void showLogin()
	{
		 login = new Login(); 
		 login.showDialog();
	}

}
