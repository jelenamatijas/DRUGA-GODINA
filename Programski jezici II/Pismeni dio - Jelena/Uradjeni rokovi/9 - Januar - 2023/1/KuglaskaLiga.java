import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class KuglaskaLiga{
	static Random rand = new Random();
	
	public static void main(String args[]){
		List<Liga> lige = new ArrayList<>();
		for(int i=0;i<8;i++){
			lige.add(new Liga());
		}
		for(Liga l : lige){
			l.start();
		}
		
		try{
			for(Liga l : lige){
				l.join();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		System.out.println("Prvi krug je zavrsio.");
		System.out.println("=================== Rezulatati ===================");
		
		for(Liga l : lige){
			l.rezultat();
		}
		
		Tim[] pobjednickiTimovi = new Tim[8];
		for(int i=0;i<8;i++){
			pobjednickiTimovi[i] = lige.get(i).timovi[7];
		}
		
		Liga zavrsna = new Liga(pobjednickiTimovi);
		zavrsna.start();
		try{
			zavrsna.join();
		}catch(Exception e){
			System.out.println(e);
		}
		
		System.out.println("Drugi krug je zavrsio.");
		System.out.println("=================== Rezulatati ===================");
		
		zavrsna.rezultat();
		
	}
}