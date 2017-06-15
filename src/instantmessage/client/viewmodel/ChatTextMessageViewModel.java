package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.model.User;

/**
 * View model for ChatTextMessage
 * 
 * @author Tao Liu
 *
 */
public class ChatTextMessageViewModel extends ChatMessageViewModel {

	// Fields
	private String msg;

	// Constructor
	public ChatTextMessageViewModel(String uid, String picUrl, String displayName, String msg) {
		super(uid, picUrl, displayName);
		this.setMsg(msg);
	}

	// Constructor
	public ChatTextMessageViewModel(User user, String msg) {
		this(user.getUid(), user.getPicUrl(), user.getDisplayName(), msg);
	}

	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_TEXT_MESSAGE;
	}

	// Getters and setters
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
