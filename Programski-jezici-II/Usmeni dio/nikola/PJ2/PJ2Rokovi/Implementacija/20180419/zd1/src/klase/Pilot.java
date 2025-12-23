package src.klase;

import java.util.Random;

public class Pilot extends Takmicar{
	
	
	public boolean savladaj(Object p){

		if(p instanceof Stijena && !(p instanceof Vatra) && !(p instanceof Voda) && new Random().nextInt(10) > 2)
			return true;
		return false;
	}
	
	@Override 
	
	public String toString(){
		return "Pilot" + ime;
	}
}