package pismeni.R2018_09_06.Z03;

public class Objekat implements Comparable<Objekat>{

	public int kljuc;
	public String vr;

	public Objekat(){
		super();
	}

	public Objekat(int kljuc, String vr){
		this.kljuc = kljuc;
		this.vr = vr;
	}

	public String toString(){
		return kljuc + " " + vr;
	}

	public int compareTo(Objekat o){
		return Integer.compare(kljuc, o.kljuc);
	}
}