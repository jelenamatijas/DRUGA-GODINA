package net.etfbl.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteArrayIOStream {

	public static void main(String[] args) {
		byte b[] = "Ovo je string literal pretvoren u bajte...".getBytes();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int i;
			while((i = bais.read())!=-1)
				baos.write(i);
			System.out.println(baos.toString());
			bais.close();
			baos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
