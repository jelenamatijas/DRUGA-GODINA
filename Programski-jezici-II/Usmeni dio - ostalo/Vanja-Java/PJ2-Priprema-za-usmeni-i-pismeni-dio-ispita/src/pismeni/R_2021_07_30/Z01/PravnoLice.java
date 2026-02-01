package pismeni.R_2021_07_30.Z01;

import java.util.*;

public class PravnoLice extends Kupac{

	public PravnoLice(){
		super(new Random().nextDouble() * 50 + 50.0);
	}

	public String toString(){
		return "PRAVNO " + super.toString();
	}

	public boolean isFizickoLice(){
		return false;
	}
}