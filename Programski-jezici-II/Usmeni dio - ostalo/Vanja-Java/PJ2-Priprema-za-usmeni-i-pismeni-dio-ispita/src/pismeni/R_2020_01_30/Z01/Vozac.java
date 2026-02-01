package pismeni.R_2020_01_30.Z01;

public class Vozac{
	String ime, prezime;
	static int count;

	public Vozac(){
		ime = "IME " + (++count);
		prezime = "PREZIME " + count;
	}

	public Vozac(String ime, String prezime){
		this.ime = ime;
		this.prezime = prezime;
	}

	public String toString(){
		return ime + " " + prezime;
	}
}