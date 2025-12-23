package src.main;

public class Customs extends Thread{
	private CustomsTerminal truckTerminal, ordinaryTerminal;
	
	public Customs(CustomsTerminal truckTerminal, CustomsTerminal ordinaryTerminal){
		this.truckTerminal = truckTerminal;
		this.ordinaryTerminal = ordinaryTerminal;
	}
	
	public void run(){
		truckTerminal.start();
		ordinaryTerminal.start();
	}
}