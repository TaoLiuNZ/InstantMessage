package instantmessage.client.manager;

import instantmessage.client.customcontrol.chatmessage.ChatFileMessageCustomControl;
import instantmessage.client.customcontrol.chatmessage.ChatImageMessageCustomControl;
import instantmessage.client.customcontrol.chatmessage.ChatMessageCustomControl;
import instantmessage.client.customcontrol.chatmessage.ChatTextMessageCustomControl;
import instantmessage.client.viewmodel.ChatMessageViewModel;

/**
 * This class is used to call corresponding ChatMessageCustomControl by message
 * type
 * 
 * @author Tao Liu
 *
 */
public class ChatMessageCustomControlManager {

	/**
	 * Get ChatMessageCustomControl by type
	 * 
	 * @param chatMessageViewModel
	 * @return
	 */
	public static ChatMessageCustomControl getChatMessageCustomControlByType(
			ChatMessageViewModel chatMessageViewModel) {
		switch (chatMessageViewModel.getViewModelType()) {
		case CHAT_IMAGE_MESSAGE:
			return new ChatImageMessageCustomControl(chatMessageViewModel);
		case CHAT_FILE_MESSAGE:
			return new ChatFileMessageCustomControl(chatMessageViewModel);
		case CHAT_TEXT_MESSAGE:
			return new ChatTextMessageCustomControl(chatMessageViewModel);
		default:
			return null;
		}
	}
}
