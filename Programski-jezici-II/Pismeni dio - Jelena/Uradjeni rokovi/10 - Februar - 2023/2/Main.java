import java.io.*;
import java.util.*;

class Main{
	
	static Object lock = new Object();
	static public void main(String args[]){
		if(args.length!=1){
			System.out.println("Nije unesen argument.");
			return;
		}
		
		Random rand = new Random();
		String kombinacija = rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10);
		try(PrintWriter pw = new PrintWriter("tombola.txt")){
			pw.println(kombinacija);
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl.");
		}
		
		List<Nit> niti = new ArrayList<>();
		
		for(int i=0; i<Integer.parseInt(args[0]);i++){
			niti.add(new Nit());
		}
		for(Nit n : niti){
			n.start();
		}
		
		
	}
}