package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.manager.UserManager;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.UserTagViewModel;

/**
 * Execution for SetupAddGroupMemberMessage
 * 
 * @author Tao Liu
 *
 */
public class SetupAddGroupMemberMessageExcution implements IMessageExecution {

	@Override
	public void sendMessageToServer(Socket socket, Message message) {
		// Get info
		String groupMemberUid = ((SetupAddGroupMemberMessage) message).getGroupMemberUid();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
	}

	@Override
	public void handleMessageFromServer(Socket socket, IUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);
		String ipAddress = MessageManager.receiveText(socket);

		// Display in UI
		GroupChatUIController groupChatUIController = (GroupChatUIController) controller;
		User user = UserManager.findUserById(uid);
		user.setIpAddress(ipAddress);
		UserTagViewModel viewModel = new UserTagViewModel(user);
		groupChatUIController.addUserTag(viewModel);

	}

}
