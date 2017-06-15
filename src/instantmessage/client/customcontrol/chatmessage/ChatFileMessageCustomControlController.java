package instantmessage.client.customcontrol.chatmessage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatFileMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller for ChatFileMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatFileMessageCustomControlController extends ChatMessageCustomControlController {

	// Fields
	@FXML
	private ImageView avatarImageView;
	@FXML
	private Label displayNameLabel;
	@FXML
	private Label chatMessageLabel;
	@FXML
	private GridPane chatMessageContainer;
	private ChatFileMessageViewModel chatMessageViewModel;

	// Methods
	@Override
	public void setViewData(IViewModel viewModel) {
		chatMessageViewModel = (ChatFileMessageViewModel) viewModel;
		FxUIHelper.setAvatarImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setText(chatMessageLabel, chatMessageViewModel.getFileName());

		// Set Orientation
		setOrientation(chatMessageContainer, chatMessageViewModel.getOrientation());
	}

	// Event Handlers
	/**
	 * The click event handler of the message
	 * 
	 * @param event
	 */
	@FXML
	private void chatMessageContainerMouseClicked(MouseEvent event) {
		try {
			// Open file
			Desktop.getDesktop().open(new File(chatMessageViewModel.getClickLink()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
