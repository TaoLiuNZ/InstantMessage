package instantmessage.server.model;

import instantmessage.server.constant.MessageTypeCombination;

public class GroupFileMessage extends Message {
	private String fileName;
	private byte[] fileByteData;

	public GroupFileMessage(String senderUid, String fileName, byte[] fileByteData) {
		super(MessageTypeCombination.GROUP_FILE_MESSAGE, senderUid);
		this.setFileName(fileName);
		this.setFileByteData(fileByteData);
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileByteData
	 */
	public byte[] getFileByteData() {
		return fileByteData;
	}

	/**
	 * @param fileByteData
	 *            the fileByteData to set
	 */
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
