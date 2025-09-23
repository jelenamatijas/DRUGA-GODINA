import java.util.*;

public class Student implements Podatak {
	
	String ime;
	String prezime;
	String brojIndeksa;
	
	private static int redniBroj = 1;
	
	public Student() {
		ime = "Ime" + redniBroj;
		prezime = "Prezime" + redniBroj;
		brojIndeksa = "Indeks" + redniBroj;
		redniBroj++;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.ime, this.prezime, this.brojIndeksa);
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null || this.getClass() != object.getClass()) {
			return false;
		} else {
			Student student = (Student) object;
			return this.ime.equals(student.ime) && this.prezime.equals(student.prezime) && this.brojIndeksa.equals(student.brojIndeksa);
		}
	}
	
	@Override
	public String toString() {
		return "Student{ime=" + ime + ", prezime=" + prezime + ", brojIndeksa=" + brojIndeksa + "}\n";
	}
}