package instantmessage.server.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.model.GroupTextMessage;
import instantmessage.server.model.Message;
import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.ui.ServerUIController;

public class GroupTextMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToClient(Socket socket,Message message) {
		// Get info
		String uid=((GroupTextMessage)message).getSenderUid();
		String msg=((GroupTextMessage)message).getMsg();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendText(socket, msg);
	}
	

	@Override
	public void handleMessageFromClient(Socket socket,ServerUIController controller) {
		// Get info
		String uid=MessageManager.receiveText(socket);
		String msg=MessageManager.receiveText(socket);
		
		// Message
		Message message=new GroupTextMessage(uid,msg);
		
		// Send back to all clients
		HashMap<String, ConnectionFromClientHandler> clients=ClientConnectionManager.getInstance(controller).getClientsList();
		for(ConnectionFromClientHandler c:clients.values()){
			this.sendMessageToClient(c.getSocket(), message);
			
			// Display
			controller.addTextToTextFlow(message.toString());
		}
	}

}
