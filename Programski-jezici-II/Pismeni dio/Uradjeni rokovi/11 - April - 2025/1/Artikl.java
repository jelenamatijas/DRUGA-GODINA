
abstract class Artikl{
	String naziv;
	int id;
	int kolicina;
	
	public Artikl(String naziv,int id,int kolicina){
		this.naziv = naziv;
		this.id = id;
		this.kolicina = kolicina;
	}
	
	@Override
	public String toString(){
		return "Naziv: " + naziv + " ID: " + id + " Kolicina: " + kolicina;
	}
}