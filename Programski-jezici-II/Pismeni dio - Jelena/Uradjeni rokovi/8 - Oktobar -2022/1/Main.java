import java.util.*;

class Main{
	static Random rand = new Random();
	static Object lock1 = new Object();
	static Object lock2 = new Object();
	static Object lock3 = new Object();
	static Object lockPauza = new Object();
	
	static Skladiste skladiste = new Skladiste();
	static Traka naObradi = new Traka();
	static Traka obradjen = new Traka();
	
	static volatile boolean pauza = false;
	static volatile boolean krajSimulacije = false;
	
	static public void main(String args[]){
		
		VozacKamiona vozacKamiona = new VozacKamiona();
		VozacViljuskara vozacViljuskara = new VozacViljuskara();
		Varilac varilac = new Varilac();
		Bravar bravar = new Bravar();
		
		System.out.println("=== SIMULACIJA POCINJE ===\n");
		
		Thread dovoz = new Thread(()-> {
			try{
				while(true){
					synchronized(lockPauza){
						while(pauza){
							System.out.println("[DOVOZ] Pauziran zbog alarma...");
							lockPauza.wait();
						}
					}
					
					Predmet p = null;
					synchronized(lock1){
						if(skladiste.predmeti.isEmpty()){
							System.out.println("[DOVOZ] Skladiste prazno - zavrsavam rad.");
							break;
						}
						p = skladiste.predmeti.remove(0);
					}
					
					System.out.println("[DOVOZ] Uzimam sa skladista: " + p);
					
					if(p instanceof OtvoriInterface && p instanceof ZatvoriInterface){
						vozacViljuskara.start(p);
					}else{
						vozacKamiona.start(p);
					}
					
					synchronized(lock2){
						naObradi.predmeti.add(p);
						System.out.println("[DOVOZ] Postavljen na traku za obradu: " + p);
						lock2.notifyAll();
					}
					
					Thread.sleep(50); 
				}
			}catch(InterruptedException e){
				System.out.println("[DOVOZ] Prekinut!");
			}
			
			synchronized(lock2){
				lock2.notifyAll();
			}
		});
		
		Thread obrada = new Thread(()-> {
			try{
				while(true){
					synchronized(lockPauza){
						while(pauza){
							System.out.println("[OBRADA] Pauziran zbog alarma...");
							lockPauza.wait();
						}
					}
					
					Predmet p = null;
					synchronized(lock2){
						while(naObradi.predmeti.isEmpty()){
							if(krajSimulacije){
								System.out.println("[OBRADA] Kraj simulacije - zavrsavam rad.");
								synchronized(lock3){
									lock3.notifyAll();
								}
								return;
							}
							lock2.wait();
						}
						p = naObradi.predmeti.remove(0);
					}
					
					System.out.println("[OBRADA] Pocinje obrada: " + p);
					
					if(p instanceof SavijInterface){
						varilac.start(p);
						try{ Thread.sleep(150); }catch(InterruptedException e){}
					}
					
					if(p instanceof OtvoriInterface && p instanceof ZatvoriInterface){
						varilac.start(p);
						try{ Thread.sleep(150); }catch(InterruptedException e){}
						bravar.start(p);
						try{ Thread.sleep(150); }catch(InterruptedException e){}
					}
					
					if(!(p instanceof SavijInterface) && !(p instanceof OtvoriInterface)){
						System.out.println("[OBRADA] Sajla ne zahtijeva obradu: " + p);
					}
					
					System.out.println("[OBRADA] Zavrsena obrada: " + p);
					
					synchronized(lock3){
						obradjen.predmeti.add(p);
						lock3.notifyAll();
					}
				}
			}catch(InterruptedException e){
				System.out.println("[OBRADA] Prekinut!");
			}
		});
		
		Thread odvoz = new Thread(()-> {
			try{
				while(true){
					synchronized(lockPauza){
						while(pauza){
							System.out.println("[ODVOZ] Pauziran zbog alarma...");
							lockPauza.wait();
						}
					}
					
					Predmet p = null;
					synchronized(lock3){
						while(obradjen.predmeti.isEmpty()){
							if(krajSimulacije){
								System.out.println("[ODVOZ] Kraj simulacije - zavrsavam rad.");
								return;
							}
							lock3.wait();
						}
						p = obradjen.predmeti.remove(0);
					}
					
					System.out.println("[ODVOZ] Odvozim obradjeni predmet: " + p);
					
					if(p instanceof OtvoriInterface && p instanceof ZatvoriInterface){
						vozacViljuskara.start(p);
					}else{
						vozacKamiona.start(p);
					}
					
					synchronized(lock1){
						skladiste.obradjeniPredmeti.add(p);
						System.out.println("[ODVOZ] Vracen u skladiste obradjenih: " + p);
					}
					
					Thread.sleep(50);
				}
			}catch(InterruptedException e){
				System.out.println("[ODVOZ] Prekinut!");
			}
		});
		
		Thread konzola = new Thread(()->{
			Scanner s = new Scanner(System.in);
			System.out.println("Unesite 'ALARM' za pauzu ili 'ALARM_END' za nastavak.\n");
			
			while(!krajSimulacije){
				try{
					if(s.hasNextLine()){
						String rijec = s.nextLine().trim();
						
						if(rijec.equalsIgnoreCase("alarm")){
							synchronized(lockPauza){
								if(!pauza){
									pauza = true;
									System.out.println("\n!!! ALARM AKTIVIRAN - SVI RADNICI PAUZIRAJU !!!\n");
								}
							}
						}
						else if(rijec.equalsIgnoreCase("alarm_end")){
							synchronized(lockPauza){
								if(pauza){
									pauza = false;
									System.out.println("\n!!! ALARM DEAKTIVIRAN - RADNICI NASTAVLJAJU RAD !!!\n");
									lockPauza.notifyAll();
								}
							}
						}
					}
					
					
					boolean trebaStat = false;
					synchronized(lock1){
						synchronized(lock2){
							synchronized(lock3){
								if(skladiste.predmeti.isEmpty() && 
								   naObradi.predmeti.isEmpty() && 
								   obradjen.predmeti.isEmpty() &&
								   !dovoz.isAlive()){
									trebaStat = true;
								}
							}
						}
					}
					
					if(trebaStat){
						krajSimulacije = true;
						System.out.println("\n=== SVI PREDMETI OBRADJENI - SIMULACIJA ZAVRSENA ===");
						
						
						synchronized(lock2){
							lock2.notifyAll();
						}
						synchronized(lock3){
							lock3.notifyAll();
						}
						synchronized(lockPauza){
							lockPauza.notifyAll();
						}
						break;
					}
					
					Thread.sleep(100);
				}catch(InterruptedException e){
					break;
				}
			}
			s.close();
		});
		
		dovoz.start();
		obrada.start();
		odvoz.start();
		konzola.start();
		
		try{
			dovoz.join();
			obrada.join();
			odvoz.join();
			konzola.join();
		}catch(InterruptedException e){
			System.out.println("Greska pri cekanju thread-ova!");
		}
		
	}
}