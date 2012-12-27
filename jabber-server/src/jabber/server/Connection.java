/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jabber.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Connection implements Runnable {

	private Socket connection;
	private OutputStreamWriter outputStreamWriter = null;
	private InputStreamReader inputStreamReader = null;
	
	public Connection(Socket connection) {
		this.connection = connection;
	}

    @Override
    public void run() {
        try {
            this.sendMessage(
                    "Connected to jabber server from ip adress: " + this.connection.getInetAddress() +
                            " and port " + this.connection.getPort()
            );
            this.sendMessage("You will close connection by typing BYE");
            String message;


            XMLRequest request;
            while (!(request = XMLRequestParser.parse(this.receiveMessage())).isCloseRequest()) {
                this.sendMessage(request.getMessage());
            }
            Logger.getLogger(Connection.class.getName()).log(Level.INFO, "zmrde");
            this.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
	 * Send message to client
	 *
	 * @param message
	 * @throws IOException 
	 */
	private void sendMessage(String message) throws IOException {
		message += "\r\n";
		this.getBufferedOutputStream().write(message);
		this.getBufferedOutputStream().flush();
	}
	
	private String receiveMessage() throws IOException {
        Logger.getLogger(Connection.class.getName()).log(Level.INFO, "receiveMessage()");
		int b;
		StringBuilder message = new StringBuilder();
		boolean waitForCR = true;
		boolean waitForLF = false;
		while((b = this.getInputStreamReader().read()) != -1) {
			message.append((char)b);
			if (waitForCR && (char)b == '\r') {
				waitForLF = true;
			}
			if (waitForLF && (char)b == '\n') {
				break;
			} else if ((char)b == '\n' && !waitForLF) {
				waitForCR = true;
				waitForLF = false;
			}
		}
		return message.toString();
	}
	
	private void close() throws IOException {
		this.connection.close();
	}

    private OutputStreamWriter getBufferedOutputStream() throws IOException {
        if (this.outputStreamWriter == null) {
            this.outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(this.connection.getOutputStream()));
        }
        return this.outputStreamWriter;
    }

    private InputStreamReader getInputStreamReader() throws IOException {
        if (this.inputStreamReader == null) {
            this.inputStreamReader = new InputStreamReader(new BufferedInputStream(this.connection.getInputStream()), "UTF8");
        }
        return this.inputStreamReader;
    }
}
