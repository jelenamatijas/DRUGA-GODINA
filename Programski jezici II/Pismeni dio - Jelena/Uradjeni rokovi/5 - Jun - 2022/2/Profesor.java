import java.util.*;
import java.io.Serializable;

class Profesor implements Serializable{
	String ime, prezime;
	int jmb;
	List<Integer> identifikatori;
	
	Profesor(String i, String p, int br, List<Integer> id){
		ime = i;
		prezime = p;
		jmb  =br;
		identifikatori = id;
	}
	
}