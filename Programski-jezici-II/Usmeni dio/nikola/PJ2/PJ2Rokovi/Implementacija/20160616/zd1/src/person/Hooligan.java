package src.person;


import java.util.Random;
import src.stadium.StadiumPos;
import static src.Main.stadium;

public class Hooligan extends Person{
	
	public Hooligan(StadiumPos currPos) {
		this.currPos = currPos;
	}
	
	@Override
	public void move() { changePos(stadium[((currPos.getY() + 1)%15)][new Random().nextInt(15)], 10);}
	
	@Override 
		public String toString() { return name + " " + surname + " ID " + id;}	
}