package src.person;

import src.stadium.StadiumPos;
import java.util.Random;
import static src.Main.stadium;
import static src.Main.central;
import java.util.ArrayList;
import static src.stadium.Stadium.numOfPeopleWhoHaventEntered;
import src.person.Person;
import src.person.Fan;
import src.person.Hooligan;

public class Police extends Person {
	
	public Police(StadiumPos currPos){
		this.currPos = currPos;
	} 
	
	@Override
	public void move() {
		int newX;
		ArrayList<Person> toEliminate = new ArrayList<>();
		System.out.println("Policeman" + this + " is checking out  " + (currPos.getY()) + ((currPos.getX() != 14) ? ((currPos.getX()) + 1) :  0));
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
				if(new Random().nextBoolean()){
					System.out.println("Hooligan " + current + " has been eliminated");
					toEliminate.add(current);
				} else
					System.out.println("Hooligan " + current + " hasn't been eliminated");
			}
		}
		currPos.getPeople().removeAll(toEliminate);
		central.getCaught().addAll(toEliminate);
	}
	
	@Override
		public String toString() { return name + " " + surname + " ID " + id;}
}