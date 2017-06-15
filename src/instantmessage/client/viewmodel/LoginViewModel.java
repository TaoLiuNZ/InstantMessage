package instantmessage.client.viewmodel;

/**
 * View model for login
 * 
 * @author liuta
 *
 */
public class LoginViewModel implements IViewModel {

	// Fields
	private String userName;
	private String password;

	// Constructor
	public LoginViewModel(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	// Getters and setters

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
