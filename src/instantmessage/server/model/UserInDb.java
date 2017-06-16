package instantmessage.server.model;

/**
 * Database User model
 * 
 * @author Tao Liu
 *
 */
public class UserInDb {

	// Fields
	private String uid;
	private String displayName;
	private String picUrl;
	private String userName;
	private String password;

	// Constructor
	public UserInDb(String uid, String userName, String password, String displayName, String picUrl) {
		this.setUid(uid);
		this.setUserName(userName);
		this.setPassword(password);
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
