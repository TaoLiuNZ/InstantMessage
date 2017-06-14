package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.Orientation;
import instantmessage.client.model.User;

public abstract class ChatMessageViewModel implements IViewModel{
	private String uid;
	private String picUrl;
	private String displayName;
	private Orientation orientation;
	private ChatMessageViewModelType viewModelType;
	
	public ChatMessageViewModel(String uid,String picUrl,String displayName){
		this.setUid(uid);
		this.setPicUrl(picUrl);
		this.setDisplayName(displayName);
		this.setOrientation(Orientation.LEFT);
		this.viewModelType=setViewModelType();
	}
	
	public ChatMessageViewModelType getViewModelType() {
		return viewModelType;
	}

	public ChatMessageViewModel(User user){
		this(user.getUid(),user.getPicUrl(),user.getDisplayName());
	}
	
	public abstract ChatMessageViewModelType setViewModelType();
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
