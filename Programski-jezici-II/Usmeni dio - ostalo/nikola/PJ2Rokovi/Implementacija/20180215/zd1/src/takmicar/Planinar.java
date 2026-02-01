package src.takmicar;

import src.*;

public class Planinar extends Takmicar{
	
	
	public Planinar(Polje[][] map){
		super(map);
	}
	
	public void move() throws InterruptedException{
		Prepreka prepreka = map[y][x].getPrepreka();
		
		if(map[y][x].hasGold()){
			map[y][x].takeGold();
		}
		
		if(prepreka != null){
			if(prepreka == Prepreka.SNIJEG || prepreka == Prepreka.KAMEN){
				obstacles++;
				Thread.sleep(1500);
			} else if(prepreka == Prepreka.VODA){
				Thread.sleep(5000);
			} else{
				if(map[y][x].numOfPeople != 0){
					map[y][x].notifyll();
					Thread.sleep(1500);
				} else{
					map[y][x].wait();
					Thread.sleep(1500);
				}
				obstacles++;
			}
		}
		changeCoorinates();
	}
	
	
	@Override 
	
	public String toString(){
		return "Planinar " + name;
	}
}