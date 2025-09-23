import java.util.*;
import java.io.*;

class SenzorTemperature extends Senzor{
	int index;
	
	public SenzorTemperature(String s){
		super();
		senzorName = s;
		data = new Integer[10];
		index = 0;
	}
	
	@Override
	public void ocitajVrijednost(){
		Integer t = Main.rand.nextInt(30)+1;
		try(PrintWriter pw = new PrintWriter(new FileWriter(senzorName + ".txt", true))){
			pw.println("Ocitana nova vrijednost TEMPERATURE: " + t);
			System.out.println(senzorName + " Ocitana nova vrijednost TEMPERATURE: " + t);
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl: " + senzorName + ".txt");
		}
		
		data[index % 10] = t;
		index++;
	}
}