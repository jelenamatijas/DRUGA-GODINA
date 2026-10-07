import java.util.*;

class Vozilo{
	int cijena;
	int taksa;
	int ukupnaCijena;
	int id;
	static int ID = 1;
	TipRegistracije tip;
	static enum TipRegistracije{
		Obicna_registracija,Registracija_plus_kasko,Registracija_plusa_kasko_plus_pomoc_na_putu;
	}
	
	static List<TipRegistracije> tipReg = new ArrayList<>();
	
	
	Vozilo(int t){
		add();
		tip = tipReg.get(Main.rand.nextInt(0,3));
		if(tip.equals(TipRegistracije.Obicna_registracija)){
			cijena = 100;
		}else if(tip.equals(TipRegistracije.Registracija_plus_kasko)){
			cijena = 200;
		}else{
			cijena = 250;
		}
		taksa = t;
		ukupnaCijena = cijena+taksa;
		id = ID++;
	}
	
	static void add(){
		tipReg.add(TipRegistracije.Obicna_registracija);
		tipReg.add(TipRegistracije.Registracija_plus_kasko);
		tipReg.add(TipRegistracije.Registracija_plusa_kasko_plus_pomoc_na_putu);
	}
	
	@Override
	public String toString(){
		return "Vozilo: " + id + "\n\t-> Tip registracije: " + tip.name() + " Cijena: " + cijena + " Taksa: " + taksa + " Ukupna cijena: " + ukupnaCijena;
	}
}