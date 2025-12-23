import java.util.Objects;

class Uredjaj{
	String naziv;
	String proizvodjac;
	int godina;
	Tip tip;
	double tezina;
	int cijena;
	
	static int id = 1;
	
	public Uredjaj(){
		naziv = "Uredjaj_" + id;
		proizvodjac = "Proizvodjac_" + Main.rand.nextInt(1, 6);
		godina = Main.rand.nextInt(15) + 2010;
		if(id % 3 == 0){
			tip = Tip.telefon;
		}else if(id % 3 == 1){
			tip = Tip.tablet;
		}else{
			tip = Tip.laptop;
		}
		tezina = Main.rand.nextDouble(1000, 3000);
		cijena = Main.rand.nextInt(300, 3000);
		id++;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}
		Uredjaj u = (Uredjaj)obj;
		return (naziv.equals(u.naziv) && proizvodjac.equals(u.proizvodjac) && tip == u.tip && tezina == u.tezina && cijena == u.cijena);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(naziv, proizvodjac, godina, tip, tezina, cijena);
	}
	
	@Override
	public String toString(){
		return "UREDJAJ -> " + naziv + " " + proizvodjac + " Godina: " + godina + " Tip: " + tip + " Tezina: " + tezina + " Cijena: " + cijena;
	}
	
	public enum Tip{
		telefon, tablet, laptop;
	}
}