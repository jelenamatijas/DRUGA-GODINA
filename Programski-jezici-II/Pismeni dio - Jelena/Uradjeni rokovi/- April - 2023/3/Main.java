import java.util.*;

class Main{
	static Random rand = new Random();
	static List<Student> studenti = new ArrayList<>();
	static Object lock = new Object();
	static List<Student> odabrani = new ArrayList<>();
	
	static public void main(String args[]){
		for(int i=0;i<2000;i++){
			studenti.add(new Student());
		}
		
		Collections.sort(studenti);
		//studenti.forEach(s -> System.out.println(s));
		List<Nit> niti = new ArrayList<>();
		for(int i=0;i<50;i++){
			niti.add(new Nit());
		}
		
		for(Nit n : niti){
			n.start();
		}
		
		try{
			for(Nit n : niti){
				n.join();
			}
		}catch(InterruptedException e){
			System.out.println("Greska pri zaustavljanju niti.");
		}
		
		System.out.println("Odabrani studenti:");
		odabrani.forEach(s -> System.out.println(s));
	}
}