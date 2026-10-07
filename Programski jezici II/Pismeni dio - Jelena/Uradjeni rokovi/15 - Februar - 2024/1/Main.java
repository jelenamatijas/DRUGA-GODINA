import java.util.*;

class Main{
	
	public static Random rand = new Random();
	public static Object lock = new Object();
	public static boolean cekaj = false;
	
	public static void main(String args[]){
		AlarmWatcher alarm = new AlarmWatcher();
		alarm.start();
		
		ArrayList<Robot> roboti = new ArrayList<>();
		
		for(int i=0; i<10; i++){
			Robot r;
			if(i%2 == 0){
				r = new Robot("MONTAZNI", new Senzor("opticki"));
			}else{
				r = new Robot("MONTAZNI", new Senzor("ultrazvucni"));
			}
			roboti.add(r);
		}
		
		for(int i=0; i<10; i++){
			Robot r;
			if(i%2 == 0){
				r = new Robot("CISTACKI", new Senzor("opticki"));
			}else{
				r = new Robot("CISTACKI", new Senzor("ultrazvucni"));
			}
			roboti.add(r);
		}
		
		for(int i=0; i<10; i++){
			Robot r;
			if(i%2 == 0){
				r = new Robot("ISTRAZIVACKI", new Senzor("opticki"));
			}else{
				r = new Robot("ISTRAZIVACKI", new Senzor("ultrazvucni"));
			}
			roboti.add(r);
		}
		
		for(Robot r : roboti){
			r.start();
		}
		
		try{
			for(Robot r : roboti){
				r.join();
			}
		}catch(InterruptedException e){
			System.out.println("Greska pri unistavanju niti.");
		}
		
		alarm.radi = false;
	}
}