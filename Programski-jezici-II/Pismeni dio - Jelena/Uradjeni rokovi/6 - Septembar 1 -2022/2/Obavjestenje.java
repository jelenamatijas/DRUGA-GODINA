import java.util.*;
import java.util.function.Predicate;

class Obavjestenje implements AkcijaInterface{
	String opis;
	String naslov;
	static int ID=1;
	
	Obavjestenje(){
		opis = "OPIS_"+ID;
		naslov = "NASLOV_"+ID;
		ID++;
	}
	
	public String getOpis(){return opis;}
	
	public int getPrioritet(){return 2;}
	
	public void runAction(){
		System.out.println(naslov);
	}
	
	@Override
	public int compareTo(AkcijaInterface other){
		return Integer.compare(getPrioritet(), other.getPrioritet());
	}
	
	public boolean check(List<Predicate<AkcijaInterface>> pp){
		for(Predicate p:pp){
			if(!p.test(this)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		return "OBAVJESTENJE: " + naslov + " " + opis;
	}
}