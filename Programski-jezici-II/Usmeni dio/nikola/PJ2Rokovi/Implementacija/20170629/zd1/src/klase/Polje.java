package src.klase;

import java.util.Random;

public class Polje{
	
	public Prepreka prepreka;
	public volatile int bonus;
	
	public Polje(Prepreka prepreka, Integer bonus){
		this.prepreka = prepreka;
		this.bonus = bonus;
	}
}
