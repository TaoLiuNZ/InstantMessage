package instantmessage.server.manager;

import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.handler.ConnectionFromClientHandler;
import instantmessage.server.manager.message.MessageManager;
import instantmessage.server.ui.ServerUIController;

/**
 * A Singleton class to manage connections from clients;
 * 
 * @author Tao Liu
 *
 */
public class ClientConnectionManager {

	// Fields
	private static HashMap<String, HashMap<String, ConnectionFromClientHandler>> clientsList;
	private static ServerUIController serverUIController;
	private static ClientConnectionManager instance = null;

	/**
	 * Get the only instance of this class
	 * 
	 * @param controller
	 * @return
	 */
	public static ClientConnectionManager getInstance(ServerUIController controller) {
		if (instance == null) {
			instance = new ClientConnectionManager();
			clientsList = new HashMap<String, HashMap<String, ConnectionFromClientHandler>>();
			serverUIController = controller;
		}
		return instance;
	}

	/**
	 * Start a new client connection handler
	 * 
	 * @param socket
	 */
	public void startNewClientHandler(Socket socket) {
		// Get message type & call corresponding execution to handle the message
		long messageType = MessageManager.receiveLong(socket);
		MessageManager.getMessageExcutionByType(messageType).handleMessageFromClient(socket, serverUIController);
	}

	/**
	 * Get current connected clients list
	 * @return
	 */
	public HashMap<String, HashMap<String, ConnectionFromClientHandler>> getClientsList() {
		return ClientConnectionManager.clientsList;
	}

}
