package instantmessage.client.manager;

import java.util.HashMap;

import instantmessage.client.model.User;
import instantmessage.client.viewmodel.LoginViewModel;
import instantmessage.server.model.UserInDb;

/**
 * Manage users, including login,logout etc...
 * 
 * @author Tao Liu
 *
 */
public class UserManager {

	// User data is for demonstration only;
	// In real implementation ,these data should come from db
	private static HashMap<String, UserInDb> usersInDb = new HashMap<String, UserInDb>() {
		{
			put("0001", new UserInDb("0001", "james", "james", "James", "james.png"));
			put("0002", new UserInDb("0002", "paul", "paul", "Paul", "paul.png"));
			put("0003", new UserInDb("0003", "michael", "michael", "Michael", "michael.png"));
			put("0004", new UserInDb("0004", "chris", "chris", "Chris", "chris.png"));
		}
	};

	/**
	 * Verify a user
	 * 
	 * @param loginViewModel
	 * @return
	 */
	public static User verifyUser(LoginViewModel loginViewModel) {
		String userName = loginViewModel.getUserName();
		String password = loginViewModel.getPassword();

		for (UserInDb userInDb : usersInDb.values()) {
			if (userInDb.getUserName().equals(userName) && userInDb.getPassword().equals(password)) {
				return convertToUser(userInDb);
			}
		}
		return null;
	}

	/**
	 * Find a user by user id
	 * 
	 * @param uid
	 * @return
	 */
	public static User findUserById(String uid) {

		if (usersInDb.containsKey(uid)) {
			UserInDb userInDb = usersInDb.get(uid);
			return convertToUser(userInDb);
		}
		return null;
	}

	/**
	 * Convert a server user object to a client user object
	 * 
	 * @param userInDb
	 * @return
	 */
	private static User convertToUser(UserInDb userInDb) {
		return new User(userInDb.getUid(), userInDb.getDisplayName(), userInDb.getPicUrl());
	}
}
