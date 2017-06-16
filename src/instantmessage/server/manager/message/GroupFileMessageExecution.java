package instantmessage.server.manager.message;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import instantmessage.server.model.GroupFileMessage;
import instantmessage.server.model.Message;
import instantmessage.server.ui.ServerUIController;

public class GroupFileMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		String uid = ((GroupFileMessage) message).getSenderUid();
		String fileName = ((GroupFileMessage) message).getFileName();
		byte[] fileByteData = ((GroupFileMessage) message).getFileByteData();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendFile(socket, fileName, fileByteData);
	}

	@Override
	public void handleMessageFromClient(Socket socket, ServerUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String fileName = MessageManager.receiveText(socket);
		long fileSize = MessageManager.receiveLong(socket);
		byte[] fileByteData = MessageManager.receiveByteData(socket, fileName, fileSize);

		// Message
		GroupFileMessage message = new GroupFileMessage(uid, fileName, fileByteData);

		// Send back to all clients
		HashMap<String, HashMap<String, ConnectionFromClientHandler>> clients = ClientConnectionManager
				.getInstance(controller).getClientsList();
		for (HashMap<String, ConnectionFromClientHandler> singleClientMultipleConnections : clients.values()) {
			for (ConnectionFromClientHandler client : singleClientMultipleConnections.values()) {
				this.sendMessageToClient(client.getSocket(), message);
			}
		}
		
		// Display
		controller.addTextToTextFlow(message.toString());

	}

}
