package net.etfbl.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputStreamRW {
	public static void main(String[] args) throws IOException {
		byte b[] = "Ovo je string literal pretvoren u bajte...".getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(b);	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	
		InputStreamReader isr = new InputStreamReader(bais);
		OutputStreamWriter osw = new OutputStreamWriter(baos);
		while(isr.ready())
			osw.write((char)isr.read());
		osw.flush();
		System.out.println(baos.toString());
		bais.close();
		baos.close();
		isr.close();
		osw.close();
	}
}
