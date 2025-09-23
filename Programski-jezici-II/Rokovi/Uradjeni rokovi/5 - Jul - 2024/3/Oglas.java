import java.util.*;

class Oglas{
	String naziv;
	String opis;
	String datumObjavljivanja;
	int vrijemeTrajanja;
	int plata;
	int iskustvo;
	boolean remote;
	String grad;
	Kategorija kategorija;
	
	static int ID = 1;
	
	public Oglas(){
		Random rand = new Random();
		naziv = "Naziv" + ID;
		opis = "Opis" + ID;
		datumObjavljivanja = (rand.nextInt(31)+1) + "." + (rand.nextInt(12)+1) + "." + (rand.nextInt(2022,2025)) + ".";
		vrijemeTrajanja = rand.nextInt(5) + 1;
		plata = rand.nextInt(500)+1800;
		iskustvo = rand.nextInt(5) + 1;
		int x = rand.nextInt(5);
		remote = rand.nextBoolean();
		
		if(x % 5 == 0){
			kategorija = Kategorija.IT;
		}else if(x % 5 == 1){
			kategorija = Kategorija.EKONOMIJA;
		}else if(x % 5 == 2){
			kategorija = Kategorija.MEDICINA;
		}else if(x % 5 == 3){
			kategorija = Kategorija.NOVINARSTVO;
		}else{
			kategorija = Kategorija.PRAVO;
		}
		
		x = rand.nextInt(5);
		if(x % 5 == 0){
			grad = "Banjaluka";
		}else if(x % 5 == 1){
			grad = "Prijedor";
		}else if(x % 5 == 2){
			grad = "Doboj";
		}else if(x % 5 == 3){
			grad = "Trebinje";
		}else{
			grad = "Novi grad";
		}
		ID++;
	}
	
	@Override
	public String toString(){
		return "OGLAS: " + naziv + " \n\t" + opis + "\n\tDatum objavljivanja: " + datumObjavljivanja + "\n\tVrijeme trajanja: " + 
						vrijemeTrajanja + "\n\tPlata: " + plata + "\n\tIskustvo: " + iskustvo + "\n\tRemote: " + remote + 
						"\n\tGrad: " + grad + "\n\tKategorija: " + kategorija;
					
	}
	
	
	public enum Kategorija{
		IT, EKONOMIJA, MEDICINA, NOVINARSTVO, PRAVO;
	}
}