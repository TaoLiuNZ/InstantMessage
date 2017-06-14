package instantmessage.client.model;

import instantmessage.client.constant.MessageType;
import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.helper.EnumHelper;

public class GroupFileMessage extends Message {
	private String filePath;

	public GroupFileMessage(String senderUid,String filePath) {
	super(
			MessageTypeCombination.GROUP_FILE_MESSAGE, 
			senderUid);
		
	this.setFilePath(filePath);
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param msg the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


}
