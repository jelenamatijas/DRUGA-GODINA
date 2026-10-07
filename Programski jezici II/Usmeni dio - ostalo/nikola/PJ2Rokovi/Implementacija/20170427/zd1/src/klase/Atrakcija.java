package src.klase;

import java.util.Random;

public class Atrakcija{
	Random rand = new Random();
	String ime = Long.toHexString(Double.doubleToLongBits(rand.nextDouble()));
	public int cjena;
	
	public static Crkva newCrkva(){
		return new Crkva();
	}
	
	public static Spomenik newSpomenik(String opis){
		return new Spomenik(opis);
	}
	
	public static Muzej newMuzej(String letak){
		return new Muzej(letak);
	}
	
	public static ZabavniPark newZabavniPark(){
		return new ZabavniPark();
	}
}

class Crkva extends Atrakcija{
	
	public int prilozi;
	
	public Crkva(){
		cjena = rand.nextInt(70) + 10;
	}
	
	@Override 
	
	public String toString(){
		System.out.println("Stanje priloga " + prilozi);
		return "Crkva" + ime;
	}
}

class Muzej extends Atrakcija{
	
	String letak;
	
	public Muzej(String letak){
		
		this.letak = letak;
		cjena = rand.nextInt(70) + 10;
	}
	
	@Override 
	
	public String toString(){
		
		System.out.println("Sadrzaj letka" + letak);
		return "Muzej" + ime;
	}
}

class ZabavniPark extends Atrakcija{
	
	public ZabavniPark(){
		cjena = rand.nextInt(70) + 10;
	}
	
	@Override 
	
	public String toString(){
		return "ZabavniPark" + ime;
	}
}

class Spomenik extends Atrakcija{
	
	public String opis;
	
	public Spomenik(String opis){
		cjena = rand.nextInt(70) + 10;
		this.opis = opis;
	}
	
	@Override 
	
	public String toString(){
		System.out.println("Sadrzaj opisa " + opis);
		return "Spomenik" + ime;
	}
}