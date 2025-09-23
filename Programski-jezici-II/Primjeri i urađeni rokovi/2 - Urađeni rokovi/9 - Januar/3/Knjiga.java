public class Knjiga {
	
	String naslov;
	Autor autor;
	
	private static int redniBroj = 1;
	
	public Knjiga() {
		naslov = "Naslov" + redniBroj++;
		autor = new Autor();
	}
	
	@Override
	public String toString() {
		return "Knjiga{naslov=" + naslov + ", autor=" + autor + "}\n";
	}
}