package instantmessage.client.manager;

import java.util.HashMap;

import instantmessage.client.model.User;
import instantmessage.client.viewmodel.LoginViewModel;
import instantmessage.server.model.UserInDb;

public class UserManager {
	
	// Test data, in real implementation ,these data should come from db
	private static HashMap<String,UserInDb> usersInDb=new HashMap<String,UserInDb>(){{
		put("0001",new UserInDb("0001","james","james","James","james.png"));
		put("0002",new UserInDb("0002","paul","paul","Paul","paul.png"));
		put("0003",new UserInDb("0003","michael","michael","Michael","michael.png"));
		put("0004",new UserInDb("0004","chris","chris","Chris","chris.png"));
	}};

	public static User VerifyUser(LoginViewModel loginViewModel){
		String userName=loginViewModel.getUserName();
		String password=loginViewModel.getPassword();

		for (UserInDb userInDb : usersInDb.values()) {
		    if(userInDb.getUserName().equals(userName)&&userInDb.getPassword().equals(password)){
		    	return convertToUser(userInDb);
		    }
		}
		return null;
	}
	
	public static User FindUserById(String uid){
		
		if(usersInDb.containsKey(uid)){
			UserInDb userInDb = usersInDb.get(uid);
			return convertToUser(userInDb);
		}
		return null;
	}
	

	
	private static User convertToUser(UserInDb userInDb){
		return new User(userInDb.getUid(),userInDb.getDisplayName(),userInDb.getPicUrl());
	}
}
