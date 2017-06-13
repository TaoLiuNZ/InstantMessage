package instantmessage.server.manager;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;


public class ClientConnectionManager {
	private static HashMap<String,ConnectionFromClientHandler> clientsList;

	private static ClientConnectionManager instance = null;
	   protected ClientConnectionManager() {}
	   
	   public static ClientConnectionManager getInstance() {
	      if(instance == null) {
	         instance = new ClientConnectionManager();
	         clientsList=new HashMap<String,ConnectionFromClientHandler>();
	      }
	      return instance;
	   }
	   
	   public void createNewClientHandler(Socket socket){
		   // Get uid
		   String uid=
		   // Create and start the thread
		   ConnectionFromClientHandler clientConnection = new ConnectionFromClientHandler(socket);
           clientConnection.start();
           
           //
           // Add this handler to clients list
           clientsList.put(uid, clientConnection);
	   }
	   
	  public HashMap<String,ConnectionFromClientHandler> getClientsList(){
		  return ClientConnectionManager.clientsList;
	  }
	   
	  
}
