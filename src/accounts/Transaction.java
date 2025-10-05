package accounts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
	
	    private static int transactionCounter = 1000;
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
	   
	    public String toString() {
	        if ("Transfer".equalsIgnoreCase(type)) {
	            return "Transaction ID: " + transactionId +
	                    " | Time: " + transactionTime +
	                    " | Type: " + type +
	                    " | From: " + accountNumber +
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
	}