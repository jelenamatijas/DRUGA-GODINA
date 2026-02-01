import java.util.*;

class PogonskiModul extends Thread{
	List<Motor> motori = new ArrayList<>();
	boolean statusPogonskogModula;
	boolean statusRaketnogPogona;
	volatile boolean ugasen;
	int prethodni = 0;
	
	PogonskiModul(){
		for(int i=0;i<4;i++){
			motori.add(new Motor());
		}
		statusPogonskogModula = true;
		statusRaketnogPogona = Main.rand.nextBoolean();
		ugasen = false;
	}
	
	public void run(){
		for(Motor m :motori){
			m.start();
		}
		while(!ugasen){
			int x = 0;
			for(Motor m : motori){
				if(!m.motorRadi){
					x++;
				}
			}

			if((x==1 || x ==2) && (x > prethodni)){
				prethodni = x;
				statusPogonskogModula = false;
				String opis = "Ne radi(e) " + x + " motor(a) na letjelici.";
				synchronized(Main.lockKomunikacija){
					Main.poruke.add(new Poruka(Main.time(), opis, KomunikacioniModul.Prioritet.UPOZORENJE));
				}
				try{
					sleep(1000);
				}catch(InterruptedException e){  
					break;
				}
			}
			if(x>2){
				ugasen = true;
				String opis = "Pogonski modul je otkazao. Letjelica se spusta. Zapocinje iskrcavanje.";
				synchronized(Main.lockKomunikacija){
					Main.poruke.add(new Poruka(Main.time(), opis, KomunikacioniModul.Prioritet.KRITICNO));
				}
				try{
					sleep(1000);
				}catch(InterruptedException e){  
					break;
				}
			}
			
			try{
				sleep(1000);
			}catch(InterruptedException e){  
				ugasen = true;
				break;
			}
			
		}
		try{
			for(Motor m : motori){
				m.motorRadi = false;
				m.interrupt();
				m.join();
			}
		}catch(Exception e){
			System.out.println("Greska pri zaustavljanju motora.");
		}
	}
}