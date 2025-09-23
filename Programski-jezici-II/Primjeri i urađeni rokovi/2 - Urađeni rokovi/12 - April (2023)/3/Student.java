import java.util.*;

public class Student {
	
	String ime;
	String prezime;
	String brojIndeksa;
	public double prosjecnaOcjena;
	
	private static int redniBroj = 1;
	private static Random random = new Random();
	
	public Student() {
		ime = "ime" + redniBroj;
		prezime = "prezime" + redniBroj;
		brojIndeksa = redniBroj + "-" + (random.nextInt(4) + 2021);
		prosjecnaOcjena = random.nextInt(4) + 6 + random.nextDouble();
		redniBroj++;
	}
	
	public double getProsjecnaOcjena() {
        return prosjecnaOcjena;
    }
	
	@Override
	public String toString() {
		return "Student{ime=" + ime + ", prezime=" + prezime + ", brojIndeksa=" + brojIndeksa + ", prosjecnaOcjena=" + prosjecnaOcjena + "}";
	} 
}