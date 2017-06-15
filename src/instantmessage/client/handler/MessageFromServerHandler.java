package instantmessage.client.handler;

import java.net.Socket;

import instantmessage.client.manager.message.MessageManager;
import instantmessage.client.ui.IUIController;

/**
 * This class is used to handle messages from server
 * @author Tao Liu
 *
 */
public class MessageFromServerHandler extends Thread {
	
	// Fields
	private Socket socket;
	private IUIController controller;

	// Constructor
	public MessageFromServerHandler(Socket socket, IUIController controller) {
		this.socket = socket;
		this.controller = controller;
	}

	@Override
	public void run() {
		// Keep running
		while (true) {
			
			// Check type
			long messageType = MessageManager.receiveLong(socket);

			try {
				
				// According to message type, call corresponding execution class to handle
				MessageManager.getMessageExecutionByType(messageType).handleMessageFromServer(socket, controller);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
