package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.constant.Orientation;
import instantmessage.client.viewmodel.ChatMessageViewModel;
import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class ChatMessageCustomControl extends CustomControl{
	
	 public ChatMessageCustomControl(ChatMessageViewModel viewModel,Orientation orientation) {
		 // Load UI
	        loadUI("ChatMessageCustomControl.fxml");
	        
	        // Set UI info
	        setUIInfo(viewModel);
	        ((ChatMessageCustomControlController)getController()).setOrientation(orientation);
	    }
	 

		@Override
		protected void setUIInfo(IViewModel viewModel) {
			getController().setUIData((ChatMessageViewModel) viewModel);			
		}


		@Override
		protected ICustomControlController getController() {
			
			return (ChatMessageCustomControlController)fxmlLoader.getController();
		}

	 
}
