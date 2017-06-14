package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.Orientation;
import instantmessage.client.model.User;

public class ChatTextMessageViewModel extends ChatMessageViewModel{
	private String msg;
	
	public ChatTextMessageViewModel(String uid,String picUrl,String displayName,String msg){
		super(uid,picUrl,displayName);
		this.setMsg(msg);
	}
	
	public ChatTextMessageViewModel(User user,String msg){
		this(user.getUid(),user.getPicUrl(),user.getDisplayName(),msg);
	}
	
	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_TEXT_MESSAGE;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
