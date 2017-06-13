package instantmessage.client.model;

public class Message {
	
private long messageType;
private String senderUid;

public Message(long messageType,String senderUid){
	this.setMessageType(messageType);
	this.setSenderUid(senderUid);
	
}

/**
 * @return the messageType
 */
public long getMessageType() {
	return messageType;
}

/**
 * @param messageType the messageType to set
 */
public void setMessageType(long messageType) {
	this.messageType = messageType;
}

/**
 * @return the senderUid
 */
public String getSenderUid() {
	return senderUid;
}

/**
 * @param senderUid the senderUid to set
 */
public void setSenderUid(String senderUid) {
	this.senderUid = senderUid;
}
}
