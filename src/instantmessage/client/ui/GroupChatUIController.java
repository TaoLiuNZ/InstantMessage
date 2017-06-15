package instantmessage.client.ui;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import instantmessage.client.constant.Orientation;
import instantmessage.client.customcontrol.chatmessage.ChatMessageCustomControl;
import instantmessage.client.customcontrol.usertag.UserTagCustomControl;
import instantmessage.client.handler.MessageFromServerHandler;
import instantmessage.client.helper.FxUIHelper;
import instantmessage.client.manager.ChatMessageCustomControlManager;
import instantmessage.client.manager.message.MessageManager;
import instantmessage.client.model.GroupFileMessage;
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
import javafx.stage.FileChooser;

/**
 * Controller for GroupChatUI
 * 
 * @author Tao Liu
 *
 */
public class GroupChatUIController implements IUIController {

	// Fields
	@FXML
	private ImageView avatarImageView;
	@FXML
	private Label displayNameLabel;
	@FXML
	private VBox chatMessageContainerVBox;
	@FXML
	private ScrollPane chatMessageContainerScrollPane;
	@FXML
	private VBox userTagContainerVBox;
	@FXML
	private TextField messageToSendTextField;
	@FXML
	private Button sendBtn;
	@FXML
	private Button fileBtn;

	private User currentUser;
	private Socket socket;
	private HashMap<String, UserTagViewModel> groupMembers;

	/**
	 * Initialize this UI with current user
	 * 
	 * @param currentUser
	 */
	public void Init(User currentUser) {
		SetListeners();
		setUIData(currentUser);
		startConnectingToServer();
	}

	/**
	 * Set listeners
	 */
	private void SetListeners() {
		// Make sure when a new message is added to the container, it
		// automatically scrolls down to the bottom
		chatMessageContainerVBox.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				chatMessageContainerScrollPane.setVvalue(1.0);
			}
		});
	}

	/**
	 * Set UI data
	 * 
	 * @param currentUser
	 */
	private void setUIData(User currentUser) {
		// groupMembers
		groupMembers = new HashMap<String, UserTagViewModel>();

		// Current user
		this.currentUser = currentUser;
		FxUIHelper.setAvatarImage(avatarImageView, currentUser.getPicUrl());
		FxUIHelper.setText(displayNameLabel, currentUser.getDisplayName());
	}

	/**
	 * Start connection with server
	 */
	private void startConnectingToServer() {
		// Start connecting to server

		try {
			socket = new Socket("localhost", 5000);

			// Start listening responses from server
			MessageFromServerHandler serverHandler = new MessageFromServerHandler(socket, this);
			serverHandler.start();

			// Send current client info to server to register
			Message message = new SetupAddGroupMemberMessage(currentUser.getUid());
			MessageManager.getMessageExecutionByType(message.getMessageType()).sendMessageToServer(socket, message);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add chat message to the UI
	 * 
	 * @param viewModel
	 */
	public void addChatMessage(ChatMessageViewModel viewModel) {
		// Check the block list
		UserTagViewModel user = groupMembers.get(viewModel.getUid());
		if (user != null && user.getIsBlocked())
			return;

		// Check if this message is from user himself
		// If true, set orientation to right
		if (viewModel.getUid().equals(currentUser.getUid())) {
			viewModel.setOrientation(Orientation.RIGHT_TO_LEFT);
		}

		// Message custom control
		ChatMessageCustomControl chatMsg = ChatMessageCustomControlManager.getChatMessageCustomControlByType(viewModel);

		FxUIHelper.addElementToParent(chatMessageContainerVBox, chatMsg);
	}

	/**
	 * Add user tag to UI
	 * 
	 * @param viewModel
	 */
	public void addUserTag(UserTagViewModel viewModel) {
		// Add to group members list
		groupMembers.put(viewModel.getUid(), viewModel);

		// user tag
		UserTagCustomControl userTag = new UserTagCustomControl(viewModel);

		FxUIHelper.addElementToParent(userTagContainerVBox, userTag);
	}

	/**
	 * Send text message
	 */
	private void sendTextMessage() {
		// Check if empty
		String msgToSend = messageToSendTextField.getText();
		if (msgToSend.isEmpty()) {
			return;
		}

		// Clear text
		messageToSendTextField.clear();

		// Set focus back to text field
		messageToSendTextField.requestFocus();

		// Send
		GroupTextMessage message = new GroupTextMessage(currentUser.getUid(), msgToSend);
		MessageManager.getMessageExecutionByType(message.getMessageType()).sendMessageToServer(socket, message);
	}

	/**
	 * Click event handler for send button
	 * 
	 * @param event
	 */
	@FXML
	private void sendBtnAction(ActionEvent event) {
		sendTextMessage();
	}

	/**
	 * Key pressed event handler for message typing field
	 * 
	 * @param event
	 */
	@FXML
	private void messageToSendTextFieldKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			sendTextMessage();
		}
	}

	/**
	 * Click event handler for file button
	 * 
	 * @param event
	 */
	@FXML
	private void fileBtnAction(ActionEvent event) {
		// Choose file
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);

		// Send
		if (selectedFile != null) {
			GroupFileMessage message = new GroupFileMessage(currentUser.getUid(), selectedFile.getPath());
			MessageManager.getMessageExecutionByType(message.getMessageType()).sendMessageToServer(socket, message);
		} else {

		}

		// Set focus back to text field
		messageToSendTextField.requestFocus();
	}
}
