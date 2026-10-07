package src.vehicle;

import java.io.Serializable;
import java.util.Random;
import src.main.*;

public class Vehicle implements Serializable {
	protected String name;
	private double currCap;
	private double cap;
	private Gorivo type;
	
	protected Vehicle(){
		Random random = new Random();
		name = Long.toHexString(Double.doubleToLongBits(random.nextDouble()));
		cap = ((double)(random.nextInt(70) + 30));
		currCap = (double)(random.nextInt(((int)cap - 20)) + 10);
		type = (random.nextBoolean()) ? new Dizel() : new Benzin();
	}
	
	public double getCurrCap(){ return currCap;}
	public void setCurrCap(double currCap){ this.currCap = currCap;}
	public double getCap(){ return cap;}
	public void setCap(double cap){ this.cap = cap;}
	public Gorivo getType(){ return type;}
	
	@Override
	
	public String toString(){
		return name;
	}
}