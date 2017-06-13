package instantmessage.server.manager;

import java.net.Socket;

import instantmessage.server.model.Message;
import instantmessage.server.ui.ServerUIController;

public interface IMessageExcution {

	void sendMessageToClient(Socket socket,Message message);
	void handleMessageFromClient(Socket socket,ServerUIController controller);
}
