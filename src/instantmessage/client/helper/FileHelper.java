package instantmessage.client.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

public class FileHelper {

	public static String getReceivedFilePath(String fileName){
		return getPath("FileReceived",fileName);
	}
	
	public static Boolean isImage(String filePath){
		File f = new File(filePath);
        String mimetype= FileTypeMap.getDefaultFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        if(type.equals("image")){
            return true;
        }
        else{ 
        	// mimetype cannot check png file, so we use Regular Expression to check again
        	if(isImageByExtension(filePath))
        		return true;
        	else
        	return false;
	}}
	
	private static Boolean isImageByExtension(String filePath){
		return Pattern
				.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)")
				.matcher(filePath)
				.matches();
	}
	
	private static String getPath(String folderName,String fileName){
		File dir=new File(System.getProperty("user.dir")+File.separatorChar+folderName);
		
		// Check if exists
		if(!dir.exists()){
		try {
			Files.createDirectories(Paths.get(dir.getName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return dir.getName()+File.separatorChar+System.currentTimeMillis()+fileName;
	}
}
