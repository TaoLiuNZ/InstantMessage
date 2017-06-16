package instantmessage.server.handler;

import java.net.Socket;

import instantmessage.server.manager.message.MessageManager;
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
	private Boolean isRunning;
	private String IpAddress;

	// Constructor
	public ConnectionFromClientHandler(Socket socket, ServerUIController controller) {
		this.socket = socket;
		this.controller = controller;
		this.isRunning = true;
		this.IpAddress=socket.getRemoteSocketAddress().toString();
	}

	/**
	 * Get socket
	 * 
	 * @return
	 */
	public Socket getSocket() {
		return this.socket;
	}
	
	/**
	 * Get IpAddress
	 * 
	 * @return
	 */
	public String getIpAddress() {
		return this.IpAddress;
	}

	@Override
	public void run() {

		while (isRunning) {

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

	/**
	 * Stop connection with client
	 */
	public void stopConnection() {
		this.isRunning = false;
	}
}
