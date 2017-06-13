package instantmessage.client.model;

import instantmessage.client.constant.MessageType;
import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.helper.EnumHelper;

public class SetupAddGroupMemberMessage extends Message {
	private String groupMemberUid;

	public SetupAddGroupMemberMessage(String senderUid) {
	super(
			MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, 
			senderUid);
		
	this.setGroupMemberUid(senderUid);
	}

	/**
	 * @return the groupMemberUid
	 */
	public String getGroupMemberUid() {
		return groupMemberUid;
	}

	/**
	 * @param groupMemberUid the groupMemberUid to set
	 */
	public void setGroupMemberUid(String groupMemberUid) {
		this.groupMemberUid = groupMemberUid;
	}


}
