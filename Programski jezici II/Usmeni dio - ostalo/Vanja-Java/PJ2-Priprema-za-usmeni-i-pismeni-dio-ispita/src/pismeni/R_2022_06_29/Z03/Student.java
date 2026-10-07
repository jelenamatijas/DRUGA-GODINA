package pismeni.R_2022_06_29.Z03;

import java.util.*;

public class Student{

	String ime;
	String prezime;
	String indeks;
	double ocjena;
	static int count = 0;

	public Student(){
		ime = "Ime" + count++;
		prezime = "Prezime" + count;
		indeks = "indeks" + count;
		ocjena = (double) new Random().nextInt(5) + 6;
	}

	public String toString(){
		return ime + " " + prezime + " " + indeks;
	}
}