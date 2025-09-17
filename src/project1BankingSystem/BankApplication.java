package project1BankingSystem;

import java.util.Scanner;

public class BankApplication {

	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
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

	            switch (choice) {
	                case 1:
	                    System.out.println(" Create Account Selected");
	                   
	                    System.out.println("Enter Name: john");
	                    System.out.println("Enter Account Type: Savings");
	                    System.out.println(" Account Created! Account No: 1001");
	                    break;

	                case 2:
	                    System.out.println(" Deposit Money Selected");
	                    
	                    System.out.println("Enter Account Number: 1001");
	                    System.out.println("Enter Amount to Deposit: 3000");
	                    System.out.println(" Deposit Successful! Updated Balance: 8000.0");
	                    break;

	                case 3:
	                    System.out.println(" Withdraw Money Selected");
	                    
	                    System.out.println("Enter Account Number: 1001");
	                    System.out.println("Enter Amount to Withdraw: 2000");
	                    System.out.println(" Withdrawal Successful! Updated Balance: 6000.0");
	                    break;

	                case 4:
	                    System.out.println(" View Account Details Selected");
	                    
	                    System.out.println("===== Account Details =====");
	                    System.out.println("Account No: 1001");
	                    System.out.println("Account Type: Savings");
	                    System.out.println("Customer Name: john");
	                    System.out.println("Balance: 6000.0");
	                    break;
	                    
	                case 5:
	                    System.out.println(" View Transaction History Selected");
	                    
	                    System.out.println("===== Transaction History =====");
	                    System.out.println("TxnID: T001 | Deposit  | +3000 | Date: 2025-09-12 10:30");
	                    System.out.println("TxnID: T002 | Withdraw | -2000 | Date: 2025-09-12 12:15");
	                    break;

	                case 6:
	                    System.out.println(" Transfer Funds Selected");
	                    
	                    System.out.println("Enter From Account: 1001");
	                    System.out.println("Enter To Account: 1002");
	                    System.out.println("Enter Amount: 1000");
	                    System.out.println(" Transfer Successful! New Balance: From=5000, To=7000");
	                    break;

	                case 7:
	                    System.out.println(" Generate Account Statement Selected");
	                    
	                    System.out.println("===== Account Statement =====");
	                    
	                    System.out.println("Opening Balance: 5000.0");
	                    System.out.println("TxnID: T001 | Deposit  | +3000 | Balance: 8000.0");
	                    System.out.println("TxnID: T002 | Withdraw | -2000 | Balance: 6000.0");
	                    System.out.println("Closing Balance: 6000.0");
	                    break;
	               
	                case 8 :
	                    System.out.println(" Exiting... Thank you!");
	                    break;

	                default:
	                    System.out.println(" Invalid choice! Try again.");
	            }
	        } while (choice != 8);

	        sc.close();
	    }
	
		
		


	}


