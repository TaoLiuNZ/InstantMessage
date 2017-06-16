package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.viewmodel.IViewModel;

/**
 * A sub class of ChatMessageCustomControl
 * 
 * @author Tao Liu
 *
 */
public class ChatSetupMessageCustomControl extends ChatMessageCustomControl {

	/**
	 * ChatTextMessageCustomControl; A sub class of ChatMessageCustomControl
	 * 
	 * @author Tao Liu
	 *
	 */
	public ChatSetupMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatSetupMessageCustomControl.fxml";
	}

}
