package pismeni.R_2021_07_30.Z01;

import java.util.*;

public class Odrasli extends FizickoLice{

	public Odrasli(){
		prioritet = new Random().nextInt(2) == 1;
	}

	public Odrasli(boolean prioritet){
		this.prioritet = prioritet;
	}

	public String toString(){
		return "ODRASLI: " + prioritet + " " + super.toString();
	}
}