package instantmessage.client.ui;

import instantmessage.client.constant.ViewConstant;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.manager.UserManager;
import instantmessage.client.model.User;
import instantmessage.client.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller for LoginUI
 * 
 * @author Tao Liu
 *
 */
public class LoginUIController implements IUIController {

	// Fields
	@FXML
	private TextField userNameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Label errorLabel;

	/**
	 * Sign in the user
	 */
	private void login() {

		// Get user name and password
		LoginViewModel viewModel = new LoginViewModel(userNameTextField.getText(), passwordTextField.getText());

		// Validate
		if (viewModel.getUserName().isEmpty() || viewModel.getPassword().isEmpty()) {
			displayErrorMessage(ViewConstant.ERROR_USERNAME_PASSWORD_REQUIRED);
			return;
		}

		// Verify
		User user = UserManager.verifyUser(viewModel);
		if (user == null) {
			displayErrorMessage(ViewConstant.ERROR_USERNAME_PASSWORD_UNMATCH);
			return;
		}

		// Switch to group chat window
		FXMLLoader vxmlLoader = FxUIHelper.switchScene(this, userNameTextField,
				"/instantmessage/client/ui/GroupChatUI.fxml");
		((GroupChatUIController) vxmlLoader.getController()).Init(user);
	}

	/**
	 * Display error message in UI
	 * 
	 * @param msg
	 */
	private void displayErrorMessage(String msg) {
		FxUIHelper.setText(errorLabel, msg);
		FxUIHelper.setVisible(errorLabel, true);
	}

	/**
	 * Click event handler for login button
	 * 
	 * @param event
	 */
	@FXML
	private void loginBtnAction(ActionEvent event) {
		login();
	}

	/**
	 * Key pressed event handler for password field
	 * 
	 * @param event
	 */
	@FXML
	private void passwordTextFieldKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

}
