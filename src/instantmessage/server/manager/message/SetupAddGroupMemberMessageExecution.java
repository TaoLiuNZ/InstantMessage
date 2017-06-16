package instantmessage.server.manager.message;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import instantmessage.server.model.Message;
import instantmessage.server.model.SetupAddGroupMemberMessage;
import instantmessage.server.ui.ServerUIController;

/**
 * Execution for SetupAddGroupMemberMessage
 * 
 * @author Tao Liu
 *
 */
public class SetupAddGroupMemberMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		SetupAddGroupMemberMessage setupAddGroupMemberMessage = (SetupAddGroupMemberMessage) message;
		String groupMemberUid = setupAddGroupMemberMessage.getGroupMemberUid();
		Boolean isNewMember = setupAddGroupMemberMessage.getIsNewMember();
		String ipAddress = setupAddGroupMemberMessage.getIpAddress();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
		MessageManager.sendBoolean(socket, isNewMember);
		MessageManager.sendText(socket, ipAddress);
	}

	@Override
	public void handleMessageFromClient(Socket socket, ServerUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String connectionId = MessageManager.receiveText(socket);
		String ipAddress = socket.getRemoteSocketAddress().toString();

		// Create and start the client handler thread
		ConnectionFromClientHandler client = new ConnectionFromClientHandler(socket, controller);
		client.start();

		// Add this handler to clients list
		HashMap<String, LinkedHashMap<String, ConnectionFromClientHandler>> clientsList = ClientConnectionManager
				.getInstance(controller).getClientsList();
		if (!clientsList.containsKey(uid)) {
			clientsList.put(uid, new LinkedHashMap<String, ConnectionFromClientHandler>());
		}
		clientsList.get(uid).put(connectionId, client);

		// Display
		controller.addTextToTextFlow("Client conntected: UID (" + uid + ")");

		// Notify other clients
		Message messageForOtherClients = new SetupAddGroupMemberMessage(uid, ipAddress, true);

		for (Entry<String, LinkedHashMap<String, ConnectionFromClientHandler>> entryLevelOne : clientsList.entrySet()) {
			String uidKey = entryLevelOne.getKey();
			LinkedHashMap<String, ConnectionFromClientHandler> levelOneValue = entryLevelOne.getValue();

			if (!uidKey.equals(uid) && (!levelOneValue.isEmpty())) {

				// Notify this client of current connected user ids and ip
				// addresses
				// excluding himself
				String otherClientIpAddress = levelOneValue.entrySet().iterator().next().getValue().getIpAddress();
				Message messageForThisClient = new SetupAddGroupMemberMessage(uidKey, otherClientIpAddress, false);
				this.sendMessageToClient(socket, messageForThisClient);

				// Notify other clients that a new client is connected
				for (Entry<String, ConnectionFromClientHandler> entryLevelTwo : levelOneValue.entrySet()) {
					ConnectionFromClientHandler levelTwoValue = entryLevelTwo.getValue();

					if (clientsList.get(uid).size() == 1)
						this.sendMessageToClient(levelTwoValue.getSocket(), messageForOtherClients);
				}
			}
		}
	}
}
