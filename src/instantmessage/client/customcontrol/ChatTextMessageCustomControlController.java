package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatTextMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class ChatTextMessageCustomControlController implements ICustomControlController{
	@FXML private ImageView avatarImageView;
	@FXML private Label displayNameLabel;
	@FXML private Label chatMessageLabel;
	
	@Override
	public void setViewData(IViewModel viewModel){
		ChatTextMessageViewModel chatMessageViewModel=(ChatTextMessageViewModel)viewModel;
		FxUIHelper.setAvatarImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setText(chatMessageLabel, chatMessageViewModel.getMsg());
		
		// Set Orientation
		setOrientation(chatMessageViewModel.getOrientation());
	}
	
	private void setOrientation(Orientation orientation){
		if(orientation!=Orientation.RIGHT){
			return;
		}
		chatMessageLabel.getParent().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		chatMessageLabel.getStyleClass().add("chat_message_reverse");
	}

	
	
}
