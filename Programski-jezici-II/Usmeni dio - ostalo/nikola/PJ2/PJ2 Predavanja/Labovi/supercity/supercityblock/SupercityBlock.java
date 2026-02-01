package supercity.supercityblock;

import supercity.badguy.*;
import supercity.superhero.*;
import supercity.resident.*;

public class SupercityBlock{
	Resident currResident;
	public boolean hasBadGuy() {
		return (currResident != null && currResident instanceof BadGuy);
	}
	public void sendBadGuy(BadGuy newResident) {
			currResident = newResident;
		}
	public boolean sendHero(Resident hero) {
		BadGuy badResident = (BadGuy)currResident;
		if(hero instanceof Superman && badResident.hasCryptonite()) {
			System.out.println("Superman je unisten!");
			return false;
		}
		else {
			System.out.println("Zlikovac" + currResident.getName() + " je unisten! Superheroj" + hero.getName());
			currResident = hero;
		}
		return true;
	}
}