package project1BankingSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accounts.Account;
import accounts.Account.InvalidPinException;
import customer.Customer;

public class BankApplication {

	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        List<Customer> customers = new ArrayList<>();
	        List<Account> accounts = new ArrayList<>();
	        int choice;
	        
	        do {
	            System.out.println("\n===== Mini Banking System =====");
	            System.out.println("1. Create Account (Savings/Current)");
	            System.out.println("2. Deposit Money");
	            System.out.println("3. Withdraw Money");
	            System.out.println("4. View Account Details");
	            System.out.println("5. View Transaction History");
	            System.out.println("6. Transfer Funds Between Accounts");
	            System.out.println("7. Generate Account Statement");
	            System.out.println("8. Exit");
	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();
	            sc.nextLine();
	            

	            switch (choice) {
	                case 1:
	                	 System.out.print("Enter name: ");
	                     String name = sc.nextLine();
	                     System.out.print("Enter mobile: ");
	                     long mobile = sc.nextLong();
	                     sc.nextLine();
	                     System.out.print("Enter address: ");
	                     String address = sc.nextLine();
	                     System.out.print("Enter email: ");
	                     String email = sc.nextLine();

	                     Customer customer = new Customer(name, mobile, address, email);
	                     customers.add(customer);

	                     System.out.println(" Customer Created Successfully!");
	                     System.out.println(customer);
	                     break;

	                case 2:
	                	if (customers.isEmpty()) {
	                        System.out.println(" No customers found! Please create a customer first.");
	                        break;
	                    }
	                	
	                	 System.out.println("Available Customers:");
	                	 
	                     for (Customer c : customers) {
	                         System.out.println(c.getCustomerId() + " - " + c.getName());
	                     }
	                     

	                     System.out.print("Enter Customer ID for Account: ");
	                     int custId = sc.nextInt();
	                     sc.nextLine();
	                     

	                     Customer selectedCustomer = null;
	                     for (Customer c : customers) {
	                         if (c.getCustomerId() == custId) {
	                             selectedCustomer = c;
	                             break;
	                         }
	                     }

	                     if (selectedCustomer == null) {
	                         System.out.println(" Invalid Customer ID!");
	                         break;
	                     }

	                     System.out.print("Enter Account Type (Savings/Current): ");
	                     String accType = sc.nextLine();

	                     System.out.print("Enter Initial Balance: ");
	                     double balance = sc.nextDouble();

	                     System.out.print("Set 4-digit PIN: ");
	                     int pin = sc.nextInt();
	                     
	                     Account account = new Account(selectedCustomer.getCustomerId(), accType, balance, pin);
	                     accounts.add(account);

	                    
	                    System.out.println("\n Account Created Successfully!");
	                    account.displayAccountDetails();
	                     break;

	                case 3:
	                	if (accounts.isEmpty()) {
	                        System.out.println(" No accounts found! Please create an account first.");
	                        break;
	                    }

	                    System.out.print("Enter Account Number: ");
	                    int accNum = sc.nextInt();
	                    System.out.print("Enter PIN: ");
	                    int depPin = sc.nextInt();
	                    System.out.print("Enter Deposit Amount: ");
	                    double depAmount = sc.nextDouble();

	                    Account depAccount = null;
	                    for (Account a : accounts) {
	                        if (a.getAccountNumber() == accNum) {
	                            depAccount = a;
	                            break;
	                        }
	                    }

	                    if (depAccount == null) {
	                        System.out.println(" Invalid Account Number!");
	                        break;
	                    }

	                    try {
	                        depAccount.deposit(depAmount, depPin);
	                    } catch (InvalidPinException e) {
	                        System.out.println(e.getMessage());
	                    }
	                    break;
	                    
	                case 4: 
	                    if (accounts.isEmpty()) {
	                        System.out.println(" No accounts found! Please create an account first.");
	                        break;
	                    }

	                    System.out.print("Enter Account Number: ");
	                    int wAccNum = sc.nextInt();
	                    System.out.print("Enter PIN: ");
	                    int wPin = sc.nextInt();
	                    System.out.print("Enter Withdraw Amount: ");
	                    double wAmount = sc.nextDouble();

	                    Account wAccount = null;
	                    for (Account a : accounts) {
	                        if (a.getAccountNumber() == wAccNum) {
	                            wAccount = a;
	                            break;
	                        }
	                    }

	                    if (wAccount == null) {
	                        System.out.println(" Invalid Account Number!");
	                        break;
	                    }

	                    try {
	                        wAccount.withdraw(wAmount, wPin);
	                    } catch (InvalidPinException e) {
	                        System.out.println(e.getMessage());
	                    }
	                    break;
	                    
	                case 5:
	                    System.out.println(" Exiting... Thank you!");
	                    break;



	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        } while (choice != 5);

	        sc.close();
	    }
	}

