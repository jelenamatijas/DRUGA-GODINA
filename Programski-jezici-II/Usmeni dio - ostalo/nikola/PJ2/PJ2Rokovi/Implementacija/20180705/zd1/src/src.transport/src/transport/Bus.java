package src.transport;

import java.util.Random;

public class Bus extends Vehicle{
	public Bus(){
		super(new Random().nextInt(51) + 1);
	}
	
		@Override
		public String toString(){
			return "Bus " + name;
		}
}