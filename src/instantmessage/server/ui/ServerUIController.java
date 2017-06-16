package instantmessage.server.ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

import instantmessage.server.handler.ListeningClientConnectionHandler;
import instantmessage.server.helper.FxUIHelper;
import instantmessage.server.manager.ClientConnectionManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller for ServerUI
 * 
 * @author Tao Liu
 *
 */
public class ServerUIController implements Initializable {

	// Fields
	@FXML
	private TextFlow textFlow;
	@FXML
	private ScrollPane scrollPane;

	/**
	 * Add text to server UI
	 * 
	 * @param str
	 */
	public void addTextToTextFlow(String str) {
		FxUIHelper.addElementToParent(textFlow, new Text(str + "\n"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startListeningClientConnection();
		setListeners();
	}

	/**
	 * Start listening client connections
	 */
	private void startListeningClientConnection() {
		try {
			// Start server socket
			ServerSocket server = new ServerSocket(5000);

			// Start listening
			ClientConnectionManager clientManager = ClientConnectionManager.getInstance(this);
			ListeningClientConnectionHandler listeningClientConnectionHandler = new ListeningClientConnectionHandler(
					server, clientManager);
			listeningClientConnectionHandler.start();

			// Display
			addTextToTextFlow("Server setup: waiting for client connections");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set listeners for some UI elements
	 */
	private void setListeners() {

		// Make sure the scroll pane always scroll down to the bottom
		textFlow.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				scrollPane.setVvalue(1.0);
			}
		});
	}
}
