package bankomat;

public class Client {
	
	private String name;
	private String surename;
	private double jmbg;
	
	public Client() {
		// default contructor for Client
	}
	
	/* Arq-constructors for Client */
	public Client(String name){
		this.name = name;
	}
	
	public Client(String name, String surename, double jmbg){
		this.name = name;
		this.surename = surename;
		this.jmbg = jmbg;
	}
	
	/* Getters and setters */
	public String getName() {
		return name;
	}

	public String getSurename() {
		return surename;
	}

	public double getJmbg() {
		return jmbg;
	}

	@Override
	public String toString() {
		return  "———————————————————————————————————————————————\n"+
				"Podaci o klijentu:\n"+
				" Ime klijenta: " + name + "\n Prezime: " + surename +
				"\n JMBG: " + jmbg +
				"\n———————————————————————————————————————————————";
	}
	
	

}
