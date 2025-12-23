package src.klase;

import java.util.Random;

public class Planinar extends Takmicar{
	
	
	public boolean savladaj(Object p){
		if(p instanceof Vatra && !(p instanceof Voda) && !(p instanceof Stijena))
			return false;
		if(new Random().nextInt(10) > 2)
			return true;
		return false;
	}
	
	@Override 
	
	public String toString(){
		return "Planinar" + ime;
	}
}