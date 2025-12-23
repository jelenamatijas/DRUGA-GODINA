package org.unibl.etf.pj2.student;

import org.unibl.etf.pj2.util.Util;

public class Student{
	private String ime, prezime;
	private int brojIspita;
	
	public Student(String ime, String prezime, int brojIspita){
		this.ime = ime;
		this.prezime = prezime;
		this.brojIspita = brojIspita;
	}
	
	public int getbrojIspita(){
		return brojIspita;
	}
	
	public String getFullNameReverse(){
		return new StringBuilder(Util.upConcat(ime, prezime)).reverse().toString();
	}
}