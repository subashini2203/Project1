package accounts;

import java.util.Date;

public class Transaction {
	//TODO add all remaining fields to be added
		private String type;
		private double amount;
		private Date date; // TODO use LocalDateTime instead of Date
		
		public Transaction(String type,double amount){
			this.type = type;
			this.amount = amount;
			this.date = new Date();
		}
		public String toString() {
			 return date + " | " + type + " | Amount: " + amount;
		}

}
