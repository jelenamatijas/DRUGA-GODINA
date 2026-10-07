package src;

public class RestoranBazenObserve extends Thread {
	private BazenObserve bazen;
	private RestoranObserve restoran;
	
	public RestoranBazenObserve(BazenObserve bazen, RestoranObserve restoran){
		this.bazen = bazen;
		this.restoran = restoran;
	}
	
	public void run(){
		restoran.start();
		bazen.start();
	}
}