package instantmessage.server.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import instantmessage.server.manager.ClientConnectionManager;

/**
 * Listen to client connections
 * 
 * @author Tao Liu
 *
 */
public class ListeningClientConnectionHandler extends Thread {

	// Fields
	private ServerSocket serverSocket;
	private ClientConnectionManager clientManager;

	// Constructor
	public ListeningClientConnectionHandler(ServerSocket socket, ClientConnectionManager clientManager) {
		this.serverSocket = socket;
		this.clientManager = clientManager;
	}

	@Override
	public void run() {

		// Keep running
		while (true) {

			try {
				// keep accepting client connections
				Socket socket = serverSocket.accept();

				// Start client connection handler thread to deal
				// with messages from different clients respectively
				clientManager.startNewClientHandler(socket);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
