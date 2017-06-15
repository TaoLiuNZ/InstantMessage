package instantmessage.client.customcontrol;

import java.io.IOException;

import instantmessage.client.viewmodel.IViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/**
 * Super class for all custom controls, Manage to load UI and set view data
 * 
 * @author Tao Liu
 *
 */
public abstract class CustomControl extends GridPane {
	protected FXMLLoader fxmlLoader;
	protected IViewModel viewModel;

	public CustomControl(IViewModel viewModel) {
		loadUI(setFXMLPath());
		setViewData(viewModel);
	}

	/**
	 * Set the path of FXML file
	 * 
	 * @return the path of FXML file
	 */
	protected abstract String setFXMLPath();

	/**
	 * Load UI
	 * 
	 * @param fxmlPath
	 */
	protected void loadUI(String fxmlPath) {
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

	/**
	 * Get the controller of this custom control
	 * 
	 * @return the controller of this custom control
	 */
	protected ICustomControlController getController() {
		return (ICustomControlController) fxmlLoader.getController();
	}

}
