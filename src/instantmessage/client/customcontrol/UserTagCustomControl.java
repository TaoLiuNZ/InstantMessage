package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.viewmodel.IViewModel;
import instantmessage.client.viewmodel.UserTagViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public class UserTagCustomControl extends CustomControl{
	private FXMLLoader fxmlLoader;
	private UserTagViewModel userTagViewModel;
	
	 public UserTagCustomControl(UserTagViewModel userTagViewModel) {
		 // Load UI
	        loadUI("UserTagCustomControl.fxml");
	        
	        // Set UI info
	        setUIInfo(userTagViewModel);
	    }
	 

		@Override
		protected void setUIInfo(IViewModel viewModel) {
			getController().setUIData((UserTagViewModel) userTagViewModel);
			
		}


		@Override
		protected ICustomControlController getController() {
			return (UserTagCustomControlController)fxmlLoader.getController();
		}


	 
}
