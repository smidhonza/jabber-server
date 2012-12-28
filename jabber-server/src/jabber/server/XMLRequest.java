package jabber.server;

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
