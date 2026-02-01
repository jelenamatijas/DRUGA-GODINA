package pismeni.R_2021_07_30.Z01;

public class Cigarete extends Artikal implements ArtikalZaPlus18{

	public Cigarete(){
		super();
		cijena = 5.00;
	}

	public String toString(){
		return "CIGARETE " + super.toString();
	}
}