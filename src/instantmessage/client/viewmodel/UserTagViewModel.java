package instantmessage.client.viewmodel;

import instantmessage.client.model.User;

public class UserTagViewModel implements IViewModel{
	private String uid;
	private String picUrl;
	private String displayName;
	private String ipAddress;
	private Boolean isBlocked;

	public UserTagViewModel(User user){
		this.setUid(user.getUid());
		this.setDisplayName(user.getDisplayName());
		this.setIpAddress(user.getIpAddress());
		this.setPicUrl(user.getPicUrl());
		this.setIpAddress(user.getIpAddress());
		this.setIsBlocked(false);
		}

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
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return "IP:"+ipAddress.substring(1);
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the isBlocked
	 */
	public Boolean getIsBlocked() {
		return isBlocked;
	}

	/**
	 * @param isBlocked the isBlocked to set
	 */
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
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
	}
