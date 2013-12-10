package client.Interface;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Login extends Application
{
	BorderPane borderpane;
	private final String LOGIN = "Login";
	
	
	private void initElements()
	{
	
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle(LOGIN);

		stage.show();
		
	}

}
