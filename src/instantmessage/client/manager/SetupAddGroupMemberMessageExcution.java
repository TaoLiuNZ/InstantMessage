package instantmessage.client.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;

public class SetupAddGroupMemberMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToServer(Socket socket,Message message) {
		// Get info
		String groupMemberUid=((SetupAddGroupMemberMessage)message).getGroupMemberUid();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
	}

	@Override
	public void handleMessageFromServer(Socket socket,IUIController controller) {
		// Get info
				String uid=MessageManager.receiveText(socket);
				String ipAddress=MessageManager.receiveText(socket);

				// Send to UI Controller to handle
				GroupChatUIController groupChatUIController=(GroupChatUIController)controller;
				
				// User
				User user=UserManager.FindUserById(uid);
				user.setIpAddress(ipAddress);
				
				// View model
				UserTagViewModel viewModel=new UserTagViewModel(user);
				
				groupChatUIController.addTagUser(viewModel);
		
	}

}
