package instantmessage.server.manager;


import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.MessageManager;
import instantmessage.server.model.Message;
import instantmessage.server.model.SetupAddGroupMemberMessage;
import instantmessage.server.ui.ServerUIController;

public class SetupAddGroupMemberMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToClient(Socket socket,Message message) {
		// Get info
		String groupMemberUid=((SetupAddGroupMemberMessage)message).getGroupMemberUid();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
		MessageManager.sendText(socket, socket.getRemoteSocketAddress().toString());
	}

	@Override
	public void handleMessageFromClient(Socket socket,ServerUIController controller) {
		// Get info
		String uid=MessageManager.receiveText(socket);
		handleMessageFromClient(socket,uid,controller);
	}
	
	public void handleMessageFromClient(Socket socket,String uid,ServerUIController controller) {

				HashMap<String, ConnectionFromClientHandler> clients=ClientConnectionManager.getInstance(controller).getClientsList();
				for (Entry<String, ConnectionFromClientHandler> entry : clients.entrySet()) {
				    String key = entry.getKey();
				    ConnectionFromClientHandler value = entry.getValue();
				    
				    // Notify other clients that a new client is connected
					Message messageForOtherClients=new SetupAddGroupMemberMessage(uid);
					this.sendMessageToClient(value.getSocket(), messageForOtherClients);	
					
					// Send current client list to this client
					if(!key.equals(uid)){
						Message messageForThisClient=new SetupAddGroupMemberMessage(key);
					    this.sendMessageToClient(socket, messageForThisClient);	
					}									    	
				}
	}

}
