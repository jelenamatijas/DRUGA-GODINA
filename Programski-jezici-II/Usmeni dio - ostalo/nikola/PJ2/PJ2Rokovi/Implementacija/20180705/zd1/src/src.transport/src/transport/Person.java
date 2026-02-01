package src.transport;

import java.util.Random;

public class Person extends CustomsObject{
	private String name;
	private Double customsValue;
	private boolean hasPassport;
	
	public Person(){
		name = "Osoba" + Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
		hasPassport = (new Random().nextInt(10) < 6);
		if(new Random().nextBoolean())
			customsValue = new Random().nextDouble()*100;
	}
	
	public boolean anythingToDeclare(){
		if(customsValue != null && new Random().nextBoolean())
			return true;
		else 
			return false;
	}
	
	public boolean getHasPassport(){
		return hasPassport;
	}
	
	public Double getCustomsValue(){
		return customsValue;
	}
	
	@Override
		public String toString(){
			return name;
		}
	
}