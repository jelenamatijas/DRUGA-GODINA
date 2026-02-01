package src.visitor;

import java.util.Random;

public class Adult extends Visitor {
	private int numOfKids;
	private double money;
	public Adult(int numOfKids) {
		this.money = (double)(new Random().nextInt(96) + 5);
		this.numOfKids = numOfKids;
	}
	
	public int getKids() { return numOfKids;}
	
	public double getMoney() { return money;}
}