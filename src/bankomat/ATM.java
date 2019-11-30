package bankomat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	
	/* Private fields for accounts, transfers, file paths, ... */
	private ArrayList<Account> accounts = new ArrayList<Account>();	
	private ArrayList<Transfer> transfers = new ArrayList<Transfer>();
	
	/* Main method where this app starts */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		// ATM object which represents bank automat
		ATM bankomat = new ATM();
		
		bankomat.start(input);
		
	}
	
	/* MAIN METHOD FOR PROGRAM STARTING */
	public void start(Scanner sc) {	
		
		System.out.printf("———————————————————————————————————————————————————————————————%n");
		System.out.printf("****%15sWELCOME TO ATM MASCHINE%10s****%n","","");
		System.out.printf("———————————————————————————————————————————————————————————————%n%n");
		System.out.printf("%15sPlease choose one of the options:%n%n","");

		int choose;
		
		/* Try to read account values from file */
		try {
			FileIO.accountsInputFile(accounts);
		} catch (IOException e) {
			System.out.printf("%n");
			System.out.printf("**** DATOTEKA SA RACUNIMA NE POSTOJI ILI JE OSTECENA ****%n");
			System.out.printf("****       NOVA DATOTEKA CE BITI KREIRANA            ****%n%n");
		}
		
		/* Main loop of the program */
		do {
			
			intro(); // print every time intro message
			
			if((choose = Helper.returnIntValue(sc)) ==  -1) {
				choose = 1;
				continue;
			}
						
			switch (choose) {
			/* Choose processing */
			case 0:					
				System.out.println("End of the program!\n");
				break;				
			case 1:				
				this.kreiranjeRacuna(sc);				
				break;				
			case 2:				
				this.moneyTransfer(sc);				
				break;
			case 3:				
				this.listOfAccounts();				
				break;								
			default:				
				System.out.println("Pogresan unos sa komandne linije!\n");				
				break;
			}			
		}while(choose != 0);	
		
		/* Try to write account values to file */
		try {
			FileIO.accountsOutputFile(accounts);
		} catch (IOException e) {			
			System.out.printf("%n");
			System.out.printf("**** NIJE MOGUCE PISATI U DATOTEKU ****%n.");
			System.out.printf("****     PROVJERITE ISPRAVNOST	  ****%n%n");			
		}
		
	}
	
	/* INTRO MESSAGE WITH OPTIONS */
	public void intro() {		
	    System.out.println("1 - Kreiranje novog racuna.");
	    System.out.println("2 - Prebacivanje novca sa jednog racuna na drugi.");
	    System.out.println("3 - spisivanje detalja postojećih računa");	    
	    System.out.println("0 - Izlaz iz programa");
	    System.out.println();		
	}
	
	/* All checks related to accounts */
	private int checkAccount(Scanner sc) {	
		
		int rBr = 1;
				
		while(rBr > 0) {
			
			if((rBr = Helper.returnIntValue(sc)) ==  -1) {
				rBr = 1;
				continue;
			}
				
			if(checkIfAccountExists(rBr) == 0) {
				System.out.println("Broj racuna ne postoji. Da li zelite unijeti drugi broj? (d,D)-da (ost)-ne\n");
				char question = sc.next().charAt(0);
				if(question == 'd' || question == 'D') {
					continue;
				}else if(question == 'n' || question == 'N') {
					return 0;
				}else {
					continue;
				}
			}else {				
				break;
			}
		}
		
		return rBr;		
	}
	/* Method fot money transfer */
	public void moneyTransfer(Scanner sc) {
		
		int sourceAccount = 1;
		int targetAccount = 1;
		double amount = 1;
		
		Account r1, r2; // Temporary references for two accounts
		
		/* Data input and checking the first account */
		System.out.print("Unesite broj prvog racuna: ");		
		sourceAccount = checkAccount(sc);
		
		if(sourceAccount == 0) {
			return;
		}		
		
		/* Data input and checking the second account */
		System.out.print("Unesite broj drugog racuna: ");		
		targetAccount = checkAccount(sc);
		
		if(targetAccount == 0) {
			return;
		}		

		System.out.print("Unesite iznos novca koji prebacujete: ");		
		
		while(amount > 0) {			
			if((amount = Helper.returnDoubleValue(sc)) ==  -1) {
				amount = 1;
				continue;
			}else {
				break;
			}
		}
		
		/* Temporary referencing for account objects */
		r1 = getAccount(sourceAccount);
		r2 = getAccount(targetAccount);
		
		/* Check if there is enough money in account*/
		if (r1.checkAccount(amount) == false) {
			System.out.println("\n >> Iznos na izvornom racunu nije dovoljan za transakciju! TRANSAKCIJA TERMINIRANA. <<\n");
			return;
		}
		
		transfers.add(new Transfer(sourceAccount, targetAccount, amount));
		
		r1.takeAmount(sourceAccount, amount);
		r2.addAmount(targetAccount, amount);
		
		System.out.println("\n >> TRANSAKCIJA USPJESNO OBAVLJENA - Molimo pogledajte stanje na vasem racunu.  <<\n");
		
	}
	
	/* Return temporary reference to the Account object */
	private Account getAccount(int idAcc) {
		Account pR = null;
		for(Account account : accounts) {
			if(account.get_accountNumber() == idAcc)
				pR = account;
		}
		return pR;
	}
	
	/* Check if account actually exists */
	private int checkIfAccountExists(int nAcc) {
		
		for(Account account : accounts) {
			if(account.get_accountNumber() == nAcc)
				return -1;
		}
		return 0;
	}
	
	/* Print the list of all accounts and its informations */
	public void listOfAccounts() {
				
		if(accounts.size() == 0) {
			System.out.printf(">> U sistemu nema kreiranih racuna! <<%n%n");
			return;
		}
		
		System.out.printf("%S %d%n", "ukupan broj racuna po stanjima:", accounts.size());
		
		for(Account account : accounts) {
			System.out.println(account.accountBalance());
		}
		
		System.out.printf("************************************************%n%n");
		
	}	
	
	/* Create a new account */
	public void kreiranjeRacuna(Scanner sc) {
				
		System.out.print("Unesite broj racuna: ");
		int nmAcc = 1;
		/* All checks related to accounts */
		while(nmAcc > 0) {
			
			if((nmAcc = Helper.returnIntValue(sc)) ==  -1) {
				nmAcc = 1;
				continue;
			}
			if(nmAcc < 0) {
				System.out.println("Broj racuna ne moze biti negativan. Unesite ponovo broj!\n");
				nmAcc = 1;
				continue;
			}else if(checkIfAccountExists(nmAcc) == -1) {
				System.out.println("Broj racuna vec postoji. Da li zelite unijeti drugi broj? (d,D)-da (ost)-ne\n");
				char question = sc.next().charAt(0);
				if(question == 'd' || question == 'D') {
					continue;
				}else if(question == 'n' || question == 'N') {
					break;
				}else {
					continue;
				}
			}
			else {				
				break;
			}
		}
		
		System.out.print("Unesite ime korisnika: ");
		String nameCust = new String("-");

		while(nameCust.length() > 0) {
			
			if((nameCust = Helper.checkName(sc)) ==  null) {
				nameCust = "-";
				continue;
			}else {
				break;
			}			
		}
		
		System.out.print("Unesite iznos novca za uplatu na racun: ");
		double amountNR = 1;
		
		while(amountNR > 0) {
			
			if((amountNR = Helper.returnDoubleValue(sc)) ==  -1) {
				amountNR = 1;
				continue;
			}
			
			if(amountNR < 0) {
				System.out.println("Iznos na racunu ne moze biti negativan. Unesite pozitivan iznos!\n");
				amountNR = 1;
				continue;
			}else {				
				break;
			}
		}		
		
		System.out.println();
		
		accounts.add(new Account(nmAcc, nameCust, amountNR));		
		
	}	
}