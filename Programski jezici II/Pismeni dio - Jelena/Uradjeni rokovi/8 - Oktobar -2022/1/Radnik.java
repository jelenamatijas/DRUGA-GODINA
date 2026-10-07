class Radnik implements Runnable{
	String ime, prezime;
	int godinaRodjenja;
	boolean radi = true;
	Predmet predmet=null;
	
	Radnik(String i, String p){
		ime = i;
		prezime =p;
		godinaRodjenja = Main.rand.nextInt(1980, 2005);
	}
	
	public void akcija(){}
	
	public void start(Predmet p){
		predmet = p;
		this.run(); 
	}
	
	public void run(){
		
		synchronized(Main.lockPauza){
			while(Main.pauza){
				try{
					Main.lockPauza.wait();
				}catch(InterruptedException e){}
			}
		}
		
		this.akcija();
		
		try{
			Thread.sleep(100);
		}catch(InterruptedException e){
			System.out.println("Greska pri pauziranju radnika.");
		}
	}
}