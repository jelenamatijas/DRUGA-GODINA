package src.transport;

import java.util.Random;
import java.util.ArrayList;

public class Vehicle extends CustomsObject{
	protected String name;
	private ArrayList<Person> passengers = new ArrayList<>();
	
	protected Vehicle(int toAdd){
		name = Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
		for(int count = 0; count < toAdd; count++)
			passengers.add(new Person());
	}
	
	public ArrayList<Person> getPassengers(){
		return passengers;
	}
	
}