package instantmessage.client.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.model.SetupAddGroupMemberMessage;

public class MessageManager {
/*	private static Socket socket;
	public MessageManager(){}
	public MessageManager(Socket socket){
		this.socket=socket;
	}*/
	
	private static HashMap<Long,IMessageExcution> messageExcutions=new HashMap<Long,IMessageExcution>(){{
		put(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE,new SetupAddGroupMemberMessageExcution());
		put(MessageTypeCombination.GROUP_TEXT_MESSAGE,new GroupTextMessageExcution());
	}};
	
	public static IMessageExcution getMessageExcutionByType(long messageType){
		return messageExcutions.get(messageType);
	}
	
	public static void sendLong(Socket socket,long arg){
		
		try {
			getDataOutputStream(socket).writeLong(arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void sendText(Socket socket,String text){
		try {
			getDataOutputStream(socket).writeUTF(text);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Long receiveLong(Socket socket){
		try {
			return getDataInputStream(socket).readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String receiveText(Socket socket){
		try {
			return getDataInputStream(socket).readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static DataInputStream getDataInputStream(Socket socket){
		try {
			return new DataInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static DataOutputStream getDataOutputStream(Socket socket){
		try {
			return new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
