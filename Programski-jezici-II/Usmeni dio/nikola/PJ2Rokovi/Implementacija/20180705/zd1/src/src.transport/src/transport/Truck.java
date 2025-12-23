package src.transport;

import java.util.Random;

public class Truck extends Vehicle{
	private double customsValue;
	public Truck(){
		super(new Random().nextInt(1) + 1);
		customsValue = new Random().nextDouble()*100;
	}
	
	public double getCustomsValue(){
		return customsValue;
	}
	
	@Override
		public String toString(){
			return "Truck " + name;
		}
	
}