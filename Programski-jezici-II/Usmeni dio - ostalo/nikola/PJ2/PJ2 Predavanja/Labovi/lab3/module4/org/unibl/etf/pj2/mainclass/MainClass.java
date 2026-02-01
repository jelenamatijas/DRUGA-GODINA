package org.unibl.etf.pj2.mainclass;

import org.unibl.etf.pj2.student.Student;
import org.unibl.etf.pj2.integer.PJ2Integer;

public class MainClass{
	public static void main(String args[]){
		Student s1 = new Student("Marko", "Markovic", 33);
		Student s2 = new Student("Jovan", "Jovanovic", 23);
		System.out.println(s1.getbrojIspita()+s2.getbrojIspita());
		System.out.println(PJ2Integer.int2string(s1.getbrojIspita()));
		System.out.println(PJ2Integer.int2string(s2.getbrojIspita()));
		System.out.println(s1.getFullNameReverse());
		System.out.println(s2.getFullNameReverse());
	}
}