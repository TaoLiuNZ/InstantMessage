package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class UserTagCustomControl extends CustomControl{

	public UserTagCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "UserTagCustomControl.fxml";
	}
	 
}
