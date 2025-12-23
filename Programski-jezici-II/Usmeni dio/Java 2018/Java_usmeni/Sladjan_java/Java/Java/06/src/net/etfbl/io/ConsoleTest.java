package net.etfbl.io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleTest {

	public static void main(String[] args) throws IOException {
		Console console = System.console();
		String s = console.readLine("Unesite: ");
		System.out.println("Unijeli ste: " + s);
		String p = new String(console.readPassword("Unesite lozinku: "));
		System.out.println("Unijeli ste lozinku: " + p);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String s2;
	    while((s2 = in.readLine()) != null && s2.length() != 0)
	      System.out.println(s2);

	}

}
