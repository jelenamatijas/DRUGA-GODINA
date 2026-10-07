package src.person;

import src.person.Person;
import java.util.Random;
import static src.Main.stadium;
import static src.stadium.Stadium.numOfPeopleWhoHaventEntered;
import src.person.central.Central;
import static src.Main.central;
import src.stadium.StadiumPos;

public class Monitor extends Person{
	
	public Monitor(StadiumPos currPos) {
		this.currPos = currPos;	
	}
	
	@Override
	public void move(){
	int newY;
	boolean hasHooligans = false;
	System.out.println("Monitor" + this + " is checking out  " + ((currPos.getY() == 14)  ? 0 : (currPos.getY() + 1))  + " " + (currPos.getX()));
	if(currPos.getY() == 14)
		newY = 0;
	else
		newY = currPos.getY() + 1;
	changePos(stadium[newY][currPos.getX()], 100);
		for(Person current: currPos.getPeople()){
			if(current instanceof Fan && !((Fan)current).getEntered()){
				System.out.println(current + " has been legitimized and is currently entering the stadium...");
				((Fan)current).setEntered(true);
				numOfPeopleWhoHaventEntered--;
			} else if(current instanceof Hooligan){
				System.out.println("Hooligan " + current + " has been spotted");
				hasHooligans = true;
			}
		}
		if(hasHooligans)
			central.sendTeam(currPos); 
	}
	
	@Override 
		public String toString() { return name + " " + surname + " ID " + id;}	
}