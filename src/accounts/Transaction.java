package accounts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
	
	    private static int transactionCounter;
	    private int transactionId;
	    private LocalDateTime transactionTime;
	    private int accountNumber;
	    private Integer receiverAccountNumber;
	    private double amount;
	    private String status; 
	    private double initialBalance;
	    private double finalBalance;
	    private String type;
	    
	    public Transaction(int accountNumber, String type, double amount, double initialBalance, double finalBalance, String status) {
                this.transactionId = ++transactionCounter;
                this.transactionTime = LocalDateTime.now();
                this.accountNumber = accountNumber;
                this.type = type;
                this.amount = amount;
                this.initialBalance = initialBalance;
                this.finalBalance = finalBalance;
                this.status = status;
                this.receiverAccountNumber = null;
	    }
	    public Transaction(int fromAcc, int toAcc, double amount, double initialBalance, double finalBalance, String status) {
	        this.transactionId = ++transactionCounter;
	        this.transactionTime = LocalDateTime.now();
	        this.accountNumber = fromAcc;
	        this.receiverAccountNumber = toAcc;
	        this.type = "Transfer";
	        this.amount = amount;
	        this.initialBalance = initialBalance;
	        this.finalBalance = finalBalance;
	        this.status = status;
	    }
	   
	    public String toString() {
	        if ("Transfer".equalsIgnoreCase(type)) {
	            return "Transaction ID: " + transactionId +
	                    " | Time: " + transactionTime +
	                    " | Type: " + type +
	                    " | From: " + accountNumber +
	                     "|To:"+receiverAccountNumber+
	                    " | Amount: " + amount +
	                    " | Status: " + status +
	                    " | Balance After: " + finalBalance;
	        } else {
	            return "Transaction ID: " + transactionId +
	                    " | Time: " + transactionTime +
	                    " | Type: " + type +
	                    " | Account: " + accountNumber +
	                    " | Amount: " + amount +
	                    " | Status: " + status +
	                    " | Balance After: " + finalBalance;
	        }
	    }
	    
	    public LocalDateTime getTime() {
	        return transactionTime;
	    }
	    public static synchronized int getTransactionCounter() {
	        return transactionCounter;
	    }

	    public static void setTransactionCounter(int counter) {
	        transactionCounter = counter;
	    }

	    public int getTransactionId() {
	        return transactionId;
	}
}