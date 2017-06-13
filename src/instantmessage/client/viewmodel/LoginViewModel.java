package instantmessage.client.viewmodel;

public class LoginViewModel implements IViewModel{
private String userName;
private String password;

public LoginViewModel(String userName,String password){
	this.setUserName(userName);
	this.setPassword(password);
}

/**
 * @return the userName
 */
public String getUserName() {
	return userName;
}
/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}

}
