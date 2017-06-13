package instantmessage.client.helper;

import java.io.File;

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
	
	public static void setText(Labeled label,String text){
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	label.setText(text);
            }
        });
	}
	
	public static void setImage(ImageView imageView,String picUrl){
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	imageView.setImage(new Image(new File(picUrl).toURI().toString()));
            }
        });
	}
	
	public static void addElementToParent(Pane parent,Node child){
		
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	parent.getChildren().add(child);
            }
        });
	}
	
	public static void openNewWindow(Object obj,String fxmlPath){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(obj.getClass().getResource(fxmlPath));
			Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
