import java.util.*;
import java.io.Serializable;

class Student implements Serializable{
	String ime, prezime;
	int brIndeksa;
	List<Integer> identifikatori;
	
	Student(String i, String p, int br, List<Integer> id){
		ime = i;
		prezime = p;
		brIndeksa  =br;
		identifikatori = id;
	}
	
}