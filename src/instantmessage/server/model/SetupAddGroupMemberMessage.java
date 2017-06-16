package instantmessage.server.model;

import instantmessage.server.constant.MessageTypeCombination;

public class SetupAddGroupMemberMessage extends Message {
	private String groupMemberUid;
	private Boolean isNewMember;

	public SetupAddGroupMemberMessage(String senderUid,Boolean isNewMember) {
		super(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, senderUid);

		this.setGroupMemberUid(senderUid);
		this.setIsNewMember(isNewMember);
	}


	// Getters and setters
	public String getGroupMemberUid() {
		return groupMemberUid;
	}

	public void setGroupMemberUid(String groupMemberUid) {
		this.groupMemberUid = groupMemberUid;
	}

	public Boolean getIsNewMember() {
		return isNewMember;
	}

	public void setIsNewMember(Boolean isNewMember) {
		this.isNewMember = isNewMember;
	}

	@Override
	public String toString() {
		return super.toString()+
				"New Client UID: " + this.getSenderUid();
	}
}
