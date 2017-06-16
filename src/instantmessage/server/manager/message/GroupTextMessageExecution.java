package instantmessage.server.manager.message;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import instantmessage.server.model.GroupTextMessage;
import instantmessage.server.model.Message;
import instantmessage.server.ui.ServerUIController;

/**
 * Execution for GroupTextMessage
 * 
 * @author Tao Liu
 *
 */
public class GroupTextMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		String uid = ((GroupTextMessage) message).getSenderUid();
		String msg = ((GroupTextMessage) message).getMsg();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendText(socket, msg);
	}

	@Override
	public void handleMessageFromClient(Socket socket, ServerUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String msg = MessageManager.receiveText(socket);

		// Message
		Message message = new GroupTextMessage(uid, msg);

		// Send back to all clients
		HashMap<String, LinkedHashMap<String, ConnectionFromClientHandler>> clients = ClientConnectionManager
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
