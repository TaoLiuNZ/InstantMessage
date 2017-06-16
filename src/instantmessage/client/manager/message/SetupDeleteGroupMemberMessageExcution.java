package instantmessage.client.manager.message;

import java.net.Socket;

import instantmessage.client.manager.UserManager;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.model.SetupDeleteGroupMemberMessage;
import instantmessage.client.model.User;
import instantmessage.client.ui.GroupChatUIController;
import instantmessage.client.ui.IUIController;
import instantmessage.client.viewmodel.ChatSetupMessageViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;

/**
 * Execution for SetupDeleteGroupMemberMessage
 * 
 * @author Tao Liu
 *
 */
public class SetupDeleteGroupMemberMessageExcution implements IMessageExecution {

	@Override
	public void sendMessageToServer(Socket socket, Message message) {
		// Get info
		SetupDeleteGroupMemberMessage setupDeleteGroupMemberMessage = (SetupDeleteGroupMemberMessage) message;
		String groupMemberUid = setupDeleteGroupMemberMessage.getGroupMemberUid();
		String connectionId = setupDeleteGroupMemberMessage.getConnectionId();

		// Send
		MessageManager.sendLong(socket, message.getMessageType());
		MessageManager.sendText(socket, groupMemberUid);
		MessageManager.sendText(socket, connectionId);
	}

	@Override
	public void handleMessageFromServer(Socket socket, IUIController controller) {
		// Get info
		String uid = MessageManager.receiveText(socket);

		// Remove from UI
		GroupChatUIController groupChatUIController = (GroupChatUIController) controller;
		User user = UserManager.findUserById(uid);
		
		UserTagViewModel userTagViewModel = new UserTagViewModel(user);
		groupChatUIController.removeUserTag(userTagViewModel);
		
		ChatSetupMessageViewModel ChatSetupMessageViewModel=new ChatSetupMessageViewModel(uid,user.getDisplayName()+" has left the group.");
		groupChatUIController.addChatMessage(ChatSetupMessageViewModel);

	}

}
