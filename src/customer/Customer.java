
package customer;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

	 
	  private static int idCounter = 1000; 
	    private int customerId;
	    private String name;
	    private long mobileNumber;
	    private String address;
	    private String emailId;

	    public Customer( String name, long mobileNumber, String address, String emailId) {
	        this.customerId =++idCounter;
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


