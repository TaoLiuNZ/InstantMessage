package instantmessage.client.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.activation.FileTypeMap;

/**
 * This class is used to manage files
 * 
 * @author Tao Liu
 *
 */
public class FileHelper {

	/**
	 * This method is used to get the file path of ReceivedFile folder
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getReceivedFilePath(String fileName) {
		return getPath("FileReceived", fileName);
	}

	/**
	 * This method is used to check whether a file is an image file
	 * 
	 * @param filePath
	 * @return
	 */
	public static Boolean isImage(String filePath) {

		// First , check the mime type
		File file = new File(filePath);
		String mimetype = FileTypeMap.getDefaultFileTypeMap().getContentType(file);
		String type = mimetype.split("/")[0];
		if (type.equals("image")) {
			return true;
		} else {
			// mimetype cannot check png file, so we use Regular Expression to
			// check again
			if (isImageByExtension(filePath))
				return true;
			else
				return false;
		}
	}

	/**
	 * This method is used to check whether a file is an image file by its
	 * extension
	 * 
	 * @param filePath
	 * @return
	 */
	private static Boolean isImageByExtension(String filePath) {
		return Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)").matcher(filePath).matches();
	}

	/**
	 * This method is used to generate a file path according to folder name and
	 * file name
	 * 
	 * @param folderName
	 * @param fileName
	 * @return
	 */
	private static String getPath(String folderName, String fileName) {
		File dir = new File(System.getProperty("user.dir") + File.separatorChar + folderName);

		// Check if exists
		if (!dir.exists()) {
			try {
				Files.createDirectories(Paths.get(dir.getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dir.getName() + File.separatorChar + System.currentTimeMillis() + fileName;
	}
}
