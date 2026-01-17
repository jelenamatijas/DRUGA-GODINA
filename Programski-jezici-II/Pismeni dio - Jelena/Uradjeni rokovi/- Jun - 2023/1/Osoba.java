import java.util.*;

class Osoba implements Comparable<Osoba>{
	String ime, prezime;
	static int id = 1;
	int ID;
	int novac;
	List<Vozilo> vozila = new ArrayList<>();
	
	Osoba(List<Vozilo> v){
		vozila = v;
		ime = "Ime_" + id;
		prezime = "Prezime_" + id;
		ID = id;
		id++;
		novac = Main.rand.nextInt(80,601);
	}
	
	@Override
	public int compareTo(Osoba other){
		return Integer.compare(vozila.size(), other.vozila.size());
	}
	
	@Override 
	public String toString(){
		return ime + " " + prezime + " Novac: " + novac;
	}
}