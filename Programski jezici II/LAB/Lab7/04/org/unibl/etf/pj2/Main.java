package org.unibl.etf.pj2;

import java.io.IOException;

import org.unibl.etf.pj2.service.StudentService;

public class Main {

	public static void main(String[] args) {
		try {
			StudentService.updateData();
			System.out.println("Analiza zavrsena");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
