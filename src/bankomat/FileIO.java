package bankomat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {	
	
	/* Method that reads accouns from the file */
	static ArrayList<Account> accountsInputFile(ArrayList<Account> list) throws IOException {
		
		Path path = Paths.get("racuni.txt");	
		Scanner input = new Scanner(path);
		
		/* In case that file doesnt exists */
		if(!Files.exists(path)) {
			System.out.println("Fajl ne postoji. Kreiranje novog fajla. ");
			Files.createFile(path);
		}
		
		/* Read the lines from the file */
		while (input.hasNext()) {
			
			int accnum = input.nextInt();
			String namecust = input.next();
			double monam = Double.parseDouble(input.next());
			
			list.add(new Account(accnum, namecust, monam));
			
		}		
		input.close();
		
		return list;
	}
	
	/* Method that writes accouns to the file */
	static void accountsOutputFile(ArrayList<Account> list) throws IOException {
		
		Path path = Paths.get("racuni.txt");
		BufferedWriter writer = Files.newBufferedWriter(path);
		
		/* In case that file doen't exists */
		if(!Files.exists(path)) {
			Files.createFile(path);
		}
		
		/* Write new accounts to the file */		
		for(Account spRac : list) {
			writer.write(spRac.get_accountNumber()+" "+spRac.get_clientName()+" "+spRac.get_accountAmount());
			writer.newLine();
		}			
		writer.close();
	}	

}
