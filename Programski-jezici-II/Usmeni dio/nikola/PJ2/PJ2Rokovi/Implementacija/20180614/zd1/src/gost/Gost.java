package src.gost;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Gost implements Serializable{
	private String name;
	private Date datumRodjenja;
	private String dodatnaOpcija = null;
	
	protected Gost(){
		name = "Gost" + Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
		datumRodjenja = new Date(Math.abs(System.currentTimeMillis() - new Random().nextLong()));
		switch(new Random().nextInt(5)){
			case 0:
				dodatnaOpcija = "Dodatni obrok";
				break;
			case 1:
				dodatnaOpcija = "Korištenje projektora";
				break;
			case 2:
				dodatnaOpcija = "Korištenje racunara";
				break;
			case 3:
				dodatnaOpcija = "Korištenje džakuzi bazena";
				break;
			case 4:
				dodatnaOpcija = "Koktel";
				break;
		}
	}
	
	public String getdodatnaOpcija(){
		return dodatnaOpcija;
	}
	
	public Date getDatumRodjenja(){
		return datumRodjenja;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}