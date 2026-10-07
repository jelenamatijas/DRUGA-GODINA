
class Hidrocentrala extends Thread{
	boolean radi;

	static Modul[] moduli;
	
	public Hidrocentrala(){
		radi = false;
		moduli = new Modul[4];
		moduli[0] = new ProizvodniModul();
		moduli[1] = new ProizvodniModul();
		moduli[2] = new DistributivniModul();
		moduli[3] = new KontrolniModul();
	}
	
	@Override 
	public void run(){
		radi = true;
		for (Modul m : moduli) {
			m.start();
		}
		while(radi){
			if(Main.rijeka.nivo < 50){
				for(Modul m: moduli){
					m.radi = false;
				}
				radi = false;
			}
			
			try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Main.rijeka.setNivo();
		}
		try{
			for(Modul m: moduli){
				m.radi = false;
				m.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju modula.");
		}
		System.out.println("HIDROCENRALA PRESTALA SA RADOM.");	
			
	}
}