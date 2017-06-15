package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.model.Message;
import instantmessage.client.ui.IUIController;

/**
 * Message Executions' interface
 * @author Tao Liu
 *
 */
public interface IMessageExecution {
	/**
	 * Send message object to server
	 * @param socket
	 * @param message
	 */
	void sendMessageToServer(Socket socket,Message message);
	/**
	 * Handle message from server
	 * @param socket
	 * @param controller
	 */
	void handleMessageFromServer(Socket socket,IUIController controller);
}
