package instantmessage.client.customcontrol;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.viewmodel.ChatFileMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class ChatFileMessageCustomControlController implements ICustomControlController{
	@FXML private ImageView avatarImageView;
	@FXML private Label displayNameLabel;
	@FXML private Label chatMessageLabel;
	@FXML private GridPane chatMessageContainer;
	private ChatFileMessageViewModel chatMessageViewModel;
	
	@Override
	public void setViewData(IViewModel viewModel){
		 chatMessageViewModel=(ChatFileMessageViewModel)viewModel;
		FxUIHelper.setAvatarImage(avatarImageView, chatMessageViewModel.getPicUrl());
		FxUIHelper.setText(displayNameLabel, chatMessageViewModel.getDisplayName());
		FxUIHelper.setText(chatMessageLabel, chatMessageViewModel.getFileName());
		
		// Set Orientation
		setOrientation(chatMessageViewModel.getOrientation());
	}
	
	private void setOrientation(Orientation orientation){
		if(orientation!=Orientation.RIGHT){
			return;
		}
		chatMessageContainer.getParent().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		chatMessageContainer.getStyleClass().add("chat_message_reverse");
	}

	@FXML
	private void chatMessageContainerMouseClicked(MouseEvent event){
		try {
            Desktop.getDesktop().open(
                    new File(chatMessageViewModel.getClickLink()));
        } catch (IOException e) {

            e.printStackTrace();
        }
	}
	
	
}
