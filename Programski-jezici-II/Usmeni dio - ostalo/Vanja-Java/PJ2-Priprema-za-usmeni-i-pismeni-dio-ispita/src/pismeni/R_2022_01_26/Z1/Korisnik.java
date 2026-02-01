public class Korisnik{
	
	String username;
	static int count = 1;
	
	public Korisnik(){
		this.username = "Korisnik " + count++;
	}
	
	public Korisnik(String username){
		this.username = username;
		count++;
	}
	
	@Override
	public String toString(){
		return username;
	}
}