package net.etfbl.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedRW {

	public static void main(String[] args) {
		char c[] = "Ovo je string literal pretvoren u karaktere...".toCharArray();
		try {
			CharArrayReader car = new CharArrayReader(c);
			CharArrayWriter caw = new CharArrayWriter();
			BufferedReader br = new BufferedReader(car);
			BufferedWriter bw = new BufferedWriter(caw);
			String s;
			while ((s = br.readLine())!=null){
				bw.write(s);
				bw.newLine();
			}		
			System.out.println("prije flush: " + caw.toString());
			bw.flush();
			System.out.println("poslije flush: " + caw.toString());
			car.close();
			caw.close();
			br.close();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}