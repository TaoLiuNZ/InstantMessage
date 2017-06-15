package instantmessage.server.manager;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import instantmessage.server.constant.MessageTypeCombination;

public class MessageManager {

	private static HashMap<Long, IMessageExcution> messageExcutions = new HashMap<Long, IMessageExcution>() {
		{
			put(MessageTypeCombination.SETUP_ADD_GROUP_MEMBER_MESSAGE, new SetupAddGroupMemberMessageExcution());
			put(MessageTypeCombination.GROUP_FILE_MESSAGE, new GroupFileMessageExcution());
			put(MessageTypeCombination.GROUP_TEXT_MESSAGE, new GroupTextMessageExcution());
		}
	};

	public static IMessageExcution getMessageExcutionByType(long messageType) {
		return messageExcutions.get(messageType);
	}

	public static void sendLong(Socket socket, long arg) {

		try {
			getDataOutputStream(socket).writeLong(arg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendText(Socket socket, String text) {
		try {
			getDataOutputStream(socket).writeUTF(text);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public static Long receiveLong(Socket socket) {
		try {
			Long value = getDataInputStream(socket).readLong();
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String receiveText(Socket socket) {
		try {
			return getDataInputStream(socket).readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static DataInputStream getDataInputStream(Socket socket) {
		try {
			return new DataInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static DataOutputStream getDataOutputStream(Socket socket) {
		try {
			return new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
