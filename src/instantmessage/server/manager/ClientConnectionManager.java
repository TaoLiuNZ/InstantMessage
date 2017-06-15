package instantmessage.server.manager;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.ui.ServerUIController;

public class ClientConnectionManager {
	private static HashMap<String, ConnectionFromClientHandler> clientsList;
	private static ServerUIController serverUIController;
	private static ClientConnectionManager instance = null;

	protected ClientConnectionManager() {
	}

	public static ClientConnectionManager getInstance(ServerUIController controller) {
		if (instance == null) {
			instance = new ClientConnectionManager();
			clientsList = new HashMap<String, ConnectionFromClientHandler>();
			serverUIController = controller;
		}
		return instance;
	}

	public void startNewClientHandler(Socket socket) {
		// Get message type & uid
		long messageType = MessageManager.receiveLong(socket);
		String uid = MessageManager.receiveText(socket);
		new SetupAddGroupMemberMessageExcution().handleMessageFromClient(socket, uid, serverUIController);

		// Create and start the client handler thread
		ConnectionFromClientHandler clientConnection = new ConnectionFromClientHandler(socket, serverUIController);
		clientConnection.start();

		// Add this handler to clients list
		clientsList.put(uid, clientConnection);

		// Display
		serverUIController.addTextToTextFlow("Client conntected: UID (" + uid + ")");
	}

	public void stopClientHandler(Socket socket) {
		// Get message type & uid
		// TODO : STOP CLIENT HANDLER
		String uid = "";

		// Find this handler
		ConnectionFromClientHandler clientConnection = clientsList.get(uid);

		// Add this handler to clients list
		clientsList.remove(uid);

		// Stop thread
		clientConnection = null;

		// Display
		serverUIController.addTextToTextFlow("Client disconntected: UID (" + uid + ")");
	}

	public HashMap<String, ConnectionFromClientHandler> getClientsList() {
		return ClientConnectionManager.clientsList;
	}

}
