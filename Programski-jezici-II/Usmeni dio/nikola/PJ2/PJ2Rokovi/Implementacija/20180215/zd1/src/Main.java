package src;

import src.takmicar;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;

public class Main{
	
	public static void main(String []args){
		
		ArrayList<Takmicar> takmicari = new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Polje[][] map = new Polje[10][10];
		ArrayList<Integer> randXGold = new ArrayList<>();
		ArrayList<Integer> randYGold = new ArrayList<>();
		ArrayList<Integer> randYObstacle = new ArrayList<>();
		ArrayList<Integer> randXObstacle = new ArrayList<>();
		IntStream.range(0,100).forEach(count -> {
			randXGold.add(count);
			randYGold.add(count);
			randXObstacle.add(count);
			randYObstacle.add(count);
		});
		
		Collections.shuffle(randXGold);
		Collections.shuffle(randYGold);
		Collections.shuffle(randXObstacle);
		Collections.shuffle(randYObstacle);
		randXGold = new ArrayList(randXGold.subList(0, 20));
		randYGold = new ArrayList(randYGold.subList(0, 20));
		randXObstacle = new ArrayList(randXObstacle.subList(0, 20));
		randYObstacle = new ArrayList(randYObstacle.subList(0, 20));
		int count = 0;
		for(int y = 0; y < 10; y++){
			for(int x = 0; x < 10; x++){
				map[y][x] = new Polje(null, false, x, y);
				if(randXGold.contains(x) && randYGold.contains(y)){
					map[y][x].setGold(true);
				}
				if(randXObstacle.contains(x) && randYObstacle.contains(y)){
					Prepreka prepreka; 
					if(count < 5){
						map[y][x].setPrepreka(Prepreka.VODA);
					} else if(count >= 5 && count < 10){
						map[y][x].setPrepreka(Prepreka.SNIJEG);
					} else if(count >= 10 && count < 15){
						map[y][x].setPrepreka(Prepreka.KAMEN);
					} else {
						map[y][x].setPrepreka(Prepreka.CRNA_RUPA);
					}
				}
			}
		}
		
		IntStream.range(0, 4).forEach(count -> {
			switch(count){
				case 0:
					takmicari.add(new Robot(map));
					break;
				case 1:
					takmicari.add(new Planinar(map));
					break;
				case 2:
					takmicari.add(new Atleticar(map));
					break;
				case 3:
					takmicari.add(new Skijas(map));
					break;
			}
		});
		
		
		try{
			while(!"start".equals(in.readLine()));
		} catch(InterruptedIOException){
			e.printStackTrace();
		}
		
		for(Takmicar t : takmicari){
			t.start();
		}
		
		Collections.sort(takmicari, (t1, t2) -> {
			double t1Bodovi = 10*t1.getScore() + 100/t1.getRunTime();
			double t2Bodovi = 10*t2.getScore() + 100/t2.getRunTime();
			if(t1 == t2)
				return 0;
			else if(t1 > t2)
				return 1;
			else 
				return -1;
		});
		
		takmicari.stream().forEach( t -> {
			System.out.println(t + " Vrijeme " + t.getRunTime() + " Broj savladanih prepreka " + t.getObstacles() " Broj zlatnika " + t.getScore() + " Broj bodova " + (10*t.getScore() + 100/t.getRunTime()));
		});
	}
	
	
}