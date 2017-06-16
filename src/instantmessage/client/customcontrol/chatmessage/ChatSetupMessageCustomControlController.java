package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatSetupMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for ChatTextMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatSetupMessageCustomControlController extends ChatMessageCustomControlController {

	// Fields
	@FXML
	private Label chatSetupMessageLabel;

	// Methods
	@Override
	public void setViewData(IViewModel viewModel) {
		ChatSetupMessageViewModel chatMessageViewModel = (ChatSetupMessageViewModel) viewModel;
		FxUIHelper.setText(chatSetupMessageLabel, chatMessageViewModel.getMsg());
	}

}
