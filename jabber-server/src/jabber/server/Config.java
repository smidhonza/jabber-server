/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jabber.server;

import java.util.HashMap;

/**
 *
 * @author Jan Machala <jan.machala@email.cz>
 */
public class Config {
	
	private HashMap<String, String> options = new HashMap<String, String>();
	private static Config instance = null;

	private Config() {
		this.options.put("port", "1111");
	}
	
	public static Config getInstance() {
		if (Config.instance == null) {
			Config.instance = new Config();
		}
		return Config.instance;
	}
	
	public String getOption(String key) {
		return this.options.get(key);
	}
}
