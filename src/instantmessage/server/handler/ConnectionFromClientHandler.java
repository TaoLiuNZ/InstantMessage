package instantmessage.server.handler;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.manager.MessageManager;
import instantmessage.server.ui.ServerUIController;

public class ConnectionFromClientHandler extends Thread{
private Socket socket;
private ServerUIController controller;
	
public ConnectionFromClientHandler(Socket socket,ServerUIController controller)
{
    this.socket = socket;
    this.controller=controller;
}

public Socket getSocket(){
	return this.socket;
}

@Override
public void run()
{

    while(true)
    {
    	// Check type
    	long messageType=MessageManager.receiveLong(socket);   	
  
		// Call MessageExcution to handle message from client
    	MessageManager.getMessageExcutionByType(messageType).handleMessageFromClient(socket,controller);
    }

}
}
