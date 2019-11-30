package bankomat;

public class Account {
	private int accountNumber;
	private Client client;
	private double accountAmount;
	
	public Account() {
		// default no-arq constructor
	}
	
	/* Arq-constructors for Account */
	public Account(int accountNumber, String nameClient) {
		this.accountNumber = accountNumber;
		this.client = new Client(nameClient);
	}
	
	public Account(int accountNumber, String nameClient, double accountAmount) {
		this.accountNumber = accountNumber;
		this.client = new Client(nameClient);
		this.accountAmount = accountAmount;
	}
	
	/* Method that adds money to the account */
	public void addAmount(int accountNumber, double amount) {
		
		if(this.accountNumber == accountNumber) {
			this.accountNumber += amount;
		} else {
			System.out.println("Account number is not valid. Transaction is not posible!");
		}
		
	}
	
	/* Method that checks whether is possible to rise money */
	public boolean checkAccount(double amount) {		

		return ( (this.accountAmount - amount) < 0 )? false : true;		
	}
	
	/* Method that takes money from account */
	public void takeAmount(int accountNumber, double amount) {		
		
		if(this.accountNumber == accountNumber && checkAccount(amount)) {
			this.accountNumber -= amount;
		}else if(this.accountNumber == accountNumber && checkAccount(amount) == false) {
			System.out.println("Iznos na racuna nije dovoljan za transakciju!");
		}
		
		if(this.accountNumber != accountNumber) {
			System.out.println("Account number is not valid. Transaction is not posible!");
		}
	}

	public int get_accountNumber() {
		return accountNumber;
	}

	public String get_clientName() {
		return client.getName();
	}

	public double get_accountAmount() {
		return accountAmount;
	}	
	
	public String accountBalance() {
		return "———————————————————————————————————————————————\n"+
				" ID racuna: " + accountNumber + "\n Korisnik: " + get_clientName() +
				"\n Trenutni iznos na racunu: " + accountAmount + " KM " +
				"\n———————————————————————————————————————————————";		
	}
	
}