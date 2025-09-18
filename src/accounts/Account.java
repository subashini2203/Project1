package accounts;

import customer.Customer;

public class Account {
    
    private static int accCounter = 2000;
    private int accountNumber;
    private String accountType;  
    private double balance;
    private Customer customer;

    public Account(String accountType, double balance, Customer customer) {
        this.accountNumber = ++accCounter;
        this.accountType = accountType;
        this.balance = balance;
        this.customer = customer;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "===== Account Details =====" +
               "\nAccount No: " + accountNumber +
               "\nAccount Type: " + accountType +
               "\nBalance: " + balance +
               "\n--- Customer Details ---\n" + customer;
    }
}
