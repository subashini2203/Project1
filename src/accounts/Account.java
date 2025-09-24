package accounts;

import accounts.InvaildPinException.InvalidPinException;
import customer.Customer;

public class Account {
    
    private static int accCounter = 2000;
    private int accountNumber;
    private int customerId; 
    private String accountType;  
    private double balance;
    private int pin; 

    public static class InvalidPinException extends Exception {
        public InvalidPinException(String message) {
            super(message);
        }
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

    public void deposit(double amount, int enteredPin) throws InvalidPinException {
        if (enteredPin != pin) {
            throw new InvalidPinException(" Invalid PIN! Deposit denied.");
        }
        balance += amount;
        System.out.println(" Deposited: " + amount + " | New Balance: " + balance);
    }
    public void withdraw(double amount, int enteredPin) throws InvalidPinException {
        if (enteredPin != pin) {
            throw new InvalidPinException(" Invalid PIN! Withdrawal denied.");
        }
        if (amount <= balance) {
            balance -= amount;
            System.out.println(" Withdrawn: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println(" Insufficient balance!");
        }
    }
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
    }

@Override
public String toString() {
    return "Account Number: " + accountNumber +
           "\nCustomer ID: " + customerId +
           "\nAccount Type: " + accountType +
           "\nBalance: " + balance;
}
}
  