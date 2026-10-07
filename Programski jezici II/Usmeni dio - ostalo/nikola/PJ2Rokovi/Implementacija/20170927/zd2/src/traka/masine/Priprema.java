package src.traka.masine;

public class Priprema extends Masina{
	
	public Priprema(){
		
	}
	
	public boolean obradi(Materijal p){
		System.out.println("Priprema materijala " + p + " traje " + speed);
		try{
			Thread.sleep(speed);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return true;
	}
}