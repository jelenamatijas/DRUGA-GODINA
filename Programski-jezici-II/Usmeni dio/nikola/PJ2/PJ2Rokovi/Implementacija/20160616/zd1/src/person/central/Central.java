package src.person.central;

import src.person.Specs;
import static src.Main.stadium;
import src.stadium.StadiumPos;
import java.util.ArrayList;
import java.util.Random;
import src.person.Hooligan;
import src.person.Person;

public class Central {
	private ArrayList<Specs> specs = new ArrayList<>();
	private ArrayList<Person> caught = new ArrayList<>();
	
	public ArrayList<Specs> getSpecs() { return specs; }
	
	public void sendTeam(StadiumPos newPos){
		ArrayList<Person> toSuspend = new ArrayList<>();
		System.out.println("Sending specs to pos " + newPos);
		for(Specs spec : specs){
			spec.changePos(newPos, 10);
		}
		for(Person current : newPos.getPeople()){
			if(current instanceof Hooligan){
				specs.get(new Random().nextInt(5)).suspendHooligan(current);
				toSuspend.add(current);
			}
		}
		for(Person tosusp : toSuspend)
			newPos.getPeople().remove(tosusp);
		caught.addAll(toSuspend);
		for(Specs spec : specs)
			spec.changePos(stadium[14][14], 10);
		System.out.println("Threat at " + newPos + " has been eliminated");
	}
	public ArrayList<Person> getCaught() { return caught; }
}