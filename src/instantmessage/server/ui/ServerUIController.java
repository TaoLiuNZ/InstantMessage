package instantmessage.server.ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import instantmessage.server.helper.FxUIHelper;
import instantmessage.server.manager.ClientConnectionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ServerUIController implements Initializable{
	@FXML private TextFlow textFlow;
	ServerSocket server;
	public void addTextToTextFlow(String str){
		FxUIHelper.addElementToParent(textFlow, new Text(str));
	}

	public ServerUIController(){
		// Start server socket
				try {
		            ServerSocket server = new ServerSocket(5000);
		           // addTextToTextFlow("Server setup: waiting for client connections");
		            ClientConnectionManager clientManager=ClientConnectionManager.getInstance(this) ;
		                   
		            Thread listenToClientThread=new Thread(){
		            	@Override
		            	public void run()
		            	{

		            		while(true)
		                    {
		                        
								try {
				            		// keep processing and accepting client connections forever
				                    Socket socket = server.accept();
				                    
									// Start client connection handler thread to deal with messages from different clients respectively
									clientManager.startNewClientHandler(socket);
			                        
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                        
		                        
		                        
		                    }
		            	}
		            	
		            };
		            listenToClientThread.start();
		            
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		        }
				
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
