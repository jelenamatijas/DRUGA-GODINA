
class Hrana extends Artikl{
	int rokTrajanja;
	
	public Hrana(String naziv,int id,int kolicina,int rokTrajanja){
		super(naziv, id, kolicina);
		this.rokTrajanja = rokTrajanja;
	}
	
	@Override
	public String toString(){
		return "Hrana{" + super.toString() + " Rok trajanja: " + rokTrajanja + "}";
	}
}