package project1BankingSystem;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import accounts.Account;
import accounts.InvalidPinException;
import customer.Customer;
import accounts.Transaction;

public class BankApplication {
	
	private static Scanner sc = new Scanner(System.in);
    private static List<Customer> customers = new ArrayList<>();
    private static List<Account> accounts = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    private static final String CUSTOMER_FILE = "customers.dat";
    private static final String ACCOUNT_FILE = "accounts.dat";
    private static final String TRANSACTION_FILE = "transactions.dat";

    private static Object loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object data = ois.readObject();
            System.out.println(fileName + " loaded successfully.");
            return data;
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from " + fileName + ": " + e.getMessage());
        }
        return null;
    }

        	    
        	

    private static void loadData() {
   
            customers = (List<Customer>) loadData(CUSTOMER_FILE);
            accounts = (List<Account>) loadData(ACCOUNT_FILE);
            transactions = (List<Transaction>) loadData(TRANSACTION_FILE);
           

            if (customers == null) customers = new ArrayList<>();
            if (accounts == null) accounts = new ArrayList<>();
            if (transactions == null) transactions = new ArrayList<>();
         
            if (!customers.isEmpty()) {
                int maxId = customers.stream()
                        .mapToInt(Customer::getCustomerId)
                        .max()
                        .getAsInt();
                Customer.setIdCounter(maxId);
            }
            if(!accounts.isEmpty()) {
            	int maxId = accounts.stream()
            			    .mapToInt(Account::getAccountNumber)
            			    .max()
            			    .getAsInt();
            	Account.setaccCounter(maxId);
            }
            if (!transactions.isEmpty()) {
                int maxTid = transactions.stream().
                		mapToInt(Transaction::getTransactionId)
                		.max()
                		.getAsInt();
                
                Transaction.setTransactionCounter(maxTid);
               
            }
        }



    private static void saveData(String fileName, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving data to " + fileName + ": " + e.getMessage());
        }
    }

    
    private static void saveData() {
        saveData(CUSTOMER_FILE, customers);
        saveData(ACCOUNT_FILE, accounts);
        saveData(TRANSACTION_FILE, transactions);
    }
    private static Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
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
	                       double initialBal = depAccount.getBalance();
	                       depAccount.deposit(depAmount, depPin);
	                       double finalBal = depAccount.getBalance();
	                       
	                       int txnId = Transaction.getTransactionCounter();
	                       Transaction t = new Transaction(txnId ,accNum, "Deposit", depAmount, initialBal, finalBal, "Success");
	                       transactions.add(t);
	                  
	                       System.out.println("Deposit successful! Transaction ID: " + t.getTransactionId());


	                     break;

	                    
	                case 4: 
               	
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
	                    double initialBalW = wAccount.getBalance();
	                    boolean success =  wAccount.withdraw(wAmount,wPin);
	                    double finalBalW =wAccount.getBalance();

	                    String status = success ? "Success" : "Failed";
	                    int wTxnId = Transaction.getTransactionCounter();
	                    Transaction t2 = new Transaction(wTxnId,wAccount.getAccountNumber(), "Withdraw", wAmount, initialBalW, finalBalW, status);
	                    transactions.add(t2);

	                    saveData();

	                    if (success)
	                        System.out.println("Withdrawal successful! Transaction ID: " + t2.getTransactionId());
	                    else
	                        System.out.println("Withdrawal failed! Transaction ID: " + t2.getTransactionId());
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
	                	    
	                	    
	                case 7:
	                	System.out.println("Transfer Funds Between Accounts ");

	                  
	                    System.out.print("Enter source account number: ");
	                    int sourceAccNo = sc.nextInt();

	                    Account sourceAcc = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == sourceAccNo)
	                            .findFirst()
	                            .orElse(null);

	                    if (sourceAcc == null) {
	                        System.out.println("Source account not found!");
	                        break;
	                    }

	                   
	                    System.out.print("Enter PIN for source account: ");
	                    int pin1 = sc.nextInt();

	                    if (!sourceAcc.verifyPin(pin1)) {
	                        System.out.println("Invalid PIN! Transfer cancelled.");
	                        break;
	                    }

	                    System.out.print("Enter destination account number: ");
	                    int destAccNo = sc.nextInt();

	                    Account destAcc = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == destAccNo)
	                            .findFirst()
	                            .orElse(null);

	                    if (destAcc == null) {
	                        System.out.println("Destination account not found!");
	                        break;
	                    }

	                   
	                    if (sourceAccNo == destAccNo) {
	                        System.out.println("Cannot transfer to the same account!");
	                        break;
	                    }

	                
	                    System.out.print("Enter amount to transfer: ");
	                    double amount = sc.nextDouble();

	                    if (amount <= 0) {
	                        System.out.println("Invalid amount!");
	                        break;
	                    }

	                 
	                    if (sourceAcc.getBalance() < amount) {
	                        System.out.println("Insufficient balance!");
	                        break;
	                    }
	                   
	                
                        double initialSourceBal = sourceAcc.getBalance();
	                    boolean withdrawSuccess = sourceAcc.withdraw(amount, pin1);
                        double finalSourceBal = sourceAcc.getBalance();
                        
                        if (!withdrawSuccess) {
	                        System.out.println("Transfer failed during withdrawal!");
	                        break;
	                    }
                        
                        double initialDestBal = destAcc.getBalance();
                        destAcc.depositForTransfer(amount, sourceAccNo);
	                    double finalDestBal = destAcc.getBalance();

	                    int txsIdTransfer = Transaction.getTransactionCounter();
	                    Transaction tSource = new Transaction( txsIdTransfer,sourceAccNo, "Transfer Out", amount, initialSourceBal, finalSourceBal, "Success");
	                    
	                    int txnIdDest = Transaction.getTransactionCounter();
	                    Transaction tDest = new Transaction(txnIdDest,destAccNo, "Transfer In", amount, initialDestBal, finalDestBal, "Success");

	                    transactions.add(tSource);
	                    transactions.add(tDest);

	                    System.out.println("Transfer successful!");
	                    System.out.println("₹" + amount + " transferred from Account " + sourceAccNo + " to " + destAccNo);
	                    System.out.println("Source Transaction ID: " + tSource.getTransactionId());
	                    System.out.println("Destination Transaction ID: " + tDest.getTransactionId());
	            
	                    // ----- Submenu -----
	                    int subChoice;
	                    do {
	                        System.out.println("\n=== Transfer Submenu ===");
	                        System.out.println("1. View All Transactions");
	                        System.out.println("2. View Last 1 Hour Transactions");
	                        System.out.println("3. View Last 3 Hours Transactions");
	                        System.out.println("4. Return to Main Menu");
	                        System.out.print("Enter your choice: ");
	                        subChoice = sc.nextInt();

	                        if (subChoice == 1) {
	                            System.out.println(" All Transactions ");
	                            transactions.forEach(System.out::println);
	                            
	                        } else if (subChoice == 2) {
	                            LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
	                            System.out.println(" Transactions in Last 1 Hour");
	                            transactions.stream()
	                                    .filter(tx -> tx.getTime().isAfter(oneHourAgo))
	                                    .forEach(System.out::println);
	                            
	                        } else if (subChoice == 3) 
	                        {
	                            LocalDateTime threeHoursAgo = LocalDateTime.now().minusHours(3);
	                            System.out.println(" Transactions in Last 3 Hours");
	                            transactions.stream()
	                                    .filter(tx -> tx.getTime().isAfter(threeHoursAgo))
	                                    .forEach(System.out::println);
	                            
	                        } else if (subChoice == 4) {
	                            System.out.println("Returning to main menu...");
	                        } else {
	                            System.out.println("Invalid choice!");
	                        }

	                    } while (subChoice != 4);

	                    break;
	                case 8:
	                	System.out.println("Generate Account Statement");

	                    System.out.print("Enter Account Number: ");
	                    String inputAc = sc.nextLine().trim();
	                    int accNumSt;
	                    try {
	                        accNumSt = Integer.parseInt(inputAc);
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid input! Returning to main menu.");
	                        break;
	                    }

	                    final int accountNumberToFind1 = accNumSt;
	                    Optional<Account> accountOpt = accounts.stream()
	                            .filter(a -> a.getAccountNumber() == accountNumberToFind1)
	                            .findFirst();

	                    if (accountOpt.isEmpty()) {
	                        System.out.println("Account not found! Returning to main menu.");
	                        break;
	                    }

	                    Account account4 = accountOpt.get();

	                    
	                    System.out.print("Enter PIN: ");
	                    String pinInput4 = sc.nextLine().trim();
	                    int pin4;
	                    try {
	                        pin = Integer.parseInt(pinInput4);
	                    } catch (NumberFormatException e) {
	                        System.out.println("Invalid PIN! Returning to main menu.");
	                        break;
	                    }

	                    if (!account4.verifyPin(pin)) {
	                        System.out.println("Invalid PIN not matched to account! Returning to main menu.");
	                        break;
	                    }

	                   
	                    System.out.println("===== Account Statement =====");
	                    account4.displayAccountDetails();
	                    System.out.println("\nTransaction History:");
	                    account4.showTransactionHistory();

	                    break;
	                   
                 
	                case 9:
	                {
	                	System.out.println("Exit");
	                	saveData();
	                	break;
	                }
	                default:
	                    System.out.println("Invalid choice! Try again.");
	        }
	        } while (choice != 8);

	        sc.close();
	    }
	}

