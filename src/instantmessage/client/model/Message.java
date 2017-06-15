package instantmessage.client.model;

/**
 * Super class for all message models
 * 
 * @author Tao Liu
 *
 */
public class Message {

	// Private fields
	private long messageType;
	private String senderUid;

	// Constructor
	public Message(long messageType, String senderUid) {
		this.setMessageType(messageType);
		this.setSenderUid(senderUid);
	}

	// Getters and setters
	public long getMessageType() {
		return messageType;
	}

	public void setMessageType(long messageType) {
		this.messageType = messageType;
	}

	public String getSenderUid() {
		return senderUid;
	}

	public void setSenderUid(String senderUid) {
		this.senderUid = senderUid;
	}
}
