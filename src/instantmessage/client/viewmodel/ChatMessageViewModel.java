package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.Orientation;
import instantmessage.client.model.User;

/**
 * Super class for all ChatMessage view models
 * 
 * @author Tao Liu
 *
 */
public abstract class ChatMessageViewModel implements IViewModel {

	// Fields
	private String uid;
	private String picUrl;
	private String displayName;
	private Orientation orientation;
	private ChatMessageViewModelType viewModelType;

	// Constructor
	public ChatMessageViewModel(String uid, String picUrl, String displayName) {
		this.setUid(uid);
		this.setPicUrl(picUrl);
		this.setDisplayName(displayName);
		this.setOrientation(Orientation.LEFT_TO_RIGHT);
		this.viewModelType = setViewModelType();
	}

	// Constructor
	public ChatMessageViewModel(User user) {
		this(user.getUid(), user.getPicUrl(), user.getDisplayName());
	}

	// Getters and setters

	public ChatMessageViewModelType getViewModelType() {
		return viewModelType;
	}

	public abstract ChatMessageViewModelType setViewModelType();

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
