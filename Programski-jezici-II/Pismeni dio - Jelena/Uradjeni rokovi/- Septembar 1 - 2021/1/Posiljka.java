import java.io.*;

class Posiljka implements Serializable{
	String adresaPrimaoca, adresaPosiljaoca;
	int tezina;
	static int id = 1;
	int ID;
	
	Posiljka(int t){
		adresaPosiljaoca = "Adresa posiljaoca " + Main.rand.nextInt(1,20);
		adresaPrimaoca = "Adresa primaoca " + Main.rand.nextInt(1,20);
		tezina = t;
		ID = id++;
	}
	
	
	@Override
	public String toString(){
		return "Posiljka: " + adresaPosiljaoca + " " + adresaPrimaoca + " Tezina: " + tezina;
	}
}