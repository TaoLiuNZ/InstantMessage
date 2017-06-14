package instantmessage.server.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.model.GroupFileMessage;
import instantmessage.server.model.Message;
import instantmessage.server.ui.ServerUIController;

public class GroupFileMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToClient(Socket socket,Message message) {
		// Get info
		String uid=((GroupFileMessage)message).getSenderUid();
		String fileName=((GroupFileMessage)message).getFileName();
		byte[] fileByteData=((GroupFileMessage)message).getFileByteData();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendFile(socket, fileName,fileByteData);
	}

	@Override
	public void handleMessageFromClient(Socket socket,ServerUIController controller) {
		// Get info
		String uid=MessageManager.receiveText(socket);
		String fileName=MessageManager.receiveText(socket);
		long fileSize=MessageManager.receiveLong(socket);
		byte[] fileByteData=MessageManager.receiveByteData(socket, fileName, fileSize);

		// Message
		GroupFileMessage message=new GroupFileMessage(uid,fileName,fileByteData);
		
		// Send back to all clients
		HashMap<String, ConnectionFromClientHandler> clients=ClientConnectionManager.getInstance(controller).getClientsList();
		for(ConnectionFromClientHandler c:clients.values()){
			this.sendMessageToClient(c.getSocket(), message);
			
			// Display
			controller.addTextToTextFlow(message.toString());
		}
	         			
	}

}