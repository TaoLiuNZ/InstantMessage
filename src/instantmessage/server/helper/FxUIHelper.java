package instantmessage.server.helper;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxUIHelper {
	

	public static void addElementToParent(Pane parent,Node child){
		
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	parent.getChildren().add(child);
            }
        });
	}
	

}
