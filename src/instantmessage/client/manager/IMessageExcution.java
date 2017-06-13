package instantmessage.client.manager;

import java.net.Socket;

import instantmessage.client.model.Message;
import instantmessage.client.ui.IUIController;

public interface IMessageExcution {

	void sendMessageToServer(Socket socket,Message message);
	void handleMessageFromServer(Socket socket,IUIController controller);
}
