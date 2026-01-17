import java.util.*;
import java.util.stream.*;

class Skladiste{
	String nazivSkladista;
	Map<Integer, Artikl> artikli;
	
	public Skladiste(String nazivSkladista){
		this.nazivSkladista = nazivSkladista;
		artikli = new HashMap<>();
	}
	
	public void add(Artikl a)throws NullPointerException{
		if(a == null){
			throw new NullPointerException("Proslijedjeni artikl je NULL.");
		}else{
			artikli.put(a.id, a);
		}
	}
	
	public void dodaj(Artikl a){
		Artikl artikl = artikli.get(a.id);
		if(artikl == null){
			artikli.put(a.id, a);
		}else{
			artikl.kolicina = a.kolicina + artikl.kolicina;
		}
	}
	
	public void ukloni(int id, int kolicina)throws NedovoljnaKolicinaException{
		Artikl artikl = artikli.get(id);
		if(artikl == null || artikl.kolicina<kolicina){
			throw new NedovoljnaKolicinaException("GRESKA: Nedovoljna kolicina artikla u skladistu: " + artikl);
		}else{
			artikl.kolicina = artikl.kolicina - kolicina;
		}
	}
	
	@Override
	public String toString(){
		String lista = artikli.values().stream().map(Object::toString).collect(Collectors.joining("\n"));
		return "Skladiste{Naziv: " + nazivSkladista + " ARTIKLI:\n" + lista + "}";
	}
}