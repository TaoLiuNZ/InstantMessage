package instantmessage;

import instantmessage.client.helper.FxUIHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	@FXML private Button startServerBtn;
	@FXML private Button addAClientBtn;
	
	@FXML
	private void startServerBtnAction(ActionEvent event){

		FxUIHelper.openNewWindow(this,"ServerUI.fxml");
	}
	@FXML
	private void addAClientBtnAction(ActionEvent event){
		FxUIHelper.openNewWindow(this,"LoginUI.fxml");
	}
	
	
	
}
