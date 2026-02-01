import java.util.Objects;

class Knjiga{
	String naziv;
	String autor;
	int godina;
	Zanr zanr;
	int brojStranica;
	static int id = 1;
	
	public Knjiga(){
		naziv = "Naziv_" + id;
		autor = "Autor_" + Main.rand.nextInt(1, 6);
		godina = Main.rand.nextInt(26) + 2000;
		if(id % 3 == 0){
			zanr = Zanr.FIKCIJA;
		}else if( id % 3 == 1){
			zanr = Zanr.NAUKA;
		}else{
			zanr = Zanr.ISTORIJA;
		}
		brojStranica = Main.rand.nextInt(100, 400);
		id++;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}
		Knjiga k = (Knjiga)obj;
		return (k.naziv.equals(naziv) && k.autor.equals(autor));
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(naziv, autor, godina, zanr, brojStranica);
	}
	
	@Override
	public String toString(){
		return "Knjiga -> " + naziv + " " + autor + " Godina: " + godina + " Zanr: " + zanr + " Broj stranica: " + brojStranica;
	}
	
	public enum Zanr{
		FIKCIJA, NAUKA, ISTORIJA;
	}
}