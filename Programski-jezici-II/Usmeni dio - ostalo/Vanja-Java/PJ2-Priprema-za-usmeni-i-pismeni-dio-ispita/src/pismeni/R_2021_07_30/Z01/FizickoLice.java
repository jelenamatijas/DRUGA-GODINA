package pismeni.R_2021_07_30.Z01;

import java.util.*;

public class FizickoLice extends Kupac {

	public FizickoLice(){
		super(new Random().nextDouble() * 30 + 20.0);
	}

	public String toString(){
		return "FIZICKO " + super.toString();
	}

	public boolean isFizickoLice(){
		return true;
	}
}