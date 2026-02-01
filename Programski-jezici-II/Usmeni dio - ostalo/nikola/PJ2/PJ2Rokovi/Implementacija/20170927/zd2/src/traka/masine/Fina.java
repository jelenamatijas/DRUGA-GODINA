package src.traka.masine;

public class Fina extends Masina{
	
	public Fina(){
		
	}
	
	public boolean obradi(Materijal p) {
		try{
			if(p instanceof ISaviti){
				System.out.println("Fina obrada materijala " + p + " traje " + speed);
				try{
					Thread.sleep(speed);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				
				return true;
		} else 
			throw new Exception("Fina masina " + id + " ne moze da obradi materijal " + p.toString());		
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	return false;

}
}