package src.gost;

import java.util.Random;

public class ObicniGost extends Gost{
	public Double novac;
	
	public ObicniGost(){
		novac = new Random().nextDouble()*200 + 10;
	}
	
	@Override
	public String toString(){
		return "Obicni " + super.toString();
	}
}