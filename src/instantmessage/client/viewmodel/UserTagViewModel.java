package instantmessage.client.viewmodel;

import instantmessage.client.model.User;

/**
 * View model for user tag
 * 
 * @author Tao Liu
 *
 */
public class UserTagViewModel implements IViewModel {

	// Fields
	private String uid;
	private String picUrl;
	private String displayName;
	private String ipAddress;
	private Boolean isBlocked;

	// Constructor
	public UserTagViewModel(User user) {
		this.setUid(user.getUid());
		this.setDisplayName(user.getDisplayName());
		this.setIpAddress(user.getIpAddress());
		this.setPicUrl(user.getPicUrl());
		this.setIpAddress(user.getIpAddress());
		this.setIsBlocked(false);
	}

	// Getters and setters
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIpAddress() {
		return "IP:" + ipAddress.substring(1);
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
