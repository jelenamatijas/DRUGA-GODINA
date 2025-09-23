import java.util.*;
import java.io.File;

class Main{
	static Random rand = new Random();
	public static ArrayList<Object> gosti = new ArrayList<>();
	public static Map<Integer, Object> gostiMap = new HashMap<>();
	public static PriorityQueue<Object> sauna = new PriorityQueue<>(Comparator.comparing((Object gost) -> !(gost instanceof FirmaInterface)));
	public static ArrayList<Object> masaza = new ArrayList<>();
	
	public static Object lock = new Object();
	
	public static void main(String []args){
		
		File file = new File("pristupSauniOtkazan.txt");
		ArrayList<Object> sviGosti = new ArrayList<>();
		
		System.out.println("=========================== SVI RASPOLOZIVI GOSTI ==========================");
		for(int i=0; i< 15; i++){
			gosti.add(new GostTrosakFirme());
			gosti.add(new GostLicniTrosak());
		}
		sviGosti.addAll(gosti);
		gosti.forEach(System.out::println);
		System.out.println("========================== GOSTI KOJI IDU U SAUNU ==========================");
		
		for(int i=0; i<10;){
			int index = rand.nextInt(gosti.size());
			if(!gostiMap.containsKey(index)){
				((Gost)gosti.get(index)).sauna = true;
				gostiMap.put(index, gosti.get(index));
				gosti.remove(index);
				i++;
				sauna.add(gostiMap.get(index));
			}
		}
		
		sauna.forEach(System.out::println);
		System.out.println("========================== GOSTI KOJI IDU NA MASAZU ==========================");
		
		gostiMap.clear();
		for(int i=0; i<5;){
			int index = rand.nextInt(gosti.size());
			if(!gostiMap.containsKey(index)){
				((Gost)gosti.get(index)).masaza = true;
				gostiMap.put(index, gosti.get(index));
				gosti.remove(index);
				i++;
				masaza.add(gostiMap.get(index));
			}
		}
		masaza.forEach(System.out::println);
		
		System.out.println("========================== GOSTI KOJI IDU NA BAZEN ==========================");
		for(Object obj : gosti){
			((Gost) obj).bazen = true;
		}
		gosti.forEach(System.out::println);
		
		System.out.println("==============================================================================");
		
		for(Object obj : sviGosti){
			((Gost) obj).run();
		}
		
		try{
			for(Object obj : sviGosti){
				((Gost) obj).join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju gostiju.");
		}
		
		for(Object obj : sviGosti){
			if(!(obj instanceof FirmaInterface)){
				System.out.println(((GostLicniTrosak) obj).ime + " " + ((GostLicniTrosak) obj).prezime + " Novac: " + ((GostLicniTrosak) obj).novac);
			}
		}
	}
}