import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

class Alarm implements AkcijaInterface{
	static int ID=1;
	int id;
	String opis;
	LocalDate date;
	
	Alarm(){
		id = ID++;
		opis = "OPIS_"+id;
		date = LocalDate.now();
	}
	
	public String getOpis(){return opis;}
	
	public int getPrioritet(){return 1;}
	
	public void runAction(){
		System.out.println(id + " " + opis);
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
		return "ALARM: " + id +" " + opis + " " + date;
	}
}