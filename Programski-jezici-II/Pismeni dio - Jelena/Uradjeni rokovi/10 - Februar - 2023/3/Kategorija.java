import java.util.*;

class Kategorija{
	String naziv;
	List<Kategorija> podkategorije;
	
	public Kategorija(String naziv){
		this.naziv = naziv;
		this.podkategorije = new ArrayList<>();
	}
	
	public int brojPodkategorija(){
		int broj = podkategorije.size();
		for(Kategorija k : podkategorije){
			broj += k.brojPodkategorija();
		}
		return broj;
	}
	
	@Override
	public String toString(){
		return naziv;
	}
}