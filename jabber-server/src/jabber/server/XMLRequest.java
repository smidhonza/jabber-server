package jabber.server;

/**
 * Created with IntelliJ IDEA.
 * User: abc
 * Date: 12/27/12
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLRequest {


    private final boolean isCloseRequest;
    private final String message;

    public XMLRequest(String s) {
        this.message = s;
        if (s.equals("BYE\r\n")) {
            this.isCloseRequest = true;
        } else {
            this.isCloseRequest = false;
        }
    }

    public boolean isCloseRequest() {
        return isCloseRequest;
    }

    public String getMessage() {
        return message;
    }
}
