package instantmessage.client.model;

import instantmessage.client.constant.MessageTypeCombination;

/**
 * SetupAddGroupMemberMessage model
 * 
 * @author Tao Liu
 *
 */
public class SetupAddGroupMemberMessage extends Message {

	// Private fields
	private String groupMemberUid;

	// Constructor
	public SetupAddGroupMemberMessage(String senderUid) {
		super(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, senderUid);
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
