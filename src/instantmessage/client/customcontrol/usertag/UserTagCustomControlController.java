package instantmessage.client.customcontrol.usertag;

import instantmessage.client.customcontrol.ICustomControlController;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * The controller of UserTagCustomControl
 * 
 * @author Tao Liu
 *
 */
public class UserTagCustomControlController implements ICustomControlController {

	// Fields
	@FXML
	private ImageView avatarImageView;
	@FXML
	private Label displayNameLabel;
	@FXML
	private Label ipAddressLabel;
	@FXML
	private CheckBox blockCheckBox;
	private UserTagViewModel userTagViewModel;

	// Methods
	@Override
	public void setViewData(IViewModel viewModel) {
		UserTagViewModel userTagViewModel = (UserTagViewModel) viewModel;
		this.userTagViewModel = userTagViewModel;

		FxUIHelper.setAvatarImage(avatarImageView, userTagViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, userTagViewModel.getDisplayName());
		FxUIHelper.setText(ipAddressLabel, userTagViewModel.getIpAddress());
	}

	// Event handlers
	/**
	 * Click event handler of blocking user check box
	 * 
	 * @param event
	 */
	@FXML
	private void blockCheckBoxAction(ActionEvent event) {
		if (blockCheckBox.isSelected()) {
			FxUIHelper.setText(blockCheckBox, "Blocked");
			userTagViewModel.setIsBlocked(true);
			return;
		}
		FxUIHelper.setText(blockCheckBox, "Block");
		userTagViewModel.setIsBlocked(false);
	}
}
