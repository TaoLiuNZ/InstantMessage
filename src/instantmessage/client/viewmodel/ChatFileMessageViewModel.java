package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.Orientation;
import instantmessage.client.model.User;

public class ChatFileMessageViewModel extends ChatMessageViewModel{
	private String fileName;
	private String clickLink;
	
	public ChatFileMessageViewModel(String uid,String picUrl,String displayName,String fileName,String clickLink){
		super(uid,picUrl,displayName);
		this.setFileName(fileName);
		this.setClickLink(clickLink);
	}
	
	public ChatFileMessageViewModel(User user,String fileName,String clickLink){
		this(user.getUid(),user.getPicUrl(),user.getDisplayName(),fileName,clickLink);
	}

	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_FILE_MESSAGE;
	}
	/**
	 * @return the clickLink
	 */
	public String getClickLink() {
		return clickLink;
	}

	/**
	 * @param clickLink the clickLink to set
	 */
	public void setClickLink(String clickLink) {
		this.clickLink = clickLink;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



}
