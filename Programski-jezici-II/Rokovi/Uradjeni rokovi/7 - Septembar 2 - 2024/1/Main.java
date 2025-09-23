import java.util.*;

class Main{
	static PriorityQueue<Zadatak> zadaci = new PriorityQueue<>(Comparator.comparingInt(Zadatak::getPrioritet));
	static PriorityQueue<Zadatak> uradjeniZadaci = new PriorityQueue<>(Comparator.comparingInt(Zadatak::getPrioritet));

	public static void main(String []args){
		ProductOwner po = new ProductOwner("Petar", "Petrovic", 10, "Vlasnik proizvoda");
		ScrumMaster sm = new ScrumMaster("Djuro","Djurovic", 5);
		Developer[] developeri = new Developer[3];
		developeri[0] = new Developer("Marko", "Markovic", 3);
		developeri[1] = new Developer("Pero", "Perovic", 8);
		developeri[2] = new Developer("Mika", "Mikic", 2);
		
		po.start();
		sm.start();
		developeri[0].start();
		developeri[1].start();
		developeri[2].start();
	}
}