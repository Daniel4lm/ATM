package bankomat;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
	/* This class is not nedded for object creation */
	private Helper() {}
	
	static String checkName(Scanner tss) {
		
		String strValue = tss.next();
		
		if(strValue.length() < 3) {
			System.out.println("Ime korisnika mora imati barem tri slova!");
			return null;
		}else if(Character.isLowerCase(strValue.charAt(0))) {
			System.out.println("Ime korisnika mora pocinjati velikim slovom!");
			return null;
		}
		
		for(int i = 0; i < strValue.length(); i++) {
			if(Character.isDigit(strValue.charAt(i))) {
				System.out.println("Pogresan unos podataka. Molimo unesite ispravo ime bez brojeva!");
				return null;
			}
		}
		return strValue;
	}
	
	static int returnIntValue(Scanner ts1) {
		
		int intb = 0;
		
		try {
			intb = ts1.nextInt();
		} catch (InputMismatchException iznimka) {
			System.out.println("Wrong data type. Please enter a valid integer value!\n");
			ts1.nextLine();
			return -1;
		}		
		return intb;
	}
	
	static double returnDoubleValue(Scanner ts2) {
		
		double doub = 0;
		
		try {
			doub = ts2.nextDouble();
		} catch (InputMismatchException iznimka) {
			System.out.println("Wrong data type. Please enter a valid double value!\n");
			ts2.nextLine();
			return -1;
		}
		
		return doub;
	}
}
