package instantmessage.client.model;

/**
 * User model
 * 
 * @author Tao Liu
 *
 */
public class User {

	// Private fields
	private String uid;
	private String displayName;
	private String picUrl;
	private String ipAddress;

	// Constructor
	public User(String uid, String displayName, String picUrl) {
		this.setUid(uid);
		this.setDisplayName(displayName);
		this.setPicUrl(picUrl);
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
