import java.util.*;
import java.util.Collections;

class Tim{
	List<Igrac> igraci = new ArrayList<>();
	String nazivTima;
	int osvojeniBodovi;
	static int id = 1;
	Tim(){
		igraci.add(new AmaterPocetnik());
		igraci.add(new AmaterNapredni());
		igraci.add(new PlaceniProfesionalac());
		igraci.add(new NeplaceniProfesionalac());
		try{
			Collections.shuffle(igraci);
		}catch(Exception e){
			System.out.println("Greska pri mijesanju igraca unutar tima.");
		}
		osvojeniBodovi = 0;
		nazivTima = "Tim_" + id;
		id++;
	}
	
	@Override
	public String toString(){
		return "\nNaziv tima: " + nazivTima + " Broj bodova: " + osvojeniBodovi;
	}
}