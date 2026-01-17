import java.util.*;

class Main{
	static Random rand = new Random();
	static PriorityQueue<Osoba> osobe = new PriorityQueue<>();
	static Object lock = new Object();
	
	static public void main(String args[]){
		List<Vozilo> vozila = new ArrayList<>();
		for(int i=0;i<5;i++){
			vozila.add(new Motor());
			vozila.add(new Kamion());
			vozila.add(new Automobil());
			vozila.add(new Autobus());
		}
		
		for(int j=0;j<10;j++){
			List<Vozilo> dodaj = new ArrayList<>();
			int y = rand.nextInt(1,6);
			for(int i=0;i<y;i++){
				dodaj.add(vozila.get(rand.nextInt(0,20)));
			}
			try{
				osobe.add(new Pravno(dodaj));
				dodaj.clear();
				dodaj.add(vozila.get(rand.nextInt(0,20)));
				osobe.add(new Fizicko(dodaj));
			}catch(Exception e){
				System.out.println("Greska pri dodavanju osoba u red.");
			}
		}
		
		Salter s1 = new Salter();
		Salter s2 = new Salter();
		s1.start();
		s2.start();
	}
	
}