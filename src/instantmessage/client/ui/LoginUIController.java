package instantmessage.client.ui;

import instantmessage.client.constant.ViewConstant;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.manager.UserManager;
import instantmessage.client.model.User;
import instantmessage.client.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginUIController implements IUIController {

	@FXML private TextField userNameTextField;
	@FXML private TextField passwordTextField;
	@FXML private Label errorLabel;
	@FXML
	private void loginBtnAction(ActionEvent event){
		// Get user name and password
		String userName=userNameTextField.getText();
		String password=passwordTextField.getText();
		
		// Validate
		if(userName.isEmpty()||password.isEmpty()){
			// Display error message
			FxUIHelper.setText(errorLabel, ViewConstant.ERROR_USERNAME_PASSWORD_REQUIRED);
			return;
		}
		
		LoginViewModel viewModel=new LoginViewModel(userName,password);
		
		// Verify
		User user=UserManager.VerifyUser(viewModel);
		if(user!=null){
			// Open group chat window
			FxUIHelper.openNewWindow(this, "GroupChatUI.fxml");
		}
		else{
			// Display error message
			FxUIHelper.setText(errorLabel, ViewConstant.ERROR_USERNAME_PASSWORD_UNMATCH);
		}
	}
}
