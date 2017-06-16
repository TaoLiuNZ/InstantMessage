package instantmessage.server.manager.message;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.constant.MessageTypeCombination;

/**
 * A class to manage sockets and different types of messages
 * 
 * @author Tao Liu
 *
 */
public class MessageManager {

	private static HashMap<Long, IMessageExecution> messageExcutions = new HashMap<Long, IMessageExecution>() {
		{
			put(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, new SetupAddGroupMemberMessageExecution());
			put(MessageTypeCombination.SETUP_DELETE_GROUP_MEMBER_MESSAGE, new SetupDeleteGroupMemberMessageExecution());
			put(MessageTypeCombination.GROUP_FILE_MESSAGE, new GroupFileMessageExecution());
			put(MessageTypeCombination.GROUP_TEXT_MESSAGE, new GroupTextMessageExecution());
		}
	};

	/**
	 * Get message execution by type
	 * 
	 * @param messageType
	 * @return
	 */
	public static IMessageExecution getMessageExcutionByType(long messageType) {
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
			e.printStackTrace();
		}
	}

	/**
	 * Send Boolean
	 * 
	 * @param socket
	 * @param boolean
	 */
	public static void sendBoolean(Socket socket, Boolean value) {
		try {
			getDataOutputStream(socket).writeBoolean(value);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send file
	 * 
	 * @param socket
	 * @param filePath
	 */
	public static void sendFile(Socket socket, String fileName, byte[] fileByteData) {

		try {
			OutputStream os = socket.getOutputStream();

			// Sending file name and file size to the server
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(fileName);
			dos.writeLong(fileByteData.length);
			dos.write(fileByteData, 0, fileByteData.length);
			dos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receive Byte data
	 * 
	 * @param socket
	 * @param fileName
	 * @param fileSize
	 * @return
	 */
	public static byte[] receiveByteData(Socket socket, String fileName, long fileSize) {
		byte[] data = null;

		try {
			int bytesRead;

			DataInputStream clientData = getDataInputStream(socket);

			ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();

			byte[] dataPart = new byte[1024];
			while (fileSize > 0
					&& (bytesRead = clientData.read(dataPart, 0, (int) Math.min(dataPart.length, fileSize))) != -1) {
				bufferStream.write(dataPart, 0, bytesRead);
				fileSize -= bytesRead;
			}

			data = bufferStream.toByteArray();
			bufferStream.close();
		} catch (IOException e) {

		}
		return data;
	}

	/**
	 * Receive long
	 * 
	 * @param socket
	 * @return
	 */
	public static Long receiveLong(Socket socket) {
		try {
			Long value = getDataInputStream(socket).readLong();
			return value;
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
