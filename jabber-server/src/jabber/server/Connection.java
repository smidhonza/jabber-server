/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jabber.server;

import java.net.Socket;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Connection implements Runnable {

	private Socket connection;
	
	public Connection(Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		System.out.println("Connection accepted from address " + connection.getInetAddress() + " on port " + connection.getPort());
	}
}
