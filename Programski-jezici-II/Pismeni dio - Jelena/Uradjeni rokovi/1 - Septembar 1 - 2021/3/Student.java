import java.util.*;

class Student implements PodatakInterface{
	String ime, prezime;
	int brojIndeksa;
	static int id = 1;
	
	Student(){
		ime = "Ime_"+id;
		prezime = "Prezime_"+id;
		brojIndeksa = id;
		id++;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(ime, student.ime) &&
               Objects.equals(prezime, student.prezime) &&
               Objects.equals(brojIndeksa, student.brojIndeksa);
    }
	
	@Override
	public int hashCode(){
		return Objects.hash(ime, prezime, brojIndeksa);
	}
	
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " + brojIndeksa;
	}
}