package instantmessage.client.manager;

import java.util.HashMap;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.customcontrol.ChatFileMessageCustomControl;
import instantmessage.client.customcontrol.ChatImageMessageCustomControl;
import instantmessage.client.customcontrol.ChatMessageCustomControl;
import instantmessage.client.customcontrol.ChatTextMessageCustomControl;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;

public class ChatMessageCustomControlManager {

	public static ChatMessageCustomControl getChatMessageCustomControlByType(ChatMessageViewModel chatMessageViewModel){
		switch (chatMessageViewModel.getViewModelType()){
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
