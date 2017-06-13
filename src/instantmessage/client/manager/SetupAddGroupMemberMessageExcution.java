package instantmessage.client.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.ui.IUIController;

public class SetupAddGroupMemberMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToServer(Socket socket,Message message) {
		// Get info
		String groupMemberUid=((SetupAddGroupMemberMessage)message).getGroupMemberUid();
		
		// Send
		MessageManager.sendText(socket, groupMemberUid);
	}

	@Override
	public void handleMessageFromServer(Socket socket,IUIController controller) {
		// TODO Group chat UI controller
		
	}

}
