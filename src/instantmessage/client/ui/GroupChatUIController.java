package instantmessage.client.ui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.event.ChangeEvent;

import instantmessage.client.constant.Orientation;
import instantmessage.client.customcontrol.ChatMessageCustomControl;
import instantmessage.client.customcontrol.UserTagCustomControl;
import instantmessage.client.handler.MessageFromServerHandler;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.manager.MessageManager;
import instantmessage.client.model.GroupTextMessage;
import instantmessage.client.model.Message;
import instantmessage.client.model.SetupAddGroupMemberMessage;
import instantmessage.client.model.User;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class GroupChatUIController implements IUIController{
@FXML private ImageView avatarImageView;
@FXML private Label displayNameLabel;
@FXML private VBox chatMessageContainerVBox;
@FXML private ScrollPane chatMessageContainerScrollPane;
@FXML private VBox userTagContainerVBox;
@FXML private TextField messageToSendTextField;
@FXML private Button sendBtn;
@FXML private Button fileBtn;


private User currentUser;
private Socket socket;
private HashMap<String,UserTagViewModel> groupMembers;

public void Init(User currentUser){
	SetListeners();
	setUIData(currentUser);
	startConnectToServer();
}

private void SetListeners(){
	chatMessageContainerVBox.heightProperty().addListener(new ChangeListener<Number>() {
	    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {	    	
	    	chatMessageContainerScrollPane.setVvalue(1.0);
	    }
	});
}

	private void setUIData(User currentUser){
		// groupMembers
		groupMembers=new HashMap<String,UserTagViewModel>();
		
		// Current user
		this.currentUser=currentUser;
		FxUIHelper.setImage(avatarImageView, currentUser.getPicUrl());
		FxUIHelper.setText(displayNameLabel, currentUser.getDisplayName());
				
	}
	
	private void startConnectToServer(){
		// Start connecting to server
		
		try {
			socket = new Socket("localhost",5000);
			
			// Start listening response from server
			MessageFromServerHandler serverHandler=new MessageFromServerHandler(socket,this);
		serverHandler.start();
		
		// Send current client info to server to register
		Message message=new SetupAddGroupMemberMessage(currentUser.getUid());
		MessageManager.getMessageExcutionByType(message.getMessageType()).sendMessageToServer(socket, message);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addChatMessage(ChatMessageViewModel viewModel){
		// Check the black list
		UserTagViewModel user=groupMembers.get(viewModel.getUid());
		if(user!=null&&user.getIsBlocked())
			return;
		
		// Check if this message is from user himself
		// If true, set orientation to right
		if(viewModel.getUid().equals(currentUser.getUid())){
			viewModel.setOrientation(Orientation.RIGHT);
		}
		
		ChatMessageCustomControl chatMsg=new ChatMessageCustomControl(viewModel);
		FxUIHelper.addElementToParent(chatMessageContainerVBox, chatMsg);
	}
	
	public void addTagUser(UserTagViewModel viewModel){
		// Add to group members list
		groupMembers.put(viewModel.getUid(), viewModel);
		
		// UI
		UserTagCustomControl userTag=new UserTagCustomControl(viewModel);
		FxUIHelper.addElementToParent(userTagContainerVBox, userTag);
	}
	
	@FXML private void sendBtnAction(ActionEvent event){
		
		sendMessage();
	}
	
	
	@FXML private void messageToSendTextFieldKeyPressed(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER) {
			sendMessage();
	     }
	}
	
	private void sendMessage(){
		// Check if empty
		String msgToSend=messageToSendTextField.getText();
		if(msgToSend.isEmpty()){
			return;
		}
		
		// Clear text
		messageToSendTextField.clear();
		
		// Set focus back to text field
		messageToSendTextField.requestFocus();
		
		// Message 
		GroupTextMessage message=new GroupTextMessage(currentUser.getUid(),msgToSend);
		MessageManager.getMessageExcutionByType(message.getMessageType()).sendMessageToServer(socket, message);
	}
	
	public User getCurrentUser(){
		return this.currentUser;
	}
	
	public HashMap<String, UserTagViewModel> getGroupMembersList(){
		return this.groupMembers;
	}
	
	
	@FXML private void fileBtnAction(ActionEvent event){
		
	}
}
