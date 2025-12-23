package net.etfbl.io;

import java.io.*;

public class Redirect {

	public static void main(String[] args) throws Exception {
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream("java.txt"));
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream("test.txt")));
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = br.readLine()) != null)
			System.out.println(s);
		out.close();
		System.setOut(console);

	}

}
