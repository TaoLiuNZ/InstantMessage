package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

public abstract class CustomControl extends GridPane {
	protected  FXMLLoader fxmlLoader;
	protected IViewModel viewModel;
	
	public CustomControl() {
		loadUI(setFXMLPath());
	}
	public CustomControl(IViewModel viewModel){
		loadUI(setFXMLPath());
		setViewData(viewModel);
	}
	
	protected abstract String setFXMLPath();

	 protected void loadUI(String fxmlPath){
		 fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
	        fxmlLoader.setRoot(this);
	        
	        try {
	            fxmlLoader.load();
	        } catch (IOException exception) {
	            throw new RuntimeException(exception);
	        }
	 }
		public void setViewData(IViewModel viewModel) {
			getController().setViewData(viewModel);
			
		}
		
		protected ICustomControlController getController() {
			return (ICustomControlController)fxmlLoader.getController();
		}

}
