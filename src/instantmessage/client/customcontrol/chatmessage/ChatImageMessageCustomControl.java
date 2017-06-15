package instantmessage.client.customcontrol.chatmessage;

import instantmessage.client.viewmodel.IViewModel;

public class ChatImageMessageCustomControl extends ChatFileMessageCustomControl {

	/**
	 * A sub class of ChatMessageCustomControl
	 * 
	 * @author Tao Liu
	 *
	 */
	public ChatImageMessageCustomControl(IViewModel viewModel) {
		super(viewModel);
	}

	@Override
	protected String setFXMLPath() {
		return "ChatImageMessageCustomControl.fxml";
	}

}
