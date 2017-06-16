package instantmessage.server.manager.message;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import instantmessage.server.model.Message;
import instantmessage.server.model.SetupDeleteGroupMemberMessage;
import instantmessage.server.ui.ServerUIController;

/**
 * Execution for SetupDeleteGroupMemberMessage
 * 
 * @author Tao Liu
 *
 */
public class SetupDeleteGroupMemberMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		String groupMemberUid = ((SetupDeleteGroupMemberMessage) message).getGroupMemberUid();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
	}

	@Override
	public void handleMessageFromClient(Socket socket, ServerUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String connectionId = MessageManager.receiveText(socket);

		HashMap<String, LinkedHashMap<String, ConnectionFromClientHandler>> clients = ClientConnectionManager
				.getInstance(controller).getClientsList();
		
		// Remove from clients list
		clients.get(uid).remove(connectionId);

		// Display
		controller.addTextToTextFlow("Client disconntected: UID (" + uid + ")");

		// Notify other clients that this client is disconnected
		Message messageForOtherClients = new SetupDeleteGroupMemberMessage(uid);
		for (HashMap<String, ConnectionFromClientHandler> levelOneValue : clients.values()) {
			for (Entry<String, ConnectionFromClientHandler> entryLevelTwo : levelOneValue.entrySet()) {
				ConnectionFromClientHandler handler = entryLevelTwo.getValue();
				if (clients.get(uid).size()==0) {
					this.sendMessageToClient(handler.getSocket(), messageForOtherClients);
				}
			}
		}
	}

}
