package instantmessage.server.model;

import instantmessage.client.constant.MessageTypeCombination;

/**
 * GroupTextMessage model
 * 
 * @author Tao Liu
 *
 */
public class GroupTextMessage extends Message {

	// Fields
	private String msg;

	// Constructor
	public GroupTextMessage(String senderUid, String msg) {
		super(MessageTypeCombination.GROUP_TEXT_MESSAGE, senderUid);

		this.setMsg(msg);
	}

	// Getters and setters

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return super.toString() + "Message: " + this.getMsg();
	}

}
