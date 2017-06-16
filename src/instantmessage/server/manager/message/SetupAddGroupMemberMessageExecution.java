package instantmessage.server.manager.message;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.ClientConnectionManager;
import instantmessage.server.model.Message;
import instantmessage.server.model.SetupAddGroupMemberMessage;
import instantmessage.server.ui.ServerUIController;

public class SetupAddGroupMemberMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		SetupAddGroupMemberMessage setupAddGroupMemberMessage = (SetupAddGroupMemberMessage) message;
		String groupMemberUid = setupAddGroupMemberMessage.getGroupMemberUid();
		Boolean isNewMember = setupAddGroupMemberMessage.getIsNewMember();
		String ipAddress = socket.getRemoteSocketAddress().toString();

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

		// Create and start the client handler thread
		ConnectionFromClientHandler client = new ConnectionFromClientHandler(socket, controller);
		client.start();

		// Add this handler to clients list
		HashMap<String, HashMap<String, ConnectionFromClientHandler>> clientsList = ClientConnectionManager
				.getInstance(controller).getClientsList();
		if (!clientsList.containsKey(uid)) {
			clientsList.put(uid, new HashMap<String, ConnectionFromClientHandler>());
		}
		clientsList.get(uid).put(connectionId, client);

		// Display
		controller.addTextToTextFlow("Client conntected: UID (" + uid + ")");

		// Notify other clients
		Message messageForOtherClients = new SetupAddGroupMemberMessage(uid, true);

		for (Entry<String, HashMap<String, ConnectionFromClientHandler>> entryLevelOne : clientsList.entrySet()) {
			String uidKey = entryLevelOne.getKey();
			HashMap<String, ConnectionFromClientHandler> levelOneValue = entryLevelOne.getValue();

			if (!uidKey.equals(uid)) {

				// Notify this client of current connected user ids,
				// excluding himself
				Message messageForThisClient = new SetupAddGroupMemberMessage(uidKey, false);
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
