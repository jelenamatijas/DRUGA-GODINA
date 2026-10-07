package pismeni.R_2021_07_30.Z01;

public class Pivo extends Artikal implements ArtikalZaPlus18{

	public Pivo(){
		super();
		cijena = 1.00;
	}

	public String toString(){
		return "PIVO " + super.toString();
	}
}