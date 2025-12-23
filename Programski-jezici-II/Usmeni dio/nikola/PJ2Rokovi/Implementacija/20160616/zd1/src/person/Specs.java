package src.person;

import src.stadium.StadiumPos;
import static src.Main.stadium;
import src.person.Person;

public class Specs extends Person{
	
	public Specs() {
		this.currPos = stadium[14][14];
	}
	
	@Override
	public void move() { return; }

	public void suspendHooligan(Person toEliminate) {
		System.out.println("Specop " + this + " suspended hooligan" + toEliminate);
	}
	
	@Override 
		public String toString() { return name + " " + surname + " ID " + id;}	
}


