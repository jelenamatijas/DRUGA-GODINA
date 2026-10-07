package src.main;

public PumpaWatch extends Thread{
	private Pumpa pumpa;
	
	public PumpaWatch(Pumpa pumpa){
		this.pumpa = pumpa;
	}
	
	void run(){
		while(pumpa.isRunning(){
			System.out.println("Stanje na pumpama koje toce");
			System.out.println(pumpa);
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}