package accounts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import accounts.Transaction;

import customer.Customer;

public class Account  {
    
	  private static int accCounter = 2000;
    private int accountNumber;
    private int customerId; 
    private String accountType;  
    private double balance;
    private int pin; 

   

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
    public void deposit(double amount, int enteredPin) {
    	
        try {
            if (enteredPin != pin) {
                throw new InvalidPinException();
            }

            if (accountType.equalsIgnoreCase("Savings")) {
                double minDeposit = 100;   
                double maxDeposit = 50000; 

                if (amount < minDeposit) {
                    //TODO create and throw MinDepositLimitNotMetException
                	 throw new MinDepositLimitNotMetException(minDeposit);
                }
                if (amount > maxDeposit) {
                    //TODO create and throw MaxDepositLimitExceededException
                	 throw new MaxDepositLimitExceededException(maxDeposit);
                }
            }
            double initial = balance;
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
            transactions.add(new Transaction("Deposit", amount));

        } catch (InvalidPinException | MinDepositLimitNotMetException | MaxDepositLimitExceededException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }


    public void withdraw(double amount, int enteredPin) {
    	
        try {
            if (enteredPin != pin) {
                throw new InvalidPinException("Invalid PIN! Withdrawal denied.");
            }

            
            if (amount > balance) {
                throw new InsufficientBalanceException(
                    " Withdrawal Failed! Insufficient Balance. Available: " + balance
                );
            }

          
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);

           
            transactions.add(new Transaction("Withdraw", amount));

        } catch (InvalidPinException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
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
  