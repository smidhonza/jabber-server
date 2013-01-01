package jabber.server;

/**
 * Created with IntelliJ IDEA.
 * User: abc
 * Date: 12/27/12
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLRequestParser {

	public static XMLRequest parse(String s) {
		return new XMLRequest(s);
	}
}
