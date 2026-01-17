import java.util.*;

class PoolPutnikaThread extends Thread{
	Set<Putnik> dostupni;
	public boolean radi;
	
	public PoolPutnikaThread(){
		dostupni = new HashSet<>();
		radi = true;
	}
	
	@Override
	public void run(){
		while(radi){
			synchronized(dostupni){
				if(dostupni.size() < 150){
					int potrebno = 150 - dostupni.size();
					Set<Putnik> novi = new HashSet<>();
					for(int i=0; i< potrebno; i++){
						novi.add(new Putnik());
					}
				
					try{
						dostupni.addAll(novi);
						System.out.println("Dodano je " + potrebno + " novih putnika u listu dostupnih putnika.");
					}catch(Exception e){
						System.out.println("GRESKA prilikom dodavanja novih putnika u listu dostupnih.");
					}
				}
			}
		}
	}
}