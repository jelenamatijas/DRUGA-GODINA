class Vegetarijanac extends Gost implements VegetarijanacInterface{
	
	Vegetarijanac(){
		super();
	}
	
	@Override
	public String toString(){
		return "ID gosta: " + id + " Novac: " + iznos;
	}
}