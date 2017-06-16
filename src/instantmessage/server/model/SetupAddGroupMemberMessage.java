package instantmessage.server.model;

import instantmessage.server.constant.MessageTypeCombination;

/**
 * SetupAddGroupMemberMessage model
 * 
 * @author Tao Liu
 *
 */
public class SetupAddGroupMemberMessage extends Message {

	// Fields
	private String groupMemberUid;
	private String ipAddress;
	private Boolean isNewMember;

	// Constructor
	public SetupAddGroupMemberMessage(String senderUid,String ipAddress, Boolean isNewMember) {
		super(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, senderUid);

		this.setGroupMemberUid(senderUid);
		this.setIsNewMember(isNewMember);
		this.setIpAddress(ipAddress);
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
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return super.toString() + "New Client UID: " + this.getSenderUid();
	}
}
