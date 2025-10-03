package project1BankingSystem;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import accounts.Account;

import accounts.InvalidPinException;
import customer.Customer;

public class BankApplication {
	
	private static Scanner sc = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();

    private static final String CUSTOMER_FILE = "customers.dat";
    private static final String ACCOUNT_FILE = "accounts.dat";

    private static void loadData() {
        ObjectInputStream ois = null;

        
        try {
            ois = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE));
            customers = (List<Customer>) ois.readObject();
            System.out.println("Customers loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Customer data file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                   
                }
            }
        }

       
        ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(ACCOUNT_FILE));
            accounts = (List<Account>) ois.readObject();
            System.out.println("Accounts loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Account data file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading account data: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading account data: " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                   
                }
            }
        }
    }

    private static void saveData() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE));
            oos.writeObject(customers);
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                 
                }
            }
        }

        oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ACCOUNT_FILE));
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving account data: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                   
                }
            }
        }
    }
	public static void main(String[] args) {
		 loadData();
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
	                     saveData();

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
	                     String custInput = sc.nextLine().trim();
	                     int custId;
	                     try {
	                         custId = Integer.parseInt(custInput);
	                        
	                     } catch (NumberFormatException e) {
	                         System.out.println(" Invalid input! Returning to main menu");
	                         break;
	                         	
	                     }
	                 

	                     Customer selectedCustomer = null;
	                     for (Customer c : customers) {
	                         if (c.getCustomerId() == custId) {
	                             selectedCustomer = c;
	                             break;
	                         }
	                     }

	                     if (selectedCustomer == null) {
	                         System.out.println(" Invalid Customer ID! Returning to main menu");
	                         break;
	                     }
                         
	              
	                    
	                     System.out.print("Enter Account Type (Savings/Current): ");
	                     String accType = sc.nextLine().trim();
	                     
	                     if (!(accType.equalsIgnoreCase("Savings") || accType.equalsIgnoreCase("Current"))) {
	                         System.out.println(" Invalid account type! Returning to main menu...");
	                         break;
	                     }
	                 

	                  System.out.print("Enter Initial Balance: ");
	                     String balInput = sc.nextLine().trim();
	                     double balance;
	                     try {
	                         balance = Double.parseDouble(balInput);
	                         if (balance < 0) {
	                             System.out.println("Balance cannot be negative!Returning to main menu");
	                             break;
	                             } 
	                            
	                    } catch (NumberFormatException e) {
	                         System.out.println("Invalid input! Returning to main menu");
	                    break;
	                    }
	                 
	                   
	                  System.out.print("Set 4-digit PIN: ");
	                       String pinInput = sc.nextLine().trim();
	                       int pin;
	                       try {
	                         pin = Integer.parseInt(pinInput);
	                         if (pin < 1000 || pin > 9999) {
	                             System.out.println("PIN must be a 4-digit number! Returning to main menu");
	                            break;
	                         }
	                     } catch (NumberFormatException e) {
	                         System.out.println("Invalid input! Returning to main menu");
	                      break;
	                      }
	                 
	                	
	                     
	                     Account account = new Account(selectedCustomer.getCustomerId(), accType , balance, pin);
	                     accounts.add(account);
	                     saveData();
	                    
	                    System.out.println("\n Account Created Successfully!");
	                    account.displayAccountDetails();
	                     break;

	                case 3:
//	                	if (accounts.isEmpty()) {
//	                        System.out.println(" No accounts found! Please create an account first.");
//	                        break;
//	                    }
	                	  
	                    System.out.print("Enter Account Number: ");
	                    String input1 = sc.nextLine().trim();
	                    int accNum;
	                       try {
	                          accNum = Integer.parseInt(input1);
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid input! Returning to main menu");
	                        break;
	                        }
	                
	                	   final int accountNumberToFind = accNum;
	                	    Optional<Account> depAccountOpt = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == accountNumberToFind)
	                            .findFirst();
	                         

	                	    if (depAccountOpt.isEmpty()) {
	                	        System.out.println(" Invalid Account Number!  Returning to main menu");
	                	        break;
	                	    }
	                	    Account depAccount = depAccountOpt.get();
						
	                	    
	                        System.out.print("Enter PIN: ");
	                        String pinInput1 = sc.nextLine().trim();
	                        int depPin;
	                        try {
	                          depPin = Integer.parseInt(pinInput1);
	                          } catch (NumberFormatException e) {
	                          System.out.println(" Invalid PIN! Please enter numbers only.");
	                          break;
	                          }
	                  
	                	   System.out.print("Enter Deposit Amount: ");
	                       String amtInput = sc.nextLine().trim();
	                       double depAmount;
	                       try {
	                          depAmount = Double.parseDouble(amtInput);
	                          if (depAmount <= 0) {
	                              System.out.println("Deposit amount must be positive! Returning to main menu");
	                            break;
	                          }
	                          } catch (NumberFormatException e) {
	                          System.out.println("Invalid input! Returning to main menu");
	                          break;
	                          }
	

	                     depAccount.deposit(depAmount, depPin);
	                     saveData();
	                     break;

	                    
	                case 4: 
//	                    if (accounts.isEmpty()) {
//	                        System.out.println(" No accounts found! Please create an account first.");
//	                        break;
//	                    }
	                	
	                    System.out.print("Enter Account Number: ");
	                    String input2 = sc.nextLine().trim();
	                    int wAccNum;
	                    try {
	                        wAccNum = Integer.parseInt(input2);
	                       
	                    } catch (NumberFormatException e) {
	                        System.out.println(" Invalid input! Please enter numbers only for Account Number.");
	                        break;
	                        }
	                
	                	  final int withdrawAccountNumber = wAccNum;
	                	  Optional<Account> wAccountOpt  = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == withdrawAccountNumber)
	                            .findFirst();
	                          

	                    if (wAccountOpt.isEmpty()) {
	                        System.out.println(" Invalid Account Number!  Returning to main menu.");
	                        break;
	                    }

	                    Account wAccount = wAccountOpt.get();
	                    
	                    System.out.print("Enter PIN: ");
	                    String pinInput2 = sc.nextLine().trim();
	                    int wPin;
	                    try {
	                        wPin = Integer.parseInt(pinInput2);
	                       
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid PIN! Please enter numbers only.");
	                        break;
	                        }
	                
	                   
	                   System.out.print("Enter Withdraw Amount: ");
	                    String amtInput1 = sc.nextLine().trim();
	                    double wAmount;
	                    try {
	                        wAmount = Double.parseDouble(amtInput1);
	                        if (wAmount <= 0) {
	                            System.out.println(" Withdrawal amount must be positive!");
	                            break;
	                        }
	                        
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid input! Please enter numbers only for withdrawal amount.");
	                        break;
	                        }
	                

	                   
	                     wAccount.withdraw(wAmount, wPin);
	                     saveData();
	                    break;
	                    
	                case 5:
	                	
                          System.out.print("Enter Account Number: ");
	                	    int viewAccNum;
	                	    try {
	                	        viewAccNum = Integer.parseInt(sc.nextLine().trim());
	                	    } catch (NumberFormatException e) {
	                	        System.out.println("Invalid Account Number!");
	                	        break;
	                	    }

	                	    Account viewAccount = accounts.stream()
	                	            .filter(a -> a.getAccountNumber() == viewAccNum)
	                	            .findFirst()
	                	            .orElse(null);

	                	    if (viewAccount == null) {
	                	        System.out.println("Invalid Account Number!");
	                	        break;
	                	    }

	                	    System.out.println(" Account Details");
	                	    viewAccount.displayAccountDetails();
	                	    break;
	                case 6:
	                	 System.out.print("Enter Account Number: ");
	                	    int transAccNum;
	                	    try {
	                	        transAccNum = Integer.parseInt(sc.nextLine().trim());
	                	    } catch (NumberFormatException e) {
	                	        System.out.println("Invalid Account Number!");
	                	        break;
	                	    }

	                	    Account transAccount = accounts.stream()
	                	            .filter(a -> a.getAccountNumber() == transAccNum)
	                	            .findFirst()
	                	            .orElse(null);

	                	    if (transAccount == null) {
	                	        System.out.println("Invalid Account Number!");
	                	        break;
	                	    }

	                	    System.out.println(" Transaction History");
	                	    transAccount.showTransactionHistory();
	                	    break;

	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        } while (choice != 7);

	        sc.close();
	    }
	}

