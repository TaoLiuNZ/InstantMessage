package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controller for ChatTextMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatTextMessageCustomControlController extends ChatMessageCustomControlController {

	// Fields
	@FXML
	private ImageView avatarImageView;
	@FXML
	private Label displayNameLabel;
	@FXML
	private Label chatMessageLabel;

	// Methods
	@Override
	public void setViewData(IViewModel viewModel) {
		ChatTextMessageViewModel chatMessageViewModel = (ChatTextMessageViewModel) viewModel;
		FxUIHelper.setAvatarImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setText(chatMessageLabel, chatMessageViewModel.getMsg());

		// Set Orientation
		setOrientation(chatMessageLabel, chatMessageViewModel.getOrientation());
	}

}
