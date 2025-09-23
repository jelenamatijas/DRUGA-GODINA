import java.util.Objects;

class Film{
	String naziv, reditelj;
	int godina;
	Zanr zanr;
	
	static int id = 1;
	
	public Film(){
		naziv = "Film_" + id++;
		reditelj = "Reditelj_" + Main.rand.nextInt(1, 10);
		godina = Main.rand.nextInt(30) + 1995;
		int x = Main.rand.nextInt(4);
		if(x%5==0){
			zanr = Zanr.AKCIJA;
		}else if(x%5==1){
			zanr = Zanr.DRAMA;
		}else if(x%5==2){
			zanr = Zanr.KOMEDIJA;
		}else{
			zanr = Zanr.DOKUMENTARAC;
		}
	}
	
	@Override
	public String toString(){
		return "FILM -> " + naziv + " " + reditelj + " Godina: " + godina + " " +zanr;
	}
	
	@Override 
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}
		Film f = (Film)obj;
		return (f.godina == godina && naziv.equals(f.naziv));
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(naziv, godina, reditelj, zanr);
	}
	
	public enum Zanr{
		AKCIJA, DRAMA, KOMEDIJA, DOKUMENTARAC;
	}
}