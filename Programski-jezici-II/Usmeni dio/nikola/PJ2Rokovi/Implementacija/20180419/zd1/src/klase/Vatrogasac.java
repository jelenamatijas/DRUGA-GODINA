package src.klase;

import java.util.Random;

public class Vatrogasac extends Takmicar{
	
	
	public boolean savladaj(Object p){
		if(p instanceof Vatra && !(p instanceof Stijena) && new Random().nextBoolean())
			return true;
		if(p instanceof Voda && !(p instanceof Stijena) && new Random().nextInt(10) > 2)
			return true;
		return false;
	}
	
	@Override 
	
	public String toString(){
		return "Vatrogasac" + ime;
	}
}