package src.traka.masine;

import java.util.Random;

public abstract class Masina{
	
	String id;
	public long speed;
	int kapacitet; 
	{
		Random rand = new Random();
		id = Long.toHexString(Double.doubleToLongBits(rand.nextDouble()));
		speed = rand.nextInt(3000);
		kapacitet = rand.nextInt(90) + 10;
	}
	abstract boolean obradi(Materijal p);
}