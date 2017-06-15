package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.viewmodel.IViewModel;

/**
 * A sub class of ChatMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatTextMessageCustomControl extends ChatMessageCustomControl {

	/**
	 * ChatTextMessageCustomControl; A sub class of ChatMessageCustomControl
	 * 
	 * @author Tao Liu
	 *
	 */
	public ChatTextMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatTextMessageCustomControl.fxml";
	}

}
