package instantmessage.client.customcontrol.chatmessage;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 * A sub class of ChatMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatFileMessageCustomControl extends ChatMessageCustomControl {

	public ChatFileMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatFileMessageCustomControl.fxml";
	}

}
