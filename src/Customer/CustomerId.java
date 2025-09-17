
package Customer;
import project1BankingSystem.BankApplication;
 public class CustomerId {
	
	    private String name;
	    private int customerId;
	    private long mobileNumber;
	    private String address;
	    private String emailId;

	    public CustomerId(int customerId, String name, long mobileNumber, String address, String emailId) {
	        this.customerId = customerId;
	        this.name = name;
	        this.mobileNumber = mobileNumber;
	        this.address = address;
	        this.emailId = emailId;
	    }

	
	    public int getCustomerId() {
	        return customerId;
	    }

	    public String getName() {
	        return name;
	    }

	    public long getMobileNumber() {
	        return mobileNumber;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public String getEmailId() {
	        return emailId;
	    }

	    @Override
	    public String toString() {
	        return "Customer ID: " + customerId +
	               "\nName: " + name +
	               "\nMobile: " + mobileNumber +
	               "\nAddress: " + address +
	               "\nEmail: " + emailId;
	    }
	}


