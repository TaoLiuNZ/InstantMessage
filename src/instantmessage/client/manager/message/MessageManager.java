package instantmessage.client.manager.message;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.client.constant.MessageTypeCombination;
import instantmessage.client.helper.FileHelper;

/**
 * A class to manage sockets and different types of messages
 * 
 * @author Tao Liu
 *
 */
public class MessageManager {

	private static HashMap<Long, IMessageExecution> messageExcutions = new HashMap<Long, IMessageExecution>() {
		{
			put(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, new SetupAddGroupMemberMessageExcution());
			put(MessageTypeCombination.SETUP_DELETE_GROUP_MEMBER_MESSAGE, new SetupDeleteGroupMemberMessageExcution());
			put(MessageTypeCombination.GROUP_FILE_MESSAGE, new GroupFileMessageExecution());
			put(MessageTypeCombination.GROUP_TEXT_MESSAGE, new GroupTextMessageExecution());
		}
	};

	/**
	 * Get message excution by type
	 * 
	 * @param messageType
	 * @return
	 */
	public static IMessageExecution getMessageExecutionByType(long messageType) {
		return messageExcutions.get(messageType);
	}

	/**
	 * Send long
	 * 
	 * @param socket
	 * @param arg
	 */
	public static void sendLong(Socket socket, long arg) {

		try {
			getDataOutputStream(socket).writeLong(arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Send text
	 * 
	 * @param socket
	 * @param text
	 */
	public static void sendText(Socket socket, String text) {
		try {
			getDataOutputStream(socket).writeUTF(text);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Send file
	 * 
	 * @param socket
	 * @param filePath
	 */
	public static void sendFile(Socket socket, String filePath) {
		// File
		File file = new File(filePath);
		byte[] mybytearray = new byte[(int) file.length()];

		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			DataInputStream dis = new DataInputStream(bis);
			dis.readFully(mybytearray, 0, mybytearray.length);
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);

			// Send file name and file size to server
			dos.writeUTF(file.getName());
			dos.writeLong(mybytearray.length);
			// Send file data to server
			dos.write(mybytearray, 0, mybytearray.length);
			dos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receive file
	 * 
	 * @param socket
	 * @param fileName
	 * @param fileSize
	 * @return
	 */
	public static String receiveFile(Socket socket, String fileName, long fileSize,String...folderNames) {
		String localFilePath = FileHelper.getPath(fileName,folderNames);

		try {
			int bytesRead;
			DataInputStream clientData = getDataInputStream(socket);
			OutputStream output = new FileOutputStream(localFilePath);
			byte[] buffer = new byte[1024];

			// Read data and save it to file
			while (fileSize > 0
					&& (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, fileSize))) != -1) {
				output.write(buffer, 0, bytesRead);
				fileSize -= bytesRead;
			}
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return localFilePath;
	}

	/**
	 * Receive long
	 * 
	 * @param socket
	 * @return
	 */
	public static Long receiveLong(Socket socket) {
		try {
			return getDataInputStream(socket).readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Receive Boolean
	 * 
	 * @param socket
	 * @return
	 */
	public static Boolean receiveBoolean(Socket socket) {
		try {
			return getDataInputStream(socket).readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Receive text
	 * 
	 * @param socket
	 * @return
	 */
	public static String receiveText(Socket socket) {
		try {
			return getDataInputStream(socket).readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get DataInputStream from socket
	 * 
	 * @param socket
	 * @return
	 */
	private static DataInputStream getDataInputStream(Socket socket) {
		try {
			return new DataInputStream(socket.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get DataOutputStream from socket
	 * 
	 * @param socket
	 * @return
	 */
	private static DataOutputStream getDataOutputStream(Socket socket) {
		try {
			return new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
