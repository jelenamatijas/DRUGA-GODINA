import java.util.*;
import java.io.*;

class Main{
	public static Random rand = new Random();
	
	public static void main(String []args){
		Senzor[] senzori1 = new Senzor[2];
		senzori1[0] = new SenzorPritisak("VARENJE-pritisak");
		senzori1[1] = new SenzorTemperature("VARENJE-temperatura");
		
		Senzor[] senzori2 = new Senzor[3];
		senzori2[0] = new SenzorPritisak("SJECENJEpritisak");
		senzori2[1] = new SenzorTemperature("SJECENJE-temperatura");
		senzori2[2] = new SenzorVibracija("SJECENJE-vibracija");
		RadnaMasina varenje = new RadnaMasina("MASINA ZA VARENJE", senzori1);
		RadnaMasina sjecenje = new RadnaMasina("MASINA ZA SJECENJE", senzori2);
		
		varenje.upali();
		sjecenje.upali();
		
		Scanner scanner = new Scanner(System.in);
		String linija = "";
		while(true){
			linija = scanner.nextLine().trim();
			if(linija.equalsIgnoreCase("KRAJ")){
				System.out.println("ZAUSTAVLJAM RAD");
				varenje.ugasi();
				sjecenje.ugasi();
				break;
			}
		}
		
	}
}