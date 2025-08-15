public class Klijent{
	private String ime;
	private String prezime;
	private String jmbg;
	private double stanjeNaRacunu;
	
	/**Klijent(){
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.stanjeNaRacunu = 0;
	}*/
	
	Klijent(String ime, String prezime, String jbmg, double stanjeNaRacunu){
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.stanjeNaRacunu = stanjeNaRacunu;
	}
	
	public void setStanjeNaRacunu(double iznos){
		stanjeNaRacunu -= iznos;
	}
	
	public double getStanjeNaRacunu(){
		return stanjeNaRacunu;
	}
	public String toString(){
		return ime + " " + prezime + "\n JMBG: " + jmbg + "\n Stanje na racunu: " + stanjeNaRacunu;
	}
}