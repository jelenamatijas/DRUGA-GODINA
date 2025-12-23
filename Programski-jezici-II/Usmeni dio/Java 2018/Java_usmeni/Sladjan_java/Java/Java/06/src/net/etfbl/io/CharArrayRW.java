package net.etfbl.io;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CharArrayRW {

	public static void main(String[] args) {
		char c[] = "Ovo je string literal pretvoren u niz karaktera...".toCharArray();
		try {
			CharArrayReader car = new CharArrayReader(c);
			CharArrayWriter caw = new CharArrayWriter();
			int i;
			while((i = car.read())!=-1)
				caw.write(i);
			System.out.println(caw.toString());
			car.close();
			caw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
