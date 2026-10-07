package src.klase;

import static src.Main.mapa;
import java.util.Random;

public abstract class Takmicar extends Thread{
	
	public int score;
	int pos;
	String ime = Long.toHexString(Double.doubleToLongBits(new Random().nextDouble()));
	int speed = new Random().nextInt(2000) + 1000;
	
	protected abstract void move();
	
	protected abstract boolean check(Polje p);
	
	protected Takmicar() {}
	
	public static Vozac newVozac(){
		return new Vozac();
	}
	
	public static Pjesak newPjesak(){
		return new Pjesak();
	}
	
	public static Pilot newPilot(){
		return new Pilot();
	}
	
	public void run(){
		try{
		while(pos < mapa.length){
			System.out.println(this + " je na poziciji " + pos);
			if(!check(mapa[pos])){
				if(mapa[pos].prepreka instanceof IGori){
					((Vatra)mapa[pos].prepreka).gori(pos);
					score -= IGori.JACINA;
				} else if(mapa[pos].prepreka instanceof IPoplavi){
					((Voda)mapa[pos].prepreka).poplavi(pos);
					score -= IPoplavi.JACINA;
				} else if(mapa[pos].prepreka instanceof IObrusi){
					((Kamen)mapa[pos].prepreka).obrusi(pos);
					score -= IObrusi.JACINA;
				}
				Thread.sleep(2000);
			}
			synchronized(mapa[pos]){
				if(mapa[pos].bonus > 0){
					score += mapa[pos].bonus;
					System.out.println(this +  " je pokupio bonus od " + mapa[pos].bonus);
					mapa[pos].bonus = -1;
				}
			}
			move();
			Thread.sleep(speed);
		}
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class Pjesak extends Takmicar{
	
	protected synchronized void  move(){
		if(mapa[pos].bonus == -1){
			mapa[pos].bonus = 0;
			pos += 3;
		} else
			pos++;
	}
	
	protected boolean check(Polje p){
		return (p.prepreka == null || !(p.prepreka instanceof Vatra));
			
	}
	
	@Override 
	
	public String toString(){
		return "Pjesak " + ime;
	}
}
class Pilot extends Takmicar{
	
	protected synchronized void  move(){
		if(mapa[pos].bonus == -1)
			mapa[pos].bonus = 0;
		
		pos += 2;
	}
	
	protected boolean check(Polje p){
		return (p.prepreka == null || !(p.prepreka instanceof Voda));
			
	}
	
	@Override 
	
	public String toString(){
		return "Pilot " + ime;
	}
}
class Vozac extends Takmicar{
	
	protected synchronized void  move(){
		if(mapa[pos].bonus == -1)
			mapa[pos].bonus = 0;
		
		pos++;
	}
	
	protected boolean check(Polje p){
		return (p.prepreka == null || p.prepreka instanceof Voda);
			
	}	
	
	@Override 
	
	public String toString(){
		return "Vozac " + ime;
	}
}

