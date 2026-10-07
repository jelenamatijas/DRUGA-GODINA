import java.util.*;

class Main{
	static Random rand = new Random();
	static Queue<Vozilo> put = new PriorityQueue<>();
	static Queue<Vozilo> automobili = new PriorityQueue<>();
	static Queue<Vozilo> kamioni = new PriorityQueue<>();
	static Queue<Vozilo> autobusi = new PriorityQueue<>();
	static Object lock = new Object();
	static Object lock1 = new Object();
	static Object lock2 = new Object();
	static Object lock3 = new Object();
	static Object lockFile1 = new Object();
	
	static int ukupanBrojPutnika = 0;
	static int ukupnaVrijednostTakse = 0;
	
	static public void main(String args[]){
		for(int i=0;i<5;i++){
			put.add(new Automobil());
			put.add(new Kamion());
			put.add(new Autobus());
		}
		
		List<Terminal> terminali = new ArrayList<>();
		terminali.add(new Terminal());
		terminali.add(new Terminal());
		terminali.add(new Terminal());
		
		for(Terminal t: terminali){
			t.start();
		}
		
		Thread rasporedjivac = new Thread(() -> {
			boolean radi = true;
			while(radi){
				Vozilo v = null;
				synchronized(Main.lock3){
					if(put.peek() != null){
						v = put.peek();
					}else{
						radi = false;
						break;
					}
				}
				if(v != null){
					if(v  instanceof RegularnoInterface){
						synchronized(Main.lock){
							if(automobili.size()<3){
								automobili.add(v);
								synchronized(Main.lock3){
									put.poll();
								}
							}
						}
					}else if(v instanceof PosebnaProceduraInterface){
						synchronized(Main.lock1){
							if(kamioni.size()<3){
								kamioni.add(v);
								synchronized(Main.lock3){
									put.poll();
								}
							}
						}
					}else{
						synchronized(Main.lock2){
							if(autobusi.size()<3){
								autobusi.add(v);
								synchronized(Main.lock3){
									put.poll();
								}
							}
						}
					}
					
					try{
						Thread.sleep(100);
					}catch(InterruptedException e){
						System.out.println("GRESKA pri pauziranju traka.");
					}
				}
						
				
			}
			
		});
		
		rasporedjivac.start();
		
		try{
			rasporedjivac.join();
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju rasporedjivaca.");
		}
		
		try{
			for(Terminal t:terminali){
				t.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju terminala.");
		}
		
		System.out.println("Ukupan broj putnika: " + ukupanBrojPutnika);
		System.out.println("Ukupna vrijednost takse: " + ukupnaVrijednostTakse);
		
	}
}