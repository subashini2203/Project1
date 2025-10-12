package accounts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import accounts.Transaction;

import customer.Customer;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int accCounter;
    private int accountNumber;
    private int customerId; 
    private String accountType;  
    private double balance;
    private int pin; 

   public static void setaccCounter(int accCounter1) {
	   accCounter = accCounter1;
   }

    public Account(int customerId, String accountType, double initialBalance, int pin) {
    	 this.accountNumber = ++accCounter;
         this.customerId = customerId;
         this.accountType = accountType;
         this.balance = initialBalance;
         this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    
    public int getCustomerId() 
    { 
    	return customerId;
    	}

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
    private List<Transaction> transactions = new ArrayList<>();
    
    public boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }
    
    public void deposit(double amount, int enteredPin) {
    	
        try {
            if (enteredPin != pin) {
                throw new InvalidPinException();
            }

            if (accountType.equalsIgnoreCase("Savings")) {
                double minDeposit = 100;   
                double maxDeposit = 50000; 

                if (amount < minDeposit) {
                    
                	 throw new MinDepositLimitNotMetException(minDeposit);
                }
                if (amount > maxDeposit) {
                    
                	 throw new MaxDepositLimitExceededException(maxDeposit);
                }
            }
            double initial = balance;
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
            int txnId = Transaction.getTransactionCounter();
            transactions.add(new Transaction(txnId,accountNumber, "Deposit", amount, initial, balance, "Success"));

            
        } catch (InvalidPinException | MinDepositLimitNotMetException | MaxDepositLimitExceededException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }


    public boolean withdraw(double amount, int enteredPin) {
    	
        try {
            if (enteredPin != pin) {
                throw new InvalidPinException("Invalid PIN! Withdrawal denied.");
            }

            
            if (amount > balance) {
                throw new InsufficientBalanceException(
                    " Withdrawal Failed! Insufficient Balance. Available: " + balance
                );
            }

            double initial = balance;
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
            
            int txnId = Transaction.getTransactionCounter();
            transactions.add(new Transaction(txnId,accountNumber, "Withdraw", amount, initial, balance, "Success"));

            return true;
        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void withdrawForTransfer(double amount, int receiverAccountNumber) {
        double initial = balance;
        balance -= amount;
        System.out.println("Transferred Out: " + amount + " | New Balance: " + balance);
        int txnId = Transaction.getTransactionCounter();
        transactions.add(new Transaction(txnId,accountNumber, "Transfer to " + receiverAccountNumber, amount, initial, balance, "Success"));
    }
    public void depositForTransfer(double amount, int senderAccountNumber) {
        double initial = balance;
        balance += amount;
        System.out.println("Transferred In: " + amount + " | New Balance: " + balance);
        int txnId = Transaction.getTransactionCounter();
        transactions.add(new Transaction(txnId ,accountNumber, "Transfer from " + senderAccountNumber, amount, initial, balance, "Success"));
    }

   
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
    }

    public void showTransactionHistory() {
    	
        if (transactions.isEmpty()) {
            System.out.println(" No transactions yet.");
        } else {
            System.out.println("Transaction History for Account " + accountNumber + ":");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
@Override
public String toString() {
    return "Account Number: " + accountNumber +
           "\nCustomer ID: " + customerId +
           "\nAccount Type: " + accountType +
           "\nBalance: " + balance;
}
}
  