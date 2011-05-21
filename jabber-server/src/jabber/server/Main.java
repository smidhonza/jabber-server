/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jabber.server;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(new Server());
		thread.start();
	}
}
