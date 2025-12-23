package net.etfbl.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRW {
		public static void main(String[] args) throws IOException {
			FileReader fr = new FileReader("ulaz.txt");
			FileWriter fw = new FileWriter("izlaz.txt");
			int i;
			while((i = fr.read())!=-1)
				fw.write((char)i);
			fr.close();
			fw.close();
		}
	}
