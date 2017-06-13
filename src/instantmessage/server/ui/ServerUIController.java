package instantmessage.server.ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ServerUIController {
	@FXML private TextFlow textFlow;

	public ServerUIController(){
		
		// Start server socket
		try {
            ServerSocket server = new ServerSocket(5000);
            addTextToTextFlow("Server setup: waiting for client connections");
                   
            
            while(true)
            {
                // keep processing and accepting client connections forever
                Socket socket = server.accept();
                addTextToTextFlow("Client Connected: ");
                
                // Start client connection handler thread to deal with messages from diffrent clients respectively
                ClientConnectionManager.getInstance().createNewClientHandler(socket);
                
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}
	
	private void addTextToTextFlow(String str){
		textFlow.getChildren().add(new Text(str));
	}
}
