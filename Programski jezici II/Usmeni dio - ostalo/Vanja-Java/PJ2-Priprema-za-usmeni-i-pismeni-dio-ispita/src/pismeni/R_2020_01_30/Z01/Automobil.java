package pismeni.R_2020_01_30.Z01;

import java.util.*;

public class Automobil extends Vozilo {
	ArrayList<Supermoc> moci = new ArrayList<>();

	public Automobil(String id, Vozac vozac, Motor motor, String konfig, ArrayList<Supermoc> moci){
		super(id,vozac,motor,konfig, 1.0);
		this.moci = moci;
	}

	public String toString(){
		return "AUTO " + super.toString();
	}
}