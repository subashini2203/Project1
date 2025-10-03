package accounts;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException() {
        super("Withdrawal Failed! Insufficient Balance.");
    }

	    public InsufficientBalanceException(String message) {
	        super(message);
	    }

	}


