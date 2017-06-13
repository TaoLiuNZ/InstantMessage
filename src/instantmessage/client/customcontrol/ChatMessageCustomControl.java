package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class ChatMessageCustomControl extends CustomControl{


	public ChatMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatMessageCustomControl.fxml";
	}	 

	 
}
