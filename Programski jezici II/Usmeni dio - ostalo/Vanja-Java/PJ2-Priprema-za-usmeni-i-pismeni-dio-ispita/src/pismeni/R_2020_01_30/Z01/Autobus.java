package pismeni.R_2020_01_30.Z01;

public class Autobus extends Vozilo {
	int mjesta;

	public Autobus(){

	}

	public Autobus(String id, Vozac vozac, Motor motor, String konfig, int mjesta){
		super(id,vozac,motor,konfig, 0.9);
		this.mjesta = mjesta;
	}

	public String toString(){
		return "AUTOBUS " + super.toString();
	}
}