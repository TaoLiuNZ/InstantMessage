package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.helper.FileHelper;
import instantmessage.client.manager.UserManager;
import instantmessage.client.model.GroupFileMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatFileMessageViewModel;
import instantmessage.client.viewmodel.ChatImageMessageViewModel;
import instantmessage.client.viewmodel.ChatMessageViewModel;

/**
 * Execution for GroupFileMessage
 * 
 * @author Tao Liu
 *
 */
public class GroupFileMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToServer(Socket socket, Message message) {
		// Get info
		String uid = ((GroupFileMessage) message).getSenderUid();
		String filePath = ((GroupFileMessage) message).getFilePath();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendFile(socket, filePath);
	}

	@Override
	public void handleMessageFromServer(Socket socket, IUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String fileName = MessageManager.receiveText(socket);
		long fileSize = MessageManager.receiveLong(socket);
		String localFilePath = MessageManager.receiveFile(socket, fileName, fileSize);

		// Display in UI
		GroupChatUIController groupChatUIController = (GroupChatUIController) controller;
		User user = UserManager.findUserById(uid);
		ChatMessageViewModel viewModel;
		if (FileHelper.isImage(localFilePath)) {
			viewModel = new ChatImageMessageViewModel(user, localFilePath);
		} else {
			viewModel = new ChatFileMessageViewModel(user, fileName, localFilePath);
		}
		groupChatUIController.addChatMessage(viewModel);

	}

}
