package instantmessage.client.ui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.constant.Orientation;
import instantmessage.client.customcontrol.ChatMessageCustomControl;
import instantmessage.client.customcontrol.UserTagCustomControl;
import instantmessage.client.handler.MessageFromServerHandler;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.manager.MessageManager;
import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.User;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GroupChatUIController implements IUIController{
@FXML private ImageView avatarImageView;
@FXML private Label displayNameLabel;
@FXML private VBox chatMessageContainerVBox;
@FXML private VBox userTagContainerVBox;
@FXML private TextField messageToSendTextField;
@FXML private Button sendBtn;
@FXML private Button fileBtn;

private User currentUser;
private Socket socket;

	public GroupChatUIController(){
		
		// Start connecting to server
		
		try {
			socket = new Socket("localhost",5000);
			
			// Start listening response from server
			MessageFromServerHandler serverHandler=new MessageFromServerHandler(socket,this);
		serverHandler.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUIData(User currentUser){

		// Current user
		this.currentUser=currentUser;
		FxUIHelper.setImage(avatarImageView, currentUser.getPicUrl());
		FxUIHelper.setText(displayNameLabel, currentUser.getDisplayName());
				
	}
	
	public void addChatMessage(ChatMessageViewModel viewModel){
		// Check if this message is from user himself
		// If true, set orientation to right
		Orientation orientation=Orientation.LEFT;
		if(viewModel.getUid().equals(currentUser.getUid())){
			orientation=Orientation.RIGHT;
		}
		
		ChatMessageCustomControl chatMsg=new ChatMessageCustomControl(viewModel,orientation);
		FxUIHelper.addElementToParent(chatMessageContainerVBox, chatMsg);
	}
	
	public void addTagUser(UserTagViewModel viewModel){
		UserTagCustomControl userTag=new UserTagCustomControl(viewModel);
		FxUIHelper.addElementToParent(userTagContainerVBox, userTag);
	}
	
	@FXML private void sendBtnAction(ActionEvent event){
		// Check if empty
		String msgToSend=messageToSendTextField.getText();
		if(msgToSend.isEmpty()){
			return;
		}
		
		// Message 
		GroupTextMessage message=new GroupTextMessage(currentUser.getUid(),msgToSend);
		MessageManager.getMessageExcutionByType(message.getMessageType()).sendMessageToServer(socket, message);
	}
	
	public User getCurrentUser(){
		return this.currentUser;
	}
	
	@FXML private void fileBtnAction(ActionEvent event){
		
	}
}
