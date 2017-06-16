package instantmessage.server.model;

import instantmessage.server.constant.MessageTypeCombination;


/**
 * GroupFileMessage model
 * @author Tao Liu
 *
 */
public class GroupFileMessage extends Message {
	
	// Fields
	private String fileName;
	private byte[] fileByteData;

	// Constructor
	public GroupFileMessage(String senderUid, String fileName, byte[] fileByteData) {
		super(MessageTypeCombination.GROUP_FILE_MESSAGE, senderUid);
		this.setFileName(fileName);
		this.setFileByteData(fileByteData);
	}

	// Getters and setters

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileByteData() {
		return fileByteData;
	}

	public void setFileByteData(byte[] fileByteData) {
		this.fileByteData = fileByteData;
	}
	
	@Override
	public String toString(){
	return
		super.toString()+ 
		"File Name: "	+ this.getFileName();
	}
	
}
