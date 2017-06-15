package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.manager.UserManager;
import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;

/**
 * Execution for GroupTextMessage
 * 
 * @author Tao Liu
 *
 */
public class GroupTextMessageExecution implements IMessageExecution {

	@Override
	public void sendMessageToServer(Socket socket, Message message) {
		// Get info
		String uid = ((GroupTextMessage) message).getSenderUid();
		String msg = ((GroupTextMessage) message).getMsg();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, uid);
		MessageManager.sendText(socket, msg);
	}

	@Override
	public void handleMessageFromServer(Socket socket, IUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String msg = MessageManager.receiveText(socket);

		// Display in UI
		GroupChatUIController groupChatUIController = (GroupChatUIController) controller;
		User user = UserManager.findUserById(uid);
		ChatTextMessageViewModel viewModel = new ChatTextMessageViewModel(user, msg);
		groupChatUIController.addChatMessage(viewModel);

	}

}
