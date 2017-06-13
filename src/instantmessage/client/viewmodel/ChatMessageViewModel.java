package instantmessage.client.viewmodel;

import instantmessage.client.model.User;

public class ChatMessageViewModel implements IViewModel{
	private String uid;
	private String picUrl;
	private String displayName;
	private String msg;
	
	public ChatMessageViewModel(String uid,String picUrl,String displayName,String msg){
		this.setUid(uid);
		this.setPicUrl(picUrl);
		this.setDisplayName(displayName);
		this.setMsg(msg);
	}
	
	public ChatMessageViewModel(User user,String msg){
		this.setUid(user.getUid());
		this.setPicUrl(user.getPicUrl());
		this.setDisplayName(user.getDisplayName());
		this.setMsg(msg);
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
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}