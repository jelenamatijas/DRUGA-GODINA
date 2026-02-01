import java.util.*;

class Main{
	static public Random rand = new Random();
	static Object lock = new Object();
	static List<GrupaGostiju> grupeGostiju = new ArrayList<>();
	
	static public void main(String args[]){
		for(int i=0;i<50;i++){
			grupeGostiju.add(new GrupaGostiju());
		}
		
		System.out.println("Grupe gostiju su kreirane:");
		grupeGostiju.forEach(g -> System.out.println(g));
		
		List<Restoran> restorani = new ArrayList<>();
		for(int i=0;i<5;i++){
			restorani.add(new Restoran());
		}
		
		for(Restoran r:restorani){
			r.start();
		}
	}
}