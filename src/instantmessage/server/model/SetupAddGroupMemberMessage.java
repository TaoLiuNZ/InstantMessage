package instantmessage.server.model;


import instantmessage.server.constant.MessageTypeCombination;


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

	@Override
	public String toString(){
		return 
				"Type:"+this.getMessageType()+","+
				"New Client UID:"+this.getSenderUid();
	}
}
