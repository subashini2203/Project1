package project1BankingSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accounts.Account;
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

	                     Account account = new Account(accType, balance, selectedCustomer);
	                     accounts.add(account);

	                     System.out.println("\n Account Created Successfully!");
	                     System.out.println(account);
	                     break;

	                case 3:
	                    System.out.println("Exiting... Thank you!");
	                    break;

	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        } while (choice != 3);

	        sc.close();
	    }
	}

