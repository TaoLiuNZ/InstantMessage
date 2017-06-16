package instantmessage.client.customcontrol.usertag;

import instantmessage.client.customcontrol.CustomControl;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;

/**
 * A sub class of CustomControl; It is used to show user tag;
 * 
 * @author Tao Liu
 *
 */
public class UserTagCustomControl extends CustomControl {
	private UserTagViewModel userTagViewModel;

	public UserTagCustomControl(IViewModel viewModel) {
		super(viewModel);
		this.setUserTagViewModel((UserTagViewModel)viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "UserTagCustomControl.fxml";
	}

	// Getters and setters
	public UserTagViewModel getUserTagViewModel() {
		return userTagViewModel;
	}
	public void setUserTagViewModel(UserTagViewModel userTagViewModel) {
		this.userTagViewModel = userTagViewModel;
	}

}
