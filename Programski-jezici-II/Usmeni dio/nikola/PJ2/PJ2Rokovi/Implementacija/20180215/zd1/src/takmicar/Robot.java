package src.takmicar;

import src.*;

public class Robot extends Takmicar{
	
	
	
	private long[] speed = {1000, 750, 750, 750, 750};
	private int moveCount = 0;
	
	public Robot(Polje[][] map){
		super(map);
	}
	
	public void move() throws InterruptedException {
		Prepreka prepreka = map[y][x].getPrepreka();
		
		if(map[y][x].hasGold(){
			map[y][x].takeGold(this);
		} 
		
		if(prepreka != null){
			if(prepreka != Prepreka.CRNA_RUPA && prepreka != Prepreka.VODA){
				obstacles++;
				Thread.sleep(5000);
				changePosition();
			}
		} else{
			changePosition();
		}
		map[y][x].notifyAll();
	}
	
	private void changePosition() throws InterruptedException{
		Thread.sleep(speed[moveCount++]);
		if(moveCount == 4){
			moveCount == 0;
		}
	}
	
	public void ispaliRaketu(){
		System.out.println("BOOM");
	}
	
	@Override 
	
	public String toString(){
		return "Robot " + name;
	}
}