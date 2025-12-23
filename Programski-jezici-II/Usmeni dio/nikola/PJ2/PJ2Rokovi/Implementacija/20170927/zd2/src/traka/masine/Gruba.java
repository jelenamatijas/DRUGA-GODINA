package src.traka.masine;

public class Gruba extends Masina{
	
	public Gruba(){
		
	}
	
	public boolean obradi(Materijal p) {
		try{
			if(p instanceof ITopiti || p instanceof ISjeci){
				System.out.println("Gruba obrada materijala " + p + " traje " + speed);
				try{
					Thread.sleep(speed);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				
				return true;
		} else 
			throw new Exception("Gruba masina " + id + " ne moze da obradi materijal " + p.toString());		
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	return false;

}
}