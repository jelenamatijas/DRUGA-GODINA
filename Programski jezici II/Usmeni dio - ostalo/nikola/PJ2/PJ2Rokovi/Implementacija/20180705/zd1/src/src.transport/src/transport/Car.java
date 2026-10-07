package src.transport;

import java.util.Random;

public class Car extends Vehicle{
	public Car(){
		super(new Random().nextInt(4) + 1);
	}
	
		@Override
		public String toString(){
			return "Car " + name;
		}
}