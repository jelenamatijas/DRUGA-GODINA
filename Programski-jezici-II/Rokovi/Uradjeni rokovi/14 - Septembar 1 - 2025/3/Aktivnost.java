import java.util.Objects;

class Aktivnost{
	String naziv, organizator;
	int godina;
	Tip tip;
	int brojUcesnika;
	double budzet;
	
	static int id =  1;
	
	public Aktivnost(){
		naziv = "AKTIVNOST_" + id;
		organizator = "ORGANZATOR_" + Main.rand.nextInt(1,10);
		if(id % 3 == 0){
			tip = Tip.PREDAVANJE;
		}else if(id % 3 == 1){
			tip = Tip.RADIONICA;
		}else{
			tip = Tip.TAKMICENJE;
		}
		godina = Main.rand.nextInt(15) + 2015;
		brojUcesnika = Main.rand.nextInt(10, 30);
		budzet = (double)Main.rand.nextInt(1000, 1100);
		id++;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}
		Aktivnost a = (Aktivnost) obj;
		return (naziv.equals(a.naziv) && organizator.equals(a.organizator) && tip == a.tip && brojUcesnika == a.brojUcesnika && budzet == a.budzet);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(naziv, organizator, godina, tip, brojUcesnika, budzet);
	}
	
	@Override
	public String toString(){
		return "AKTIVNOST -> " + naziv + " " + organizator + " Godina: " + godina + " Tip: " + tip + " Broj ucesnika: " + brojUcesnika + " Budzet: " + budzet;
	}
	
	public enum Tip{
		PREDAVANJE, RADIONICA, TAKMICENJE;
	}
}