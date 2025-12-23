package src.traka.masine;

public class Pakovanje extends Masina{
	
	public Pakovanje(){
		
	}
	
	public boolean obradi(Materijal p){
		System.out.println("Pakovanje materijala " + p + " traje " + speed);
		try{
			Thread.sleep(speed);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return true;
	}
}