package instantmessage.client.viewmodel;

import instantmessage.client.constant.ChatMessageViewModelType;
import instantmessage.client.constant.Orientation;
import instantmessage.client.model.User;

public class ChatImageMessageViewModel extends ChatMessageViewModel{
	private String imageUrl;
	
	public ChatImageMessageViewModel(String uid,String picUrl,String displayName,String imageUrl){
		super(uid,picUrl,displayName);
		this.setImageUrl(imageUrl);
	}
	
	public ChatImageMessageViewModel(User user,String imageUrl){
		this(user.getUid(),user.getPicUrl(),user.getDisplayName(),imageUrl);
	}
	
	@Override
	public ChatMessageViewModelType setViewModelType() {
		return ChatMessageViewModelType.CHAT_IMAGE_MESSAGE;
	}
	
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



}
