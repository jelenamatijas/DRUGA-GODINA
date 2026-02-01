package pismeni.R_2021_07_30.Z01;

import java.io.*;

public abstract class Artikal implements Serializable{
	double cijena;
	boolean kupljen = false;

	public Artikal(){
	}

	public String toString(){
		return "" + cijena;
	}
}