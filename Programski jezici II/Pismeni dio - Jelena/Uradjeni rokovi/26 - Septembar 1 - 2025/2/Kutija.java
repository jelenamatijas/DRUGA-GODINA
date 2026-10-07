
class Kutija{
	String id;
	double visina, sirina, duzina;
	int tezina;
	Tip tip;
	
	public Kutija(String id, double visina, double sirina, double duzina, int tezina, Tip tip){
		this.id = id;
		this.visina = visina;
		this.sirina = sirina;
		this.duzina = duzina;
		this.tezina = tezina;
		this.tip = tip;
	}
	
	@Override
	public String toString(){
		return "KUTIJA -> ID: " + id + " Visina: " + visina + " Sirina: " + sirina + " Duzina: " + duzina + " Tip: " + tip;
	}
	
	public enum Tip{
		STANDARD, FRAGILE, REFRIGERATED;
	}
}