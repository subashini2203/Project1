package customer;

import java.io.Serializable;

public class CustomerCounter  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int counter;
	
	 public CustomerCounter(int counter) {
	        this.counter = counter;
	    }

	    public int getCounter() {
	        return counter;
	    }

	    public void setCounter(int counter) {
	        this.counter = counter;
	    }
	}


