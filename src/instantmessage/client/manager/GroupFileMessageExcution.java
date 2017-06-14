package instantmessage.client.manager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.client.model.GroupFileMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatFileMessageViewModel;
import instantmessage.client.viewmodel.ChatImageMessageViewModel;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;
import instantmessage.client.helper.FileHelper;
import instantmessage.client.manager.MessageManager;

public class GroupFileMessageExcution implements IMessageExcution {

	@Override
	public void sendMessageToServer(Socket socket,Message message) {
		// Get info
		String uid=((GroupFileMessage)message).getSenderUid();
		String filePath=((GroupFileMessage)message).getFilePath();
		
		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendFile(socket, filePath);
	}

	@Override
	public void handleMessageFromServer(Socket socket,IUIController controller) {
		// Get info
				String uid=MessageManager.receiveText(socket);
				String fileName=MessageManager.receiveText(socket);
				long fileSize=MessageManager.receiveLong(socket);
				String localFilePath=MessageManager.receiveFile(socket, fileName, fileSize);

		// Send to UI Controller to handle
		GroupChatUIController groupChatUIController=(GroupChatUIController)controller;
		
		// User
		User user=UserManager.FindUserById(uid);

					
		// Check if it's image
		if(FileHelper.isImage(localFilePath)){
			// View model
						ChatImageMessageViewModel viewModel=new ChatImageMessageViewModel(user,localFilePath);
						groupChatUIController.addChatMessage(viewModel);
		}
		else{
			// View model
			ChatFileMessageViewModel viewModel=new ChatFileMessageViewModel(user,fileName,localFilePath);
			groupChatUIController.addChatMessage(viewModel);
		}
		

		
		
			
	}

}
