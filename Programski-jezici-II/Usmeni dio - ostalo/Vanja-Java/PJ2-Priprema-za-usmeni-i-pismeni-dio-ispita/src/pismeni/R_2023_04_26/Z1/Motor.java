package pismeni.R_2023_04_26.Z1;

import java.util.*;

public class Motor extends Thread{

	Status status;
	int snaga;

	public Motor(){
		snaga = new Random().nextInt(9001) + 1000;
		status = Status.UPALJEN;
		setDaemon(true);
	}

	@Override
	public void run(){
		try{
			while(!Main.STOP){
				boolean pokvaren = new Random().nextInt(100) < 20;
				if(pokvaren){
					status = Status.POKVAREN;
					synchronized(Main.poruke){
						Main.poruke.add(new Poruka(Prioritet.UPOZORENJE, NM.poruke.get(Prioritet.UPOZORENJE)));
					}
				}
				Thread.sleep(1000);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}