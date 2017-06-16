package instantmessage.server.model;

import instantmessage.server.constant.MessageTypeCombination;

/**
 * SetupDeleteGroupMemberMessage model
 * 
 * @author Tao Liu
 *
 */
public class SetupDeleteGroupMemberMessage extends Message {

	// Private fields
	private String groupMemberUid;

	// Constructor
	public SetupDeleteGroupMemberMessage(String senderUid) {
		super(MessageTypeCombination.SETUP_DELETE_GROUP_MEMBER_MESSAGE, senderUid);
		this.setGroupMemberUid(senderUid);
	}

	// Getters and setters

	public String getGroupMemberUid() {
		return groupMemberUid;
	}

	public void setGroupMemberUid(String groupMemberUid) {
		this.groupMemberUid = groupMemberUid;
	}

}
