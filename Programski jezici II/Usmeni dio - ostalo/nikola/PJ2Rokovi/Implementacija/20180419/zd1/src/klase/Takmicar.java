package src.klase;

import java.util.Random;

import static src.Main.mapa;


public abstract class Takmicar extends Thread{
	
	public Integer energija = new Random().nextInt(101);
	public String ime = Long.toHexString(Double.doubleToLongBits(new Random().nextDouble())) + " energija " + energija.toString();
	public int pozicija;
	
	protected abstract boolean savladaj(Object p);
	
	public void run(){
		synchronized(this){
		while(pozicija < mapa.length){
			System.out.println("Takmicar" + this + " je na poziciji " + pozicija);
			System.out.println(((mapa[pozicija] instanceof Prepreka) ? ("Pronadjena je " + mapa[pozicija])  : "Nije pronadjena") + " prepreka");
			synchronized(mapa[pozicija]){
				if(mapa[pozicija] instanceof Prepreka && savladaj((mapa[pozicija]))){
					mapa[pozicija] = new Object();
					System.out.println("Prepreka je uklonjena");
				} else
					break;
			}
			pozicija++;
			try{
				Thread.sleep(3000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println("Takmicar" + this + " je zavrsio takmicenje na poziciji " + pozicija);
		}	
		
		
	}
}
}