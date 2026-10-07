class NavigacioniModul extends Thread{
	int x, y;
	volatile boolean modulRadi;
	
	NavigacioniModul(int x, int y){
		this.x = x;
		this.y = y;
		modulRadi = true;
	}
	
	public void run(){
		for(int i=0;i<10 && modulRadi;i++){
			if(modulRadi){
				String opis =  "Letjelica se pomjerila na koordinate (" + x + "," + y + ").";
				
				if(modulRadi){
					synchronized(Main.lockKomunikacija){
						Main.poruke.add(new Poruka(Main.time(), opis, KomunikacioniModul.Prioritet.INFO));
					}
				}
				try{
					sleep(1000);
				}catch(InterruptedException e){  
					modulRadi = false;
					break;  
				}
				x = Main.rand.nextInt(0,101);
				y = Main.rand.nextInt(0,101);
			}
		}
		modulRadi = false;
	}
}