import java.util.*;
import java.util.Objects;

class Autor{
	String ime, prezime;
	
	public Autor(){
		ime = "Ime" + (new Random()).nextInt(10)+1;
		prezime = "Prezime" + (new Random()).nextInt(10)+1;
	}
	
	@Override
	public String toString(){
		return "Autor{" + ime + " " + prezime + "}";
	}
}