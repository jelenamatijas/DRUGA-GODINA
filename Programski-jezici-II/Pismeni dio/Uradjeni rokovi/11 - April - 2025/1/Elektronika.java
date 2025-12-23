
class Elektronika extends Artikl{
	int garancija;
	
	public Elektronika(String naziv,int id,int kolicina,int garancija){
		super(naziv, id, kolicina);
		this.garancija = garancija;
	}
	
	@Override
	public String toString(){
		return "Elektronika{" + super.toString() + " Garancija: " + garancija + "}";
	}
}