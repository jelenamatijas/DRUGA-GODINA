import java.util.*;

class Student implements Comparable<Student>{
	String ime, prezime, indeks;
	double ocjena;
	static int id = 1;
	
	Student(){
		ime = "Ime_" + id;
		prezime = "Prezime_" + id;
		indeks = id + "-2025";
		ocjena = Math.round(Main.rand.nextDouble(6, 10.01) * 100.0) / 100.0;
		id++;
	}
	
	@Override
	public int compareTo(Student o){
		int cmp = Double.compare(o.ocjena, this.ocjena); 
		if(cmp != 0){
			return cmp;
		}
		return indeks.compareTo(o.indeks);
	}
	
	public double getOcjena(){
		return ocjena;
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " +indeks + " " + ocjena;
	}
}