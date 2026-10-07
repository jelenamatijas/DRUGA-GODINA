
abstract class Proizvod{
	String id;
	String naziv;
	
	public Proizvod(String id, String naziv){
		this.id = id;
		this.naziv = naziv;
	}
	
	@Override
	public String toString(){
		return "ID: " + id + " Naziv: " + naziv;
	}
}