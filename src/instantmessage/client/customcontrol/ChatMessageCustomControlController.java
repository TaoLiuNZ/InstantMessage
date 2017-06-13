package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChatMessageCustomControlController implements ICustomControlController{
	@FXML private ImageView avatarImageView;
	@FXML private Label displayNameLabel;
	@FXML private Label chatMessageLabel;
	
	@Override
	public void setUIData(IViewModel viewModel){
		ChatMessageViewModel chatMessageViewModel=(ChatMessageViewModel)viewModel;
		FxUIHelper.setImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setText(chatMessageLabel, chatMessageViewModel.getMsg());		
	}
	
	public void setOrientation(Orientation orientation){
		if(orientation==Orientation.RIGHT){
			chatMessageLabel.getStyleClass().clear();
			chatMessageLabel.getStyleClass().add("chat_message_reverse");
			return;
		}
		chatMessageLabel.getStyleClass().clear();
		chatMessageLabel.getStyleClass().add("chat_message");
	}

	
	
}
