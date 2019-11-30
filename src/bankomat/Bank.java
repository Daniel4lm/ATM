package bankomat;

public class Bank {
	
	private String bankName;
	private String location;
	
	public Bank() {
		// default contructor
	}
	
	public Bank(String bankName, String location){
		this.bankName = bankName;
		this.location = location;
	}

	public String get_bankName() {
		return this.bankName;
	}

	public String get_location() {
		return this.location;
	}

	@Override
	public String toString() {
		return "———————————————————————————————————————————————\n"+
				" Naziv banke: " + bankName + "\n Lokacija: " + location +
				"\n———————————————————————————————————————————————";
	}
	
	

}
