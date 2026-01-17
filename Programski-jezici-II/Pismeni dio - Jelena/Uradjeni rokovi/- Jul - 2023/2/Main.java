import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static Object lock = new Object();
	static Random rand = new Random();
	static Path path;
	static int broj = 0;
	
	static public void main(String args[]){
		if(args.length != 3){
			System.out.println("GRESKA: pogresna format");
			return;
		}
		int n = Integer.parseInt(args[0]);
		broj = Integer.parseInt(args[1]);
		path = Paths.get(args[2]);
		
		List<Nit> niti = new ArrayList<>();
		for(int i=0;i<n;i++){
			niti.add(new Nit());
		}
		
		System.out.println("Niti se pokrecu, takmicenje pocinje.");
		for(Nit nit : niti){
			nit.start();
		}
		
		try{
			for(Nit nit : niti){
				nit.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju niti.");
		}
		
		System.out.println("Takmicenje gotovo.");
	}
}