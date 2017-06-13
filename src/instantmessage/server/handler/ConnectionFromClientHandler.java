package instantmessage.server.handler;

import java.net.Socket;
import java.util.HashMap;

public class ConnectionFromClientHandler extends Thread{
private Socket socket;
	
public ConnectionFromClientHandler(Socket socket)
{
    this.socket = socket;
    
    System.out.println("New client connected"+socket.getRemoteSocketAddress().toString());


}

public void run()
{
    System.out.println("Client connected");
    
    String clientName = null;
    
    while(true)
    {
        // Handle message
    	
    	// 
}
}
}
