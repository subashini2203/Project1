package accounts;

import java.io.Serializable;

public class AccountCounter implements Serializable {
private static final long serialVersionUID = 1L;
    
    private int counter;

    public AccountCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
	


