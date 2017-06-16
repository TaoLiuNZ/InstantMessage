package instantmessage.client.model;

import instantmessage.client.constant.MessageTypeCombination;

/**
 * SetupDeleteGroupMemberMessage model
 * 
 * @author Tao Liu
 *
 */
public class SetupDeleteGroupMemberMessage extends Message {

	// Private fields
	private String groupMemberUid;
	private String connectionId;

	// Constructor
	public SetupDeleteGroupMemberMessage(String senderUid,String connectionId) {
		super(MessageTypeCombination.SETUP_DELETE_GROUP_MEMBER_MESSAGE, senderUid);
		this.setGroupMemberUid(senderUid);
		this.setConnectionId(connectionId);
	}

	// Getters and setters

	public String getGroupMemberUid() {
		return groupMemberUid;
	}

	public void setGroupMemberUid(String groupMemberUid) {
		this.groupMemberUid = groupMemberUid;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

}
