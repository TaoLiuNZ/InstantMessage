package instantmessage.server.manager.message;

import java.net.Socket;

import instantmessage.server.model.Message;
import instantmessage.server.ui.ServerUIController;

/**
 * Message Executions' interface
 * 
 * @author Tao Liu
 *
 */
public interface IMessageExecution {
	/**
	 * Send message object to client
	 * 
	 * @param socket
	 * @param message
	 */
	void sendMessageToClient(Socket socket, Message message);

	/**
	 * Handle message from client
	 * 
	 * @param socket
	 * @param controller
	 */
	void handleMessageFromClient(Socket socket, ServerUIController controller);
}
