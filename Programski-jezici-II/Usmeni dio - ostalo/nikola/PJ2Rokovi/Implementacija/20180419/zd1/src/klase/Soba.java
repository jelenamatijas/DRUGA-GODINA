package src.klase;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Random;

public class Soba{
	
	public ArrayList<Boolean> frizideri = new ArrayList<>();
	
	Random rand = new Random();
	
	int count = new Random().nextInt(10);
	String name = Long.toHexString(Double.doubleToLongBits(rand.nextDouble()));
	
	public Soba(){
		IntStream.range(0, count).forEach(count -> frizideri.add(rand.nextBoolean()));
	}
	
	@Override 
	
	public String toString(){
		return "Soba" + name + " broj frizidera " + frizideri.stream().mapToInt(f -> f ? 1 : 0).sum();
	}
}