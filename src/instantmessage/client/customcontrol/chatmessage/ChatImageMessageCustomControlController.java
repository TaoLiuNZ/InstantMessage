package instantmessage.client.customcontrol.chatmessage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatImageMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller for ChatImageMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatImageMessageCustomControlController extends ChatFileMessageCustomControlController {

	// Fields
	@FXML
	private ImageView avatarImageView;
	@FXML
	private Label displayNameLabel;
	@FXML
	private ImageView imageView;
	@FXML
	private GridPane chatMessageContainer;
	private ChatImageMessageViewModel chatMessageViewModel;

	// Methods
	@Override
	public void setViewData(IViewModel viewModel) {
		chatMessageViewModel = (ChatImageMessageViewModel) viewModel;
		FxUIHelper.setAvatarImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setImage(imageView, chatMessageViewModel.getImageUrl());

		// Set Orientation
		setOrientation(chatMessageContainer, chatMessageViewModel.getOrientation());
	}

	// Event handlers
	/**
	 * The click event handler of the message
	 * 
	 * @param event
	 */
	@FXML
	private void chatMessageContainerMouseClicked(MouseEvent event) {
		try {
			Desktop.getDesktop().open(new File(chatMessageViewModel.getImageUrl()));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
