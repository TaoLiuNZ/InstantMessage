package instantmessage.server.model;

import instantmessage.client.constant.MessageTypeCombination;

public class GroupTextMessage extends Message {
	private String msg;

	public GroupTextMessage(String senderUid, String msg) {
		super(MessageTypeCombination.GROUP_TEXT_MESSAGE, senderUid);

		this.setMsg(msg);
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return 
				super.toString()+
				"Message: "	+ this.getMsg();
	}

}
