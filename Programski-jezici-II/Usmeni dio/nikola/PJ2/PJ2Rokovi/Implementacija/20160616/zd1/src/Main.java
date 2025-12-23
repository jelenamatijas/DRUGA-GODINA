package src;

import src.stadium.StadiumPos;
import src.stadium.Stadium;
import src.person.central.Central;

public class Main{
	public static StadiumPos[][] stadium = new StadiumPos[15][15];
	public static Central central = new Central();
	
	static {
		for(int y = 0; y < 15; y++)
			for(int x = 0; x < 15; x++)
				stadium[y][x] = new StadiumPos(y, x);
	}
	
	public static void main(String args[]){
		Stadium newStadium = new Stadium(15, 15);
		newStadium.init();
		while(newStadium.simulate()){}
	}
}