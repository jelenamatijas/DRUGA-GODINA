import java.time.LocalDateTime;

public abstract class Poruka{

	String naslov;
	String sadrzaj;
	String dt;
	
	
	public Poruka(){
		this.dt = LocalDateTime.now().toString();
	}
	
	public Poruka(String naslov, String sadrzaj){
		this.naslov = naslov;
		this.sadrzaj = sadrzaj;
		this.dt = LocalDateTime.now().toString();
	}
	
	@Override
	public String toString(){
		return naslov + " " + sadrzaj + " " + dt;
	}
}