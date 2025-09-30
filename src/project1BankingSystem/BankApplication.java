package project1BankingSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import accounts.Account;

import accounts.InvalidPinException;
import customer.Customer;

public class BankApplication {

	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        List<Customer> customers = new ArrayList<>();
	        List<Account> accounts = new ArrayList<>();
	        int choice = 0;
	        
	        do {
	            System.out.println("\n===== Mini Banking System =====");
	            System.out.println("1. Create Customer Account");
	            System.out.println("2. Create Account (Savings/Current)");
	            System.out.println("3. Deposit Money");
	            System.out.println("4. Withdraw Money");
	            System.out.println("5. View Account Details");
	            System.out.println("6. View Transaction History");
	            System.out.println("7. Transfer Funds Between Accounts");
	            System.out.println("8. Generate Account Statement");
	            System.out.println("9. Exit");
	            System.out.print("Enter your choice: ");
	            String input = sc.nextLine();
	            try {
	                choice = Integer.parseInt(input);
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid input! Please enter a number between 1 and 9.");
	                continue; 
	            }

	            switch (choice) {
	                case 1:
	                	String name = "";
	                    long mobile = 0;
	                    String address = "";
	                    String email = "";

	                	 System.out.print("Enter name: ");
	                	 name = sc.nextLine().trim();
	                	    if (name.isEmpty()) {
	                	        System.out.println(" Name cannot be empty!");
	                	        break;
	                	    }

	                     
	                     System.out.print("Enter mobile: ");
	                     try {
	                         String mobileInput = sc.nextLine().trim();
	                         mobile = Long.parseLong(mobileInput);
	                         if (mobileInput.length() != 10) {
	                             System.out.println("Mobile number must be 10 digits!");
	                             break;
	                         }
	                     } catch (NumberFormatException e) {
	                         System.out.println("Invalid mobile number!");
	                         break;
	                     }
	                   
	                     System.out.print("Enter address: ");
	                     address = sc.nextLine().trim();
	                     if (address.isEmpty()) {
	                         System.out.println("Address cannot be empty!");
	                         break;
	                     }
	                     
	                     System.out.print("Enter email: ");
	                   
	                     email = sc.nextLine().trim();
	                     if (!email.isEmpty() && !email.contains("@")) {
	                         System.out.println(" Invalid email format!");
	                         break;
	                     }

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
	                     
	                     int custId = -1;
	                     while (true) {
	                     System.out.print("Enter Customer ID for Account: ");
	                     String custInput = sc.nextLine().trim();
	                     try {
	                         custId = Integer.parseInt(custInput);
	                         break;
	                     } catch (NumberFormatException e) {
	                         System.out.println(" Invalid input! Please enter a valid Customer ID (numbers only).");
	                     }
	                 }

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

	                     String accType = "";
	                     while (true) {
	                     System.out.print("Enter Account Type (Savings/Current): ");
	                     accType = sc.nextLine().trim();
	                     if (accType.equalsIgnoreCase("Savings") || accType.equalsIgnoreCase("Current")) {
	                         break;
	                     } else {
	                         System.out.println("Invalid account type! Please enter 'Savings' or 'Current'.");
	                     }
	                 }

	                     double balance = 0;
	                     while (true) {
	                     System.out.print("Enter Initial Balance: ");
	                     String balInput = sc.nextLine().trim();
	                     try {
	                         balance = Double.parseDouble(balInput);
	                         if (balance < 0) {
	                             System.out.println("Balance cannot be negative!");
	                         } else {
	                             break;
	                         }
	                     } catch (NumberFormatException e) {
	                         System.out.println("Invalid input! Please enter a valid number.");
	                     }
	                 }
	                     int pin = 0;
	                     while (true) {
	                     System.out.print("Set 4-digit PIN: ");
	                     String pinInput = sc.nextLine().trim();
	                     try {
	                         pin = Integer.parseInt(pinInput);
	                         if (pin < 1000 || pin > 9999) {
	                             System.out.println("PIN must be a 4-digit number!");
	                         } else {
	                             break;
	                         }
	                     } catch (NumberFormatException e) {
	                         System.out.println("Invalid input! Please enter numbers only.");
	                     }
	                 }
	                     
	                     Account account = new Account(selectedCustomer.getCustomerId(), accType, balance, pin);
	                     accounts.add(account);

	                    
	                    System.out.println("\n Account Created Successfully!");
	                    account.displayAccountDetails();
	                     break;

	                case 3:
//	                	if (accounts.isEmpty()) {
//	                        System.out.println(" No accounts found! Please create an account first.");
//	                        break;
//	                    }
	                	   int accNum = -1;
	                	   while (true) {
	                    System.out.print("Enter Account Number: ");
	                    String input1 = sc.nextLine().trim();
	                       try {
	                          accNum = Integer.parseInt(input1);
	                        break;
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid input! Please enter numbers only for Account Number.");
	                    }
	                }
	                	   final int accountNumberToFind = accNum;
	                	    Optional<Account> depAccountOpt = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == accountNumberToFind)
	                            .findFirst();
	                         

	                	    if (depAccountOpt.isEmpty()) {
	                	        System.out.println(" Invalid Account Number!");
	                	        break;
	                	    }
	                	    Account depAccount = depAccountOpt.get();
						
	                	    int depPin = -1;
	                	    while (true) {
	                      System.out.print("Enter PIN: ");
	                      String pinInput = sc.nextLine().trim();
	                      try {
	                          depPin = Integer.parseInt(pinInput);
	                          break;
	                      } catch (NumberFormatException e) {
	                          System.out.println(" Invalid PIN! Please enter numbers only.");
	                      }
	                  }
	                	    
	                	    double depAmount = 0;
	                	    while (true) {
	                      System.out.print("Enter Deposit Amount: ");
	                      String amtInput = sc.nextLine().trim();
	                      try {
	                          depAmount = Double.parseDouble(amtInput);
	                          if (depAmount <= 0) {
	                              System.out.println("Deposit amount must be positive!");
	                              continue;
	                          }
	                          break;
	                      } catch (NumberFormatException e) {
	                          System.out.println("Invalid input! Please enter numbers only for deposit amount.");
	                      }
	                  }

	                    depAccount.deposit(depAmount, depPin);
	                    
	                   break;

	                    
	                case 4: 
//	                    if (accounts.isEmpty()) {
//	                        System.out.println(" No accounts found! Please create an account first.");
//	                        break;
//	                    }
	                	 int wAccNum = -1;
	                	  while (true) {
	                    System.out.print("Enter Account Number: ");
	                    String input2 = sc.nextLine().trim();
	                    try {
	                        wAccNum = Integer.parseInt(input2);
	                        break;
	                    } catch (NumberFormatException e) {
	                        System.out.println(" Invalid input! Please enter numbers only for Account Number.");
	                    }
	                }
	                	  final int withdrawAccountNumber = wAccNum;
	                	  Optional<Account> wAccountOpt  = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == withdrawAccountNumber)
	                            .findFirst();
	                          

	                    if (wAccountOpt.isEmpty()) {
	                        System.out.println(" Invalid Account Number!");
	                        break;
	                    }

	                    Account wAccount = wAccountOpt.get();
	                    int wPin = -1;
	                    while (true) {
	                    System.out.print("Enter PIN: ");
	                    String pinInput = sc.nextLine().trim();
	                    try {
	                        wPin = Integer.parseInt(pinInput);
	                        break;
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid PIN! Please enter numbers only.");
	                    }
	                }
	                    double wAmount = 0;
	                    while (true) {
	                    System.out.print("Enter Withdraw Amount: ");
	                    String amtInput = sc.nextLine().trim();
	                    try {
	                        wAmount = Double.parseDouble(amtInput);
	                        if (wAmount <= 0) {
	                            System.out.println(" Withdrawal amount must be positive!");
	                            continue;
	                        }
	                        break;
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid input! Please enter numbers only for withdrawal amount.");
	                    }
	                }

	                   
	                     wAccount.withdraw(wAmount, wPin);
	                    break;
	                    
	                case 5:
	                	 if (accounts.isEmpty()) {
	                         System.out.println("No accounts found!");
	                         break;
	                     }

	                     System.out.print("Enter Account Number: ");
	                     int viewAccNum = sc.nextInt();

	                     Account viewAccount = accounts.stream()
	                             .filter(a -> a.getAccountNumber() == viewAccNum)
	                            .findFirst()
	                             .orElse(null);

	                     if (viewAccount == null) {
	                         System.out.println("Invalid Account Number!");
	                         break;
	                     }

	                     viewAccount.showTransactionHistory();
	                     break;


	                case 6:
	                    System.out.println("Exiting... Thank you!");
	                    break;

	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        } while (choice != 6);

	        sc.close();
	    }
	}

