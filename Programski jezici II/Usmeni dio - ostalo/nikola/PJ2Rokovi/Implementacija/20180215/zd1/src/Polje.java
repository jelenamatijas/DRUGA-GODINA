package src; 

import src.takmicar;
import java.util.Random;

public class Polje extends Thread{
	
	
	public int numOfPeople = 0;
	
	private int x, y;
	private Prepreka prepreka;
	private boolean gold;
	long runTime = new Random().nextLong() + 10000;


	public Polje(Prepreka prepreka, boolean gold, int x, int y){
		this.x = x;
		this.y = y;
		this.prepreka = prepreka;
		this.gold = gold;
	}
	
	public void run(){
		try{
			Thread.sleep(runTime);
			System.out.println("Polje " + y + " " + x + " je izgubilo prepreku");
			prepreka = null;
		} catch(InterruptedException){
			e.printStackTrace();
		}
	}
	
	public synchronized void takeGold(Takmicar takmicar){
		gold = false;
		takmicar.score++;
	}
	
	public boolean hasGold(){
		return gold;
	}
	
	public void setGold(boolean gold){
		this.gold = gold;
	}
	
	public void setPrepreka(Prepreka prepreka){
		this.prepreka = prepreka;
	}
	
	@Override
	
	public String toString(){
		return "" +  x + " " + y;
	}
}