package src.person;

import src.person.Person;
import static src.Main.stadium;
import static src.Main.central;
import static src.stadium.Stadium.numOfPeopleWhoHaventEntered;
import src.stadium.StadiumPos;

public class Agent extends Person{
	
	public Agent(StadiumPos currPos) {
		this.currPos = currPos;	
	}
	
	@Override
	public void move(){
	int newX;
	boolean hasHooligans = false;
	System.out.println("Agent" + this + " is checking out  " + currPos.getY() + " " + ((currPos.getX() != 14)  ?  ((currPos.getX()) + 1) : 0));
	if(currPos.getX() == 14)
		newX = 0;
	else
		newX = currPos.getX() + 1;
	changePos(stadium[currPos.getY()][newX], 100);
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