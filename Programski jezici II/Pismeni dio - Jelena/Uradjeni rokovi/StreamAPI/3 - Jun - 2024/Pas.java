import java.util.Objects;

class Pas{
	int godina;
	String ime;
	double tezina;
	OmiljenaHrana omiljenaHrana;
	
	public Pas(){
		godina = Main.rand.nextInt(10) + 2010;
		ime = "Pas_" + Main.rand.nextInt(1, 20);
		int x = Main.rand.nextInt(3);
		if(x % 3 == 0){
			omiljenaHrana = OmiljenaHrana.MESO;
		}else if(x % 3 ==1){
			omiljenaHrana = OmiljenaHrana.PILETINA;
		}else{
			omiljenaHrana = OmiljenaHrana.RIBA;
		}
		tezina = Main.rand.nextDouble(5, 10);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || this.getClass() != obj.getClass()){
			return false;
		}
		Pas pas = (Pas)obj;
		return (ime.equals(pas.ime) && godina==pas.godina);
	}
	
	public int hashcode(){
		return Objects.hash(godina, ime, tezina, omiljenaHrana);
	}
	
	@Override
	public String toString(){
		return "PAS -> Ime: " + ime + " Godina rodjenja: " + godina + " Tezina: " + tezina + " Omiljena hrana: " + omiljenaHrana;
	}
	
	public enum OmiljenaHrana{
		MESO, PILETINA, RIBA;
	}
}