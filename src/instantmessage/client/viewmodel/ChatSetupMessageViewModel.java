package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.model.User;

/**
 * View model for ChatTextMessage
 * 
 * @author Tao Liu
 *
 */
public class ChatSetupMessageViewModel extends ChatMessageViewModel {

	// Fields
	private String msg;

	// Constructor
	public ChatSetupMessageViewModel(String uid,String msg) {
		super(uid, null, null);
		this.setMsg(msg);
	}

	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_SETUP_MESSAGE;
	}

	// Getters and setters
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
