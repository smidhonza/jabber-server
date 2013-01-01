package jabber.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection implements Runnable {

	private ArrayList clients;
	private Thread thread;
	public ServerSocket serverSocket;
	public Server server;
	public int clientCount;
	public int port;

	public Connection(Server server, int port) {
		this.server = server;
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
			server.log("Server starts on " + InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			System.err.println("Can't make a socketconnection " + e);
			System.exit(0);
		}
		clientCount = 0;
		clients = new ArrayList();
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				broadcast(socket.getInetAddress() + " is connected");
				clients.add(new Client(socket, this));
			} catch (Exception e) {
			}
		}
	}

	public synchronized void broadcast(String msg) {
		server.log(msg);
		for (int i = 0; i < clients.size(); i++) {
			Client client = (Client) clients.get(i);
			client.sendMessage(msg);
		}
	}

	public synchronized void killClient(Client client) {
		String msg = client.socket.getInetAddress().toString();
		try {
			client.in.close();
			client.out.close();
			client.socket.close();
			for (int i = 0; i < clients.size(); i++) {
				if (((Client) clients.get(i)).equals(client)) {
					clients.remove(i);
				}
			}
			clients.trimToSize();
			clientCount--;
		} catch (Exception e) {
		}
		broadcast(msg + " has disconnected");
	}
}
