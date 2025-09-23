import java.util.Objects;
import java.util.*;

class Pas{
	int godinaRodjenja;
	String ime;
	double tezina;
	OmiljenaHrana omiljenaHrana;
	static int id=0;
	
	public Pas(){
		Random rand = new Random();
		godinaRodjenja = rand.nextInt(10) + 2015;
		ime = "Ime" + id;
		id++;
		if(id % 3 == 0){
			omiljenaHrana = OmiljenaHrana.MESO;
		}else if(id % 3 ==1 ){
			omiljenaHrana = OmiljenaHrana.PILETINA;
		}else{
			omiljenaHrana = OmiljenaHrana.RIBA;
		}
		tezina = rand.nextDouble(10) + 5;
	}
	
	public OmiljenaHrana getOmiljenaHrana(){
		return omiljenaHrana;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || !(obj instanceof Pas)){
			return false;
		}
		
		if(this == obj || (godinaRodjenja == ((Pas)obj).godinaRodjenja && ime == ((Pas)obj).ime)){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(godinaRodjenja, ime);
	}
	
	@Override
	public String toString(){
		return "PAS{" + ime + " Tezina: " + tezina + " Omiljena hrana: " + omiljenaHrana + " Godina rodjenja: " + godinaRodjenja + "}";
	}
	
	public enum OmiljenaHrana{
		MESO, PILETINA, RIBA;
	}
}