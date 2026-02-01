class Motor extends Thread{
	static public enum StanjeMotora{
		UPALJEN, UGASEN, POKVAREN;
	}
	
	StanjeMotora stanjeMotora;
	int snaga;
	boolean motorRadi;
	
	Motor(){
		stanjeMotora = StanjeMotora.UPALJEN;
		snaga = Main.rand.nextInt(1000, 10001);
		motorRadi = true;
	}
	
	public void run(){
		while(motorRadi){
			double x = Main.rand.nextDouble(0, 1);
			if(x < 0.1){
				motorRadi = false;
				stanjeMotora = StanjeMotora.UGASEN;
			}
			try{
				sleep(500); 
			}catch(InterruptedException e){
				motorRadi = false;
				break;
        }
		}
	}
	
	
}