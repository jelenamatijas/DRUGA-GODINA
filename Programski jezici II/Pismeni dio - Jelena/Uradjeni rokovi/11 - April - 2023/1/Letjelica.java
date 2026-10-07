class Letjelica extends Thread{
	PogonskiModul pogon;
	NavigacioniModul nav;
	KomunikacioniModul kom;
	volatile boolean leti;
	
	public Letjelica(){
		pogon = new PogonskiModul();
		nav = new NavigacioniModul(10,10);
		kom = new KomunikacioniModul();
		leti = true;
	}
	
	public void run(){
		kom.start();
		nav.start();
		pogon.start();

		while(leti){
			if(pogon.ugasen || !nav.modulRadi){
				pogon.ugasen = true;
				nav.modulRadi = false;
				nav.interrupt();
				pogon.interrupt();
				try{
					sleep(1000);
				}catch(Exception e){
					System.out.println("Greska pri cekanju na prestanak rada pogona. " + e);
				}
				
				kom.modulRadi = false;
				leti = false;
				kom.interrupt();
				
				try{
					sleep(1000);
				}catch(Exception e){
					System.out.println("Greska pri cekanju na prestanak rada pogona. " + e);
				}
			
				try{
					nav.join();
					kom.join();
					pogon.join();
				}catch(Exception e){
					System.out.println("Greska pri zaustavljanju pogona.");
				}
			}
			try{
				sleep(100);
			}catch(Exception e){
				System.out.println("Greska pri privremenom zaustavljanju letjelice. " + e);
			}
		}
	}
}