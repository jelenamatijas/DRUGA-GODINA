package src.takmicar;

import src.*;

public class Atleticar extends Takmicar{
	
	private long[] speed = {1000, 1000, 500, 500, 500};
	
	public Atleticar(Polje[][] map){
		super(map);
	}
	
	public void move() throws InterruptedException{
		Prepreka prepreka = map[y][x].getPrepreka();
		
		if(map[y][x].hasGold()){
			map[y][x].takeGold();
		}
		
		if(prepreka != null){
			if(prepreka == Prepreka.SNIJEG){
				Thread.sleep(1500);
			} else if(prepreka == Prepreka.VODA || prepreka == Prepreka.KAMEN){
				Thread.sleep(5000);
				obstacles++;
			} else{
				if(map[y][x].numOfPeople != 0){
					map[y][x].notifyll();
					Thread.sleep(1500);
				} else{
					map[y][x].wait();
				}
				obstacles++;
			}
		}
		changePosition();
	}
	
	private void changePosition() throws InterruptedException{
		Thread.sleep(speed[moveCount++]);
		if(moveCount == 5){
			moveCount == 0;
		}
	}
	
	@Override 
	
	public String toString(){
		return "Atleticar " + name;
	}
}