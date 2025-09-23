import java.util.*;

class Zadatak{
	String opis, naziv;
	int prioritet;
	Vrsta vrsta;
	String developer;
	int trajanjeRada;
	
	static int id = 1;
	
	public Zadatak(){
		opis = "Opis" + id;
		naziv = "Naziv" + id;
		prioritet = (new Random()).nextInt(5)+1;
		if(id % 3 ==0){
			vrsta = Vrsta.TASK;
		}else if(id % 3 == 1){
			vrsta = Vrsta.BUG;
		}else{
			vrsta = Vrsta.STORY;
		}
		developer = "";
		trajanjeRada = 0;
		id++;
	}
	
	public int getPrioritet(){
		return prioritet;
	}
	
	@Override
	public String toString(){
		return opis + " " + naziv + " Prioritet: " + prioritet + " Vrsta: " + vrsta + " Uradio:" + developer + " Vrijeme rada: " + trajanjeRada;
	}
	
	public enum Vrsta{
		TASK, BUG, STORY;
	}
}