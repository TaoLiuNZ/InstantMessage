package instantmessage.client.manager;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.helper.FileHelper;
import instantmessage.client.model.SetupAddGroupMemberMessage;

public class MessageManager {

	private static HashMap<Long,IMessageExcution> messageExcutions=new HashMap<Long,IMessageExcution>(){{
		put(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE,new SetupAddGroupMemberMessageExcution());
		put(MessageTypeCombination.GROUP_FILE_MESSAGE,new GroupFileMessageExcution());
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
	
	public static void sendFile(Socket socket,String filePath){
		// File
		File file = new File(filePath);
        byte[] mybytearray = new byte[(int) file.length()];

		try {
			FileInputStream fis = new FileInputStream(file);
	        BufferedInputStream bis = new BufferedInputStream(fis);	         
	        DataInputStream dis = new DataInputStream(bis);   
	        dis.readFully(mybytearray, 0, mybytearray.length);
	         
	        OutputStream os = socket.getOutputStream();
	         
	        //Sending file name and file size to the server
	        DataOutputStream dos = new DataOutputStream(os);   
	        dos.writeUTF(file.getName());   
	        dos.writeLong(mybytearray.length);   
	        dos.write(mybytearray, 0, mybytearray.length);   
	        dos.flush();
	         
	   
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String receiveFile(Socket socket,String fileName,long fileSize){
		 String localFilePath=FileHelper.getReceivedFilePath(fileName);

		try{		int bytesRead;
		        

		        DataInputStream clientData = getDataInputStream(socket);
		         
 
		        OutputStream output = new FileOutputStream(localFilePath);   
 
		        byte[] buffer = new byte[1024];   
		        while (fileSize > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, fileSize))) != -1)   
		        {   
		            output.write(buffer, 0, bytesRead);   
		            fileSize -= bytesRead;   
		        }
		        output.close();

		         }
		        catch(IOException e){
		        	e.printStackTrace();
		        }
		return localFilePath;
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
