
class Prekrsaj{
	String naziv;
	int iznos;
	
	public Prekrsaj(String naziv){
		this.naziv = naziv;
		iznos = Main.rand.nextInt(10, 51);
	}
	
	@Override
	public String toString(){
		return "Naziv: " + naziv + " Iznos kazne: " + iznos;
	}
}