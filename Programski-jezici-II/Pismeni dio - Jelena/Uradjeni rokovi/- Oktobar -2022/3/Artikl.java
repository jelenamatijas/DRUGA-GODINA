import java.util.*;

class Artikl{
	String naziv;
	Double cijena;
	List<String> barkodovi = new ArrayList<>();
	static int id = 1;
	
	Artikl(){
		naziv = "Artikl_" +id;
		cijena = Main.rand.nextDouble(10, 100);
		int x = Main.rand.nextInt(1, 7);
		for(int i=0;i<x;i++){
			barkodovi.add("Barkod_"+i);
		}
		id++;
	}
	
	@Override
	public String toString(){
		return naziv + " " + cijena + " " + barkodovi;
	}
	
}