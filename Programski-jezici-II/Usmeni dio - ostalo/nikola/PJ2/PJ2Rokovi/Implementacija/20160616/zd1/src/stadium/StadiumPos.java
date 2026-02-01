package src.stadium;

import java.util.ArrayList;
import src.person.Person;

public class StadiumPos {
	private int y, x;
	ArrayList<Person> people = new ArrayList<>();
	
	public StadiumPos(int y, int x) { 
		this.y = y; 
		this.x = x;
	}
	
	public ArrayList<Person> getPeople() { return people;}
	
	public int getX() { return x;}
	
	public int getY() { return y;}
	
	@Override
		public String toString() { return y + " " + x; }
}