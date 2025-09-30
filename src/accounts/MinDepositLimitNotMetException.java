package accounts;


    public class MinDepositLimitNotMetException extends Exception {
    	
    	
	public MinDepositLimitNotMetException(double minLimit) {
        super("Deposit Failed! Minimum deposit allowed is: " + minLimit);
    }
}
