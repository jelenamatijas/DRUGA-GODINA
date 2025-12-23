package javatechnoshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class AnalizaProdaje{
	private static final String NAZIV_FAJLA = "racuni.txt";
	private static final String RACUNARI = "racunari.txt";
	private static final String TELEFONI = "telefoni.txt";
	private static final String MONITORI = "monitori.txt";
	private static final String SUMARNO = "sumarno.txt";
	private static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
}
	public static void main(String args[]) throws IOException{
		BufferedReader racuni = new BufferedReader(new FileReader((new File(NAZIV_FAJLA))));
		File monitoriFIle = new File(MONITORI);
		File telefoniFile = new File(TELEFONI);
		File racunariFile = new File(RACUNARI);
		File sumarnoFile = new File(SUMARNO);
		monitoriFIle.createNewFile();
		telefoniFile.createNewFile();
		racunariFile.createNewFile();
		sumarnoFile.createNewFile();
		System.out.println(monitoriFIle.canWrite());
		System.out.println(sumarnoFile.canWrite());
		System.out.println(racunariFile.canWrite());
		System.out.println(telefoniFile.canWrite());
		BufferedWriter monitori = new BufferedWriter(new FileWriter(monitoriFIle));
		BufferedWriter telefoni = new BufferedWriter(new FileWriter(telefoniFile));
	    BufferedWriter racunari = new BufferedWriter(new  FileWriter(racunariFile));
		BufferedWriter sumarno = new  BufferedWriter(new FileWriter(sumarnoFile));
		double sumMonitori = 0;
		double sumTelefoni = 0;
		double sumRacunari = 0;
		String currLine;
	while((currLine = racuni.readLine()) != null) {
				String[] splitedLine = currLine.split("#");
		double tmpSum = Double.parseDouble(splitedLine[2])*Double.parseDouble(splitedLine[3]);
		if(currLine.startsWith("monitor")) {
		monitori.write(currLine);
		sumMonitori += tmpSum;
		monitori.newLine();
	}
	if(currLine.startsWith("racunar")) {
	    racunari.write(currLine);
		sumRacunari += tmpSum;
		racunari.newLine();
	}

	if(currLine.startsWith("telefon")) {
	   telefoni.write(currLine);
	   sumTelefoni += tmpSum;
	   telefoni.newLine();
	}	
	}
	System.out.println(sumMonitori);
	System.out.println(sumRacunari);
	System.out.println(sumTelefoni);
	sumarno.write("Telefoni: " + sumTelefoni);
	sumarno.newLine();
	sumarno.write("Racunari: " + sumRacunari);
	sumarno.newLine();
	sumarno.write("Monitori: " + sumMonitori );
	sumarno.newLine();
	sumarno.flush();
	monitori.flush();
	telefoni.flush();
	racunari.flush();
	}

		
	}