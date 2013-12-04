package client.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login extends Application
{
	BorderPane root;
	
	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		root = new BorderPane();
		Scene scene = new Scene(root);
		
		initElements();
		
		primaryStage.setWidth(1280);
		primaryStage.setHeight(720);
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	private void initElements()
	{
		
	}

}
