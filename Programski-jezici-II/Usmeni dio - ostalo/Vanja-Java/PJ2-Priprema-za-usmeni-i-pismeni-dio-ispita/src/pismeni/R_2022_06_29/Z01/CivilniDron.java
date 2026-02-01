package pismeni.R_2022_06_29.Z01;

import java.util.*;

public class CivilniDron extends Dron implements Civilni{

	int domet;

	public CivilniDron(){
		super();
		domet = new Random().nextInt(Simulacija.DIM);
	}
}