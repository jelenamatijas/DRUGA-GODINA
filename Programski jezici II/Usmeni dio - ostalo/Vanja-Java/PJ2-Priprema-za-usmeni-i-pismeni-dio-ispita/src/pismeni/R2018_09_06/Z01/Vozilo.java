package pismeni.R2018_09_06.Z01;

import java.util.*;
import java.io.*;

public abstract class Vozilo implements Serializable, Comparable<Vozilo>{

	String naziv;
	int kapacitet;
	int stanjeGoriva;
	Gorivo gorivo;
	static int count;

	public Vozilo(){
		Random rand = new Random();
		naziv = "Vozilo " + (++count);
		kapacitet = rand.nextInt(71) + 30;
		stanjeGoriva = rand.nextInt(kapacitet);
		int g = rand.nextInt(2);
		switch(g){
			case 0:
				gorivo = Gorivo.BENZIN;
				break;
			case 1:
				gorivo = Gorivo.DIZEL;
				break;
		}
	}

	public String toString(){
		return naziv + " " + kapacitet + " stanjeGoriva: " + stanjeGoriva + " " + gorivo;
	}

	public void uspiGorivo(){
		stanjeGoriva += 10;
	}
	public int compareTo(Vozilo v){
		return Integer.compare(v.stanjeGoriva, this.stanjeGoriva);
	}
}