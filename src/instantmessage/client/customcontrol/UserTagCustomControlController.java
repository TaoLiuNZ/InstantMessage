package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class UserTagCustomControlController implements ICustomControlController{
	@FXML private ImageView avatarImageView;
	@FXML private Label displayNameLabel;
	@FXML private Label ipAddressLabel;
	@FXML private CheckBox blockCheckBox;
	private UserTagViewModel userTagViewModel;
	
	public void setViewData(IViewModel viewModel){
		UserTagViewModel userTagViewModel=(UserTagViewModel)viewModel;
		this.userTagViewModel=userTagViewModel;
		
		FxUIHelper.setImage(avatarImageView, userTagViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, userTagViewModel.getDisplayName());
		FxUIHelper.setText(ipAddressLabel, userTagViewModel.getIpAddress());		
	}
	
	@FXML
	private void blockCheckBoxAction(ActionEvent event){
		if(blockCheckBox.isSelected()){
			FxUIHelper.setText(blockCheckBox, "Blocked");	
			userTagViewModel.setIsBlocked(true);
			return;
		}
		FxUIHelper.setText(blockCheckBox,"Block");	
		userTagViewModel.setIsBlocked(false);
	}
}
