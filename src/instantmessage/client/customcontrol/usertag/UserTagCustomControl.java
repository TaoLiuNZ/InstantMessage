package instantmessage.client.customcontrol.usertag;

import instantmessage.client.customcontrol.CustomControl;
import instantmessage.client.viewmodel.IViewModel;

/**
 * A sub class of CustomControl; It is used to show user tag;
 * 
 * @author Tao Liu
 *
 */
public class UserTagCustomControl extends CustomControl {

	public UserTagCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "UserTagCustomControl.fxml";
	}

}
