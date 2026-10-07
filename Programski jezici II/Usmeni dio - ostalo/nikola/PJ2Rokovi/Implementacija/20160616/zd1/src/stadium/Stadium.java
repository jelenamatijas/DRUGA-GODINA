package src.stadium;


import src.stadium.StadiumPos;
import static src.Main.stadium;
import static src.Main.central;
import java.util.ArrayList;
import java.util.Random;
import src.person.*;


public class Stadium{
	int capacity, numOfEntrances;
	ArrayList<Person> toRun = new ArrayList<>();
	public static int numOfPeopleWhoHaventEntered = 10;
	
	public Stadium(int capacity, int numOfEntrances){
		this.capacity = capacity;
		this.numOfEntrances = numOfEntrances;
	}
	
	public void init(){
		StadiumPos newPos;
		Person toAdd = null;
		for(int counter = 0; counter < 25; counter++){
			newPos = stadium[new Random().nextInt(15)][new Random().nextInt(15)];
			if(counter < 5)
				toAdd = new Monitor(newPos);
			else if(counter >= 5 && counter < 10)
				toAdd = new Agent(newPos);
			else if(counter >= 10 && counter < 20){
				toAdd = new Fan(newPos);
				System.out.println("Fan added on a pos " + newPos);
			} else if(counter >= 20 && counter < 25){
				toAdd = new Hooligan(newPos);
				System.out.println("Hooligan added on a pos" + newPos);
			}
			toRun.add(toAdd);
			newPos.getPeople().add(toAdd);
		}
		for(int counter = 0; counter < 15; counter++){			
			for(int c = 0; c < 5; c++){
				newPos = stadium[3*(counter / 3 + 1) - 1][new Random().nextInt(15)];
				toAdd = new Police(newPos);
				toRun.add(toAdd);
				newPos.getPeople().add(toAdd);
			}
		}
		for(int counter = 0; counter < 5; counter++){
			newPos = stadium[14][14];
			toAdd = new Specs();
			central.getSpecs().add(((Specs)toAdd));
			newPos.getPeople().add(toAdd);
			toRun.add(toAdd);
			
		}
	}
		public boolean simulate() {
			if(numOfPeopleWhoHaventEntered != 0){
				for(Person toMove : toRun)
					toMove.move();
				return true;
			} else 
				return false;
		}
}
