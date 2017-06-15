package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.model.User;

/**
 * View model for ChatImageMessage
 * 
 * @author Tao Liu
 *
 */
public class ChatImageMessageViewModel extends ChatMessageViewModel {

	// Private fields
	private String imageUrl;

	// Constructor
	public ChatImageMessageViewModel(String uid, String picUrl, String displayName, String imageUrl) {
		super(uid, picUrl, displayName);
		this.setImageUrl(imageUrl);
	}

	// Constructor
	public ChatImageMessageViewModel(User user, String imageUrl) {
		this(user.getUid(), user.getPicUrl(), user.getDisplayName(), imageUrl);
	}

	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_IMAGE_MESSAGE;
	}

	// Getters and setters

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
