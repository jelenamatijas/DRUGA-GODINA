import java.util.*;
import java.util.stream.*;
import java.util.Arrays;
import java.io.*;

class SenzorPritisak extends Senzor{
	int index;
	public SenzorPritisak(String s){
		super();
		senzorName = s;
		data = new Integer[5];
		index = 0;
	}
	
	@Override
	public void ocitajVrijednost(){
		Integer t = Main.rand.nextInt(50)+11;
		try(PrintWriter pw = new PrintWriter(new FileWriter(senzorName + ".txt", true))){
			pw.println("Ocitana nova vrijednost PRITISKA: " + t);
			System.out.println(senzorName + " Ocitana nova vrijednost PRITISKA: " + t);
			if(data[4] != null){
				pw.println("Prosjecna vrijednost pritiska: " + average());
			}
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl: " + senzorName + ".txt");
		}
		data[index % 5] = t;
		index++;
	}
	
	public double average(){
		return Arrays.stream(data).mapToDouble(Integer::doubleValue).average().getAsDouble();
	}
}