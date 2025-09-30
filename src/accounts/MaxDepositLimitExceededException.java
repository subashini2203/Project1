package accounts;

    public class MaxDepositLimitExceededException extends Exception {
	
    public MaxDepositLimitExceededException(double maxLimit) {
        super("Deposit Failed! Maximum deposit allowed is: " + maxLimit);
    }
}


