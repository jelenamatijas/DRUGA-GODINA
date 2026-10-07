import java.util.*;

class Fakultet{
	String naziv;
	List<Student> studenti;
	
	Fakultet(String n){
		naziv = n;
		studenti = new ArrayList<>();
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Student st:studenti){
			s +="\t\n" + st;
		}
		return naziv + s;
	}
}