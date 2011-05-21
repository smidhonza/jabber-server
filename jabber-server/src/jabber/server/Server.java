/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jabber.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Server implements Runnable {

	@Override
	public void run() {
		try {
			Integer port = Integer.parseInt(Config.getInstance().getOption("port"));
			
			ServerSocket socket = new ServerSocket(port);
			while (true) {
				new Thread(new Connection(socket.accept())).start();
			}
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
