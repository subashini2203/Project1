package accounts;


	public class InvalidPinException extends Exception {

    public InvalidPinException() {
		        super("Invalid PIN entered!");
		    }
		
    public InvalidPinException(String message) {
	        super(message);
	 }
	}

