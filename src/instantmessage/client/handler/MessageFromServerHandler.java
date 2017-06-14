package instantmessage.client.handler;

import java.net.Socket;

import instantmessage.client.manager.MessageManager;
import instantmessage.client.ui.IUIController;

public class MessageFromServerHandler extends Thread{
	private Socket socket;
	  	IUIController controller;
	
public MessageFromServerHandler(Socket socket,IUIController controller){
	
	this.socket=socket;
	this.controller=controller;
}
	
        public void run()
        {
            while(true)
            {
               // Check type
            	long messageType=MessageManager.receiveLong(socket);
            	
          try{
				// Call MessageExcution to handle message from server
            	MessageManager.getMessageExcutionByType(messageType).handleMessageFromServer(socket,controller);
          }catch(Exception e){
        	  e.printStackTrace();
          }
            }
        }

}
