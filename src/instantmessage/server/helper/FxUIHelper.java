package instantmessage.server.helper;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * This class is used to manage Fxml elements
 * 
 * @author Tao Liu
 *
 */
public class FxUIHelper {

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

}
