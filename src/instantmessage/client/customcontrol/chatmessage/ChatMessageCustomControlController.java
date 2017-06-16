package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.constant.Orientation;
import instantmessage.client.customcontrol.ICustomControlController;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;

/**
 * Super class of all chat message custom control controllers
 * 
 * @author Tao Liu
 *
 */
public abstract class ChatMessageCustomControlController implements ICustomControlController {

	/**
	 * Set the orientation of this message
	 * 
	 * @param container
	 *            The container element of this message
	 * @param orientation
	 */
	protected void setOrientation(Node container, Orientation orientation) {
		if (orientation != Orientation.RIGHT_TO_LEFT) {
			return;
		}
		container.getParent().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		container.getStyleClass().add("chat_message_reverse");
	}
}
