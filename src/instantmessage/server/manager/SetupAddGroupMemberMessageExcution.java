package instantmessage.server.manager;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.model.Message;
import instantmessage.server.model.SetupAddGroupMemberMessage;
import instantmessage.server.ui.ServerUIController;

public class SetupAddGroupMemberMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToClient(Socket socket, Message message) {
		// Get info
		SetupAddGroupMemberMessage setupAddGroupMemberMessage=(SetupAddGroupMemberMessage) message;
		String groupMemberUid = setupAddGroupMemberMessage.getGroupMemberUid();
		Boolean isNewMember=setupAddGroupMemberMessage.getIsNewMember();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
		MessageManager.sendBoolean(socket, isNewMember);
		MessageManager.sendText(socket, socket.getRemoteSocketAddress().toString());
	}

	@Override
	public void handleMessageFromClient(Socket socket, ServerUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);

		// Create and start the client handler thread
		ConnectionFromClientHandler clientConnection = new ConnectionFromClientHandler(socket, controller);
		clientConnection.start();

		// Add this handler to clients list
		HashMap<String, ConnectionFromClientHandler> clients = ClientConnectionManager.getInstance(controller)
				.getClientsList();
		clients.put(uid, clientConnection);

		// Display
		controller.addTextToTextFlow("Client conntected: UID (" + uid + ")");

		// Notify other clients
		for (Entry<String, ConnectionFromClientHandler> entry : clients.entrySet()) {
			String key = entry.getKey();
			ConnectionFromClientHandler value = entry.getValue();

			if (!key.equals(uid)) {

				// Notify other clients that a new client is connected
				Message messageForOtherClients = new SetupAddGroupMemberMessage(uid,true);
				this.sendMessageToClient(value.getSocket(), messageForOtherClients);

				// Send current client list to this client
				Message messageForThisClient = new SetupAddGroupMemberMessage(key,false);
				this.sendMessageToClient(socket, messageForThisClient);
			}
		}
	}

}
