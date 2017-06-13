package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public abstract class CustomControl extends GridPane{
	protected  FXMLLoader fxmlLoader;

	 protected abstract void setUIInfo(IViewModel viewModel);
	 
	 protected void loadUI(String fxmlPath){
		 fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
	        fxmlLoader.setRoot(this);
	        
	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
	 }
	 
	 protected abstract ICustomControlController getController();
}
