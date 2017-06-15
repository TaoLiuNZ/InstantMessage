package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.model.User;

/**
 * View model for ChatFileMessage
 * 
 * @author Tao Liu
 *
 */
public class ChatFileMessageViewModel extends ChatMessageViewModel {

	// Private fields
	private String fileName;
	private String clickLink;

	// Constructor
	public ChatFileMessageViewModel(String uid, String picUrl, String displayName, String fileName, String clickLink) {
		super(uid, picUrl, displayName);
		this.setFileName(fileName);
		this.setClickLink(clickLink);
	}

	// Constructor
	public ChatFileMessageViewModel(User user, String fileName, String clickLink) {
		this(user.getUid(), user.getPicUrl(), user.getDisplayName(), fileName, clickLink);
	}

	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_FILE_MESSAGE;
	}

	// Getters and setters
	public String getClickLink() {
		return clickLink;
	}

	public void setClickLink(String clickLink) {
		this.clickLink = clickLink;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
