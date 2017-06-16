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

/**
 * This class is used to manage Fxml elements
 * 
 * @author Tao Liu
 *
 */
public class FxUIHelper {

	/**
	 * Set the text of a labeled control
	 * 
	 * @param label
	 * @param text
	 */
	public static void setText(Labeled label, String text) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				label.setText(text);
			}
		});
	}

	/**
	 * Set the image of an ImageView control
	 * 
	 * @param imageView
	 * @param picUrl
	 */
	public static void setImage(ImageView imageView, String picUrl) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				imageView.setImage(new Image(new File(picUrl).toURI().toString()));
			}
		});
	}

	/**
	 * Set the avatar image
	 * 
	 * @param imageView
	 * @param picUrl
	 */
	public static void setAvatarImage(ImageView imageView, String picUrl) {
		setImage(imageView, "image/" + picUrl);
	}

	/**
	 * Set visibility of a control
	 * 
	 * @param node
	 * @param visible
	 */
	public static void setVisible(Node node, Boolean visible) {
		node.setVisible(visible);
	}

	/**
	 * Add a child control
	 * 
	 * @param parent
	 * @param child
	 */
	public static void addElementToParent(Pane parent, Node child) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				parent.getChildren().add(child);
			}
		});
	}

	/**
	 * Remove a child control from parents
	 * 
	 * @param parent
	 * @param child
	 */
	public static void removeElementFromParent(Pane parent, Node child) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				parent.getChildren().remove(child);
			}
		});
	}

	/**
	 * Open a new window
	 * 
	 * @param obj
	 * @param fxmlPath
	 */
	public static void openNewWindow(Object obj, String fxmlPath) {
		showWindow(obj, new Stage(), fxmlPath);
	}

	/**
	 * Switch to another scene
	 * 
	 * @param obj
	 * @param node
	 * @param fxmlPath
	 * @return
	 */
	public static FXMLLoader switchScene(Object obj, Node node, String fxmlPath) {
		Stage stage = (Stage) node.getScene().getWindow();
		return showWindow(obj, stage, fxmlPath);
	}

	/**
	 * Show a window
	 * 
	 * @param object
	 * @param stage
	 * @param fxmlPath
	 * @return
	 */
	private static FXMLLoader showWindow(Object object, Stage stage, String fxmlPath) {

		FXMLLoader fxmlLoader = new FXMLLoader(object.getClass().getResource(fxmlPath));
		Parent root = null;
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return fxmlLoader;
	}
}
