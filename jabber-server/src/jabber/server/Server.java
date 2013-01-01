package jabber.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Server {

	private Connection connection;

	public Server() {
		Integer port = Integer.parseInt(Config.getInstance().getOption("port"));
		connection = new Connection(this, port);
	 }

	public void log(String msg) {
		Logger.getLogger(Connection.class.getName()).log(Level.INFO, msg);
	}
}
