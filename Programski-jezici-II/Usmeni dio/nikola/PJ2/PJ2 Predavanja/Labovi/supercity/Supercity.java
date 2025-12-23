package supercity;

import supercity.supercityblock.*;
import supercity.superhero.*;
import supercity.badguy.*;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import supercity.resident.*;

class Supercity{
	private static final int X_BLOCKS = 30;
	private static final int Y_BLOCKS = 90;
	private static SupercityBlock city_matrix[][] = new SupercityBlock[X_BLOCKS][Y_BLOCKS];
	private static int numOfBadguys = 0;
	static {
		for(int i = 0; i < X_BLOCKS; i++) {
			for(int j = 0; j < Y_BLOCKS; j++) {
				
				if(j < 30){
					city_matrix[i][j] = new WaterBlock();
				}
				
				if(30 <= j && j< 60){
					city_matrix[i][j] = new LandBlock();
				}
				
				if(60 <= j && j< 90){
					city_matrix[i][j] = new SkyBlock();
				}
			}
		}
	}
	
	public static void main(String []args) {
		Random rand = new Random();
		while(numOfBadguys < 6){
			int x = rand.nextInt(30);
			int y = rand.nextInt(90);
			if(city_matrix[x][y].hasBadGuy())
				continue;
			else{
				city_matrix[x][y].sendBadGuy(new BadGuy("Zlikovac " + x  + " " + y));
				numOfBadguys++;
			}
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			
			while(!"start".equals(in.readLine()));
			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("Justice League krece u akciju!");
		for(int i = 0; i < X_BLOCKS; i++) {
			for(int j = 0; j < Y_BLOCKS; j++) {
					if(city_matrix[i][j].hasBadGuy()) {
					if(j < 30){
						System.out.println("U bloku" +  i + " "  + j+ " na vodi " + " se nalazi zlikovac");
						city_matrix[i][j].sendHero(new Aquaman());
					}
				
					if(30 <= j && j < 60){
						System.out.println("U bloku" +  i + " " + j + "  na zemlji " + " se nalazi zlikovac"); 
						if(rand.nextBoolean())
							city_matrix[i][j].sendHero(new Batman());
						else
							city_matrix[i][j].sendHero(new Green_Arrow());
					}
				
					if(60 <= j && j < 90){
					System.out.println("U bloku" +  i + " " + j + "  na zemlji " + " se nalazi zlikovac"); 
						int k = rand.nextInt(3);
						Resident hero;
						if (k == 1)
							hero = new Wonder_Woman();
						else if(k == 2)
							hero = new Superman();
						else
							hero = new Supergirl();
						if(!city_matrix[i][j].sendHero(hero))
							return;
				}
			}
			else
				System.out.println("Sve cisto u bloku" + i + " " + j);
			}
		}
		System.out.println("Justice League je opet spasila grad!");
	}
}