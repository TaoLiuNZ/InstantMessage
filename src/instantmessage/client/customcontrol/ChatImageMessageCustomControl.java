package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class ChatImageMessageCustomControl extends ChatMessageCustomControl{

	public ChatImageMessageCustomControl() {
		super();
	}
	public ChatImageMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatImageMessageCustomControl.fxml";
	}	 

	 
}
