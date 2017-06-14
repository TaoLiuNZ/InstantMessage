package instantmessage.client.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;

public class GroupTextMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToServer(Socket socket,Message message) {
		// Get info
		String uid=((GroupTextMessage)message).getSenderUid();
		String msg=((GroupTextMessage)message).getMsg();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendText(socket, msg);
	}

	@Override
	public void handleMessageFromServer(Socket socket,IUIController controller) {
		// Get info
		String uid=MessageManager.receiveText(socket);
		String msg=MessageManager.receiveText(socket);		

		// Send to UI Controller to handle
		GroupChatUIController groupChatUIController=(GroupChatUIController)controller;
		
		// User
		User user=UserManager.FindUserById(uid);
		
		// Check if user is blocked
		
		// View model
		ChatTextMessageViewModel viewModel=new ChatTextMessageViewModel(user,msg);
		
		groupChatUIController.addChatMessage(viewModel);
			
	}

}
