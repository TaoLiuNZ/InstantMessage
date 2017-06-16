package instantmessage.server.manager;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.ui.ServerUIController;

/**
 * A Singleton class to manage connections from clients;
 * @author Tao Liu
 *
 */
public class ClientConnectionManager {
	
	// Fields
	private static HashMap<String, ConnectionFromClientHandler> clientsList;
	private static ServerUIController serverUIController;
	private static ClientConnectionManager instance = null;

	/**
	 * Get the only instance of this class
	 * @param controller
	 * @return
	 */
	public static ClientConnectionManager getInstance(ServerUIController controller) {
		if (instance == null) {
			instance = new ClientConnectionManager();
			clientsList = new HashMap<String, ConnectionFromClientHandler>();
			serverUIController = controller;
		}
		return instance;
	}

	/**
	 * Start a new client connection handler
	 * @param socket
	 */
	public void startNewClientHandler(Socket socket) {
		// Get message type & call corresponding execution to handle the message
		long messageType = MessageManager.receiveLong(socket);
		MessageManager.getMessageExcutionByType(messageType).handleMessageFromClient(socket,  serverUIController);
	}

	/**
	 * Stop client handler
	 * @param socket
	 */
	public void stopClientHandler(Socket socket) {
		// Get message type & call corresponding execution to handle the message
				long messageType = MessageManager.receiveLong(socket);
				MessageManager.getMessageExcutionByType(messageType).handleMessageFromClient(socket,  serverUIController);
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
