package src.person; 

import java.util.Random;
import src.stadium.StadiumPos;


public abstract class Person {
	protected String name, surname, id;
	protected StadiumPos currPos;
	
	protected Person() {
			name = "Name " + Double.toHexString(new Double(new Random().nextDouble()));
			surname = "Surname " +Double.toHexString(new Double(new Random().nextDouble()));
			id = Double.toHexString(new Double(new Random().nextDouble()));
	}
	
	public void changePos(StadiumPos newPos, long speed) { 
		currPos.getPeople().remove(this);
		newPos.getPeople().add(this);
		try{
			Thread.sleep(speed);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void move();
	
	@Override 
	public String toString(){ return name + " " + surname + " " + "ID " + id; }
	
	
	
}