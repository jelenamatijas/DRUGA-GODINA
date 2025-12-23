package src.takmicar;

import src.*;

public class Skijas extends Takmicar{
	
		public Skijas(Polje[][] map){
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
		changeCoorinates();
	}
	
	@Override 
	
	public String toString(){
		return "Skijas " + name;
	}
}