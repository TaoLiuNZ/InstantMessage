package instantmessage.client.model;

import instantmessage.client.constant.MessageType;
import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.helper.EnumHelper;

public class GroupTextMessage extends Message {
	private String msg;

	public GroupTextMessage(String senderUid,String msg) {
	super(
			MessageTypeCombination.GROUP_TEXT_MESSAGE, 
			senderUid);
		
	this.setMsg(msg);
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
