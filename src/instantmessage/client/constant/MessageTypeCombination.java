package instantmessage.client.constant;

import instantmessage.client.helper.EnumHelper;

public class MessageTypeCombination {
	
	public static final long GROUP_TEXT_MESSAGE = 
			EnumHelper.encode(MessageType.GROUP_MESSAGE,MessageType.TEXT_MESSAGE);
	
	public static final long SETUP_ADD_GROUP_MEMBER_MESSAGE = 
			EnumHelper.encode(MessageType.SETUP_MESSAGE,MessageType.ADD_GROUP_MEMBER_MESSAGE);
	
}
