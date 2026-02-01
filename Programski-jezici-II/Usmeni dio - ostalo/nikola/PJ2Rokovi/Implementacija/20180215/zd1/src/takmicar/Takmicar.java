package src.takmicar;

import java.util.Random;
import src.*;


public abstract class Takmicar extends Thread{
	
	
	protected int obstacles = 0;
	protected Polje[][] map;
	private long runTime;
	protected boolean horizontal = new Random().nextBoolean();
	private static final long SPEED = 400;
	public int score = 0;
	protected int x = 0, y = 0;
	protected String name = Long.toHexString(Double.doubleToLongBits(new Random().nextDouble())); 
	
	protected Takmicar (Polje[][] map) {
		this.map = map;
	}
	
	public abstract void move();
	
	protected abstract void overcome() throws InterruptedException;
	
	
	protected void changeCoordinates(){
		synchronized(map[y][x].numOfPeople){
			map[y][x].numOfPeople--;
		}
		
		if(horizontal){
			if(x == 9){
				y++;
				x = 0;
			} else {
				x++;
			}
		} else {
			if( y == 9){
				x++;
				y = 0;
			} else{
				y++;
			}
		}
		synchronized(map[y][x].numOfPeople){
			map[y][x].numOfPeople++;
		}
	}
	
	public void run(){
		runTime = System.currentTimeMillis();
		while(x != 9 && y != 9){
			try{
				System.out.println("Trenutno na poziciji " + x + " " + y);
				move();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		runTime = System.currentTimeMillis() - runTime;
	}
	
	public double getRunTime(){
		return (double)(runTime/1000);
	}
	
	public int getObstacle(){
		return obstacles;
	}
	
	public double getScore(){
		return score;
	}
	
} 	
