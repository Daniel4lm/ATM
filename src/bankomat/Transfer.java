package bankomat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {
		
	private int sourceAccount;
	private int targetAccount;
	private double amount;
	private String date;
	
	public Transfer() {
		// defaultnog no-arq constructor 
	}
	
	/* Arq-constructor for Transfer */
	public Transfer(int sourceAccount, int targetAccount, double amount) {
		
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		date = this.getDate();
	}
	
	/* Create date format */
	private String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	public int get_sourceAccount() {
		return sourceAccount;
	}

	public int get_targetAccount() {
		return targetAccount;
	}

	public double get_amount() {
		return amount;
	}
	
	
	
	@Override
	public String toString() {
		return "———————————————————————————————————————————————\n"+
				" Broj #1 racuna: " + sourceAccount + "\n Broj #2 racuna: " + targetAccount +
				"\n Iznos prebacenog novca: " + amount +
				"\n———————————————————————————————————————————————";		
	}	
	
}