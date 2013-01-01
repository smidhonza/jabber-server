package jabber.server;

import java.io.*;
import java.net.*;

/**
*	Description of Client
*	@author smidhonza
*/
public class Client extends Thread {

	public Socket socket;
	public Connection connection;
	public BufferedReader in;
	public PrintWriter out;

	public Client(Socket socket, Connection connection) {
		this.socket = socket;
		this.connection = connection;
		connection.clientCount++;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Welcome to " + InetAddress.getLocalHost().getHostName());
			out.println("You can log out by typing BYE");

		} catch (Exception e) {
			//Ignored
		}
		this.start();
	}

	// Sends a message to the client object
	public void sendMessage(String msg) {
		try {
			out.println(msg);
		} catch (Exception e) {
			connection.killClient(this);
		}
	}

	// Receives messages
	@Override
	public void run() {
		String inStream;
		try {
			XMLRequest request;
			while ((inStream = in.readLine()) != null && !(request = XMLRequestParser.parse(inStream)).isCloseRequest()) {
				//TODO
				if (inStream.equals("STATUS")) {
					String msg = "Status:";
					msg += "\r\nServer hostname : \t" + InetAddress.getLocalHost().getHostName();
					msg += "\r\nTotal clients : \t" + connection.clientCount;
					out.println(msg);
				} else {
					connection.broadcast(socket.getInetAddress() + ": " + inStream);
				}
			}
			out.close();
			in.close();
			socket.close();
		} catch (Exception e) {
		}
		connection.killClient(this);
	}
}
