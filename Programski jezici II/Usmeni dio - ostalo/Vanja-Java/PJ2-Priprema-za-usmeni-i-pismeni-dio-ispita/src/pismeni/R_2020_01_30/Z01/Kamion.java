package pismeni.R_2020_01_30.Z01;

public class Kamion extends Vozilo implements PrevoziTeret {

	public Kamion(){

	}
	public Kamion(String id, Vozac vozac, Motor motor, String konfig){
		super(id,vozac,motor,konfig, 1.0);
	}

	public String toString(){
		return "KAMION " + super.toString();
	}
}