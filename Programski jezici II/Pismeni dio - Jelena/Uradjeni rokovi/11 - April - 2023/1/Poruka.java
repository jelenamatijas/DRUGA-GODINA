public class Poruka{
	String vrijeme, opis;
	KomunikacioniModul.Prioritet prioritet;
	
	Poruka(String v, String o, KomunikacioniModul.Prioritet p){
		vrijeme = v;
		opis = o;
		prioritet = p;
	}
	
	@Override
	public String toString(){
		return vrijeme + " -> " + prioritet.name() + ": " + opis;
	}
}