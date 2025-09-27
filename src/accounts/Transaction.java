package accounts;

import java.util.Date;

public class Transaction {
		private String type;
		private double amount;
		private Date date;
		
		public Transaction(String type,double amount){
			this.type = type;
			this.amount = amount;
			this.date = new Date();
		}
		public String toString() {
			 return date + " | " + type + " | Amount: " + amount;
		}

}
