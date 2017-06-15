package instantmessage.server.handler;

import java.net.Socket;

import instantmessage.server.manager.MessageManager;
import instantmessage.server.ui.ServerUIController;

/**
 * This class is used to handle messages from client
 * 
 * @author Tao Liu
 *
 */
public class ConnectionFromClientHandler extends Thread {

	// Fields
	private Socket socket;
	private ServerUIController controller;

	// Constructor
	public ConnectionFromClientHandler(Socket socket, ServerUIController controller) {
		this.socket = socket;
		this.controller = controller;
	}

	/**
	 * Get socket
	 * 
	 * @return
	 */
	public Socket getSocket() {
		return this.socket;
	}

	@Override
	public void run() {

		while (true) {

			// Check type
			long messageType = MessageManager.receiveLong(socket);

			try {
				// Call MessageExcution to handle message from server
				MessageManager.getMessageExcutionByType(messageType).handleMessageFromClient(socket, controller);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
