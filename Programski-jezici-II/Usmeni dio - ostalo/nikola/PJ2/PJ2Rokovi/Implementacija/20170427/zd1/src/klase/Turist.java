package src.klase;

import static src.Main.mapa;
import static src.Main.parniDan;

import java.util.ArrayList;
import java.util.Random;

public class Turist extends Thread{
	Random rand = new Random();
	int speed = rand.nextInt(100) + 300;
	public ArrayList<String> folder = new ArrayList<>();
	public String ime = Long.toHexString(Double.doubleToLongBits(rand.nextDouble()));
	public int brojMjesta;
	public Kretanje nacinKretanja;
	public boolean done;
	public int x, y;
	public int novac = rand.nextInt(200) + 200;
	public boolean religiozan = rand.nextBoolean();
	
	public Turist(){
		x = rand.nextInt(50);
		y = rand.nextInt(50);
		
		System.out.println("Pocetna pozicija za " + this + "je " + x + " " + y);
		System.out.println("Turista " + (religiozan ? "je" : "nije") + "religiozan");
		
		switch(rand.nextInt(3)){
			case 0:
				nacinKretanja = Kretanje.SAMO_U_JEDNOM_REDU;
				break;
			case 1:
				nacinKretanja = Kretanje.DIJAGONALNO;
				break;
			case 2:
				nacinKretanja = Kretanje.KROZ_CIJELU_MATRICU;
				break;
		}
		
		System.out.println("Nacin kretanja " + nacinKretanja);
	}
	
	public void run(){
		while(!done){
			synchronized(mapa[y][x]){
			System.out.println("Obilazak pozicije " + mapa[y][x]);
			for(Atrakcija a: mapa[y][x].atrakcije){
				System.out.println(a + " je posjetio " + this);
				if( a instanceof Muzej){
					if(!parniDan)
						novac -= a.cjena;
					folder.add(((Muzej)a).letak);
				} else if(a instanceof Spomenik){
					folder.add(((Spomenik)a).opis);
				} else if(a instanceof ZabavniPark){
					novac -= a.cjena;
				} else{
					if(religiozan){
						((Crkva)a).prilozi += a.cjena;
						novac -= a.cjena;
					}
				}
				brojMjesta++;
				}
				if(novac <= 0)
					done = true;
			}
			
			/*try{
				Thread.sleep(speed);
			} catch(InterruptedException e){
				e.printStackTrace();
			}*/
			
			switch(nacinKretanja){
				case SAMO_U_JEDNOM_REDU:
					if(x < 49)
						x++;
					else
						done = true;
					break;
				case KROZ_CIJELU_MATRICU:
					if(y == 49 && x == 49)
						done = true;
					else{
						if(x == 49){
							x = 0;
							y++;
						} else
							x++;
					}
					break;
				case DIJAGONALNO:
					if(x == 49 || y == 49)
						done = true;
					else {
						x++;
						y++;
					}
					break;
			}
			
		}
	}
	
	@Override
	
	public String toString(){
		return "Turist " + ime;
	}
}