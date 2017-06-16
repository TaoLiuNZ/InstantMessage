package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.manager.UserManager;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatSetupMessageViewModel;
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
		Boolean isNewMember = MessageManager.receiveBoolean(socket);
		String ipAddress = MessageManager.receiveText(socket);

		// Display in UI
		GroupChatUIController groupChatUIController = (GroupChatUIController) controller;
		User user = UserManager.findUserById(uid);
		user.setIpAddress(ipAddress);

		UserTagViewModel userTagViewModel = new UserTagViewModel(user);
		groupChatUIController.addUserTag(userTagViewModel);

		if (isNewMember == true) {
			ChatSetupMessageViewModel ChatSetupMessageViewModel = new ChatSetupMessageViewModel(uid,
					user.getDisplayName() + " has joined the group.");
			groupChatUIController.addChatMessage(ChatSetupMessageViewModel);
		}
	}

}
