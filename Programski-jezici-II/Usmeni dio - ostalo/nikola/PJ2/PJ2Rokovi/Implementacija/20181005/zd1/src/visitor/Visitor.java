package src.visitor;

import java.util.Random;

public class Visitor{
	String name, surname;
	
	public Visitor() {
		name = "Name" + Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
		surname = "Surname" + Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
	}
	
	@Override
		public String toString() { return name + " " + surname; }
}