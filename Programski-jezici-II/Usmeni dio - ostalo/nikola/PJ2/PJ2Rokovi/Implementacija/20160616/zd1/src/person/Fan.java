package src.person;

import src.stadium.StadiumPos;
import static src.Main.stadium;
import java.util.Random;

public class Fan extends Person{
	
	boolean entered;
	
	public Fan(StadiumPos currPos) {
		this.currPos = currPos;
	}
	
	@Override
	public void move() { changePos(stadium[((currPos.getY() + 1)%15)][new Random().nextInt(15)], 10); }

	public boolean getEntered() { return entered;}
	
	public void setEntered(boolean entered) { this.entered = entered;}
	
	@Override 
		public String toString() { return name + " " + surname + " ID " + id;}	
}