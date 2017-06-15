package instantmessage.client.model;

import instantmessage.client.constant.MessageTypeCombination;

/**
 * GroupFileMessage model
 * @author Tao Liu
 *
 */
public class GroupFileMessage extends Message {
	
	// private fields
	private String filePath;

	// Constructor
	public GroupFileMessage(String senderUid, String filePath) {
		super(MessageTypeCombination.GROUP_FILE_MESSAGE, senderUid);
		this.setFilePath(filePath);
	}
	
	// Getters and setters
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
