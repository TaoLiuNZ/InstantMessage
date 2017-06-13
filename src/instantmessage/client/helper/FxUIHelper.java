package instantmessage.client.helper;

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
            	imageView.setImage(new Image(new File("image/"+picUrl).toURI().toString()));
            }
        });
	}
	
	public static void setVisible(Node node,Boolean visible){
		node.setVisible(visible);
	}
	
	public static void addElementToParent(Pane parent,Node child){
		
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	parent.getChildren().add(child);
            }
        });
	}
	
	public static void openNewWindow(Object obj,String fxmlPath){
		showWindow(obj,new Stage(),fxmlPath);
	}
	
	public static FXMLLoader switchScene(Object obj,Node node,String fxmlPath){
		Stage stage=(Stage)node.getScene().getWindow();				
		return showWindow(obj,stage,fxmlPath);
	}
	
	
	private static FXMLLoader showWindow(Object object,Stage stage,String fxmlPath){

		FXMLLoader fxmlLoader = new FXMLLoader(object.getClass().getResource(fxmlPath));
		Parent root = null;
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return fxmlLoader;
	}
}
