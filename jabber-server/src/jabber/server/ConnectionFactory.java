package jabber.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created with IntelliJ IDEA.
 * User: abc
 * Date: 12/27/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionFactory {
       private static Runnable connection = null;

    public static Runnable getConnection(ServerSocket socket) {
        if (connection == null) {
            try {
                connection = new Connection(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return connection;  //To change body of created methods use File | Settings | File Templates.
    }
}
