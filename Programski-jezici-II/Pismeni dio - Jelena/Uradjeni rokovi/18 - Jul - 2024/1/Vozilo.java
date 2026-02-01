import java.util.*;

abstract class Vozilo extends Thread{
	Set<Putnik> putnici;
	String naziv;
	
	public Vozilo(String naziv){
		putnici = new HashSet<>();
		int n = Main.rand.nextInt(25, 51);
		this.naziv = naziv;
		try{
			move(n);
		}catch(PogresanBrojPutnikaException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void move(int n) throws PogresanBrojPutnikaException{
		synchronized(Main.pool.dostupni){
			if(Main.pool.dostupni.size() >= n){
				Iterator<Putnik> it = Main.pool.dostupni.iterator();
				for(int i=0; i<n && it.hasNext();i++){
						Putnik p = it.next();
						putnici.add(p);
						it.remove();
					}
				System.out.println(n + " putnika skinuto sa liste dostupnih putnika i dodano u vozilo " + naziv);
			}else{
				throw new PogresanBrojPutnikaException("NEMA DOVOLJNO DOSTUPNIH PUTNIKA ZA PREBACITI PRI KREIRANJU VOZILA. VOZILO: " + naziv);
			}
		}
	}
	
	@Override 
	public void run(){
		int trajanje = Main.rand.nextInt(20, 31);
		try{
			System.out.println(this + " POCELO SA VOZNJOM.");
			while(trajanje > 0){
				boolean potez = Main.rand.nextBoolean();
				int n = 0;
				
				if(this instanceof VozSaVagonimaInterface){
					n = Main.rand.nextInt(5)+1;
				}else{
					n = Main.rand.nextInt(3)+1;
				}
				if(potez && n <= (50 - putnici.size())){
					try{
						move(n);
					}catch(PogresanBrojPutnikaException e){
						System.out.println(e.getMessage());
					}
				}else if(!potez && n < putnici.size()){
					Iterator<Putnik> it = putnici.iterator();
					for(int i=0; i<n && it.hasNext();i++){
						Putnik p = it.next();
						it.remove();
					}
					System.out.println(n + " putnika skinuto sa liste putnika u vozilu " + naziv);
				}else{
					throw new PogresanBrojPutnikaException("NEMA DOVOLJNO DOSTUPNIH PUTNIKA ZA UKLONITI. VOZILO: " + naziv);
				}
				System.out.println(this);
				sleep(2000);
				trajanje-=2;
			}
		}catch(PogresanBrojPutnikaException|InterruptedException e){
			System.out.println(e.getMessage());
		}
		System.out.println(this + " ZAVRSILO SA VOZNJOM.");
		
	}
	
	@Override
	public String toString(){
		return "Vozilo: " + naziv + " Broj putnika:" + putnici.size();
	}
}