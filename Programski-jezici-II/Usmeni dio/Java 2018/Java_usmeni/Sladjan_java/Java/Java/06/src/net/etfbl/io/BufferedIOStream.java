package net.etfbl.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedIOStream {

	public static void main(String[] args) {
		byte b[] = "Ovo je string literal pretvoren u bajte...".getBytes();
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedInputStream bis = new BufferedInputStream(bais);
			BufferedOutputStream bos = new BufferedOutputStream(baos);
			int i;
			while((i = bis.read())!=-1){
				bos.write(i);
			}
			System.out.println("baos prije flush: " + baos.toString());
			bos.flush();
			System.out.println("baos poslije flush: " + baos.toString());
			bais.close();
			baos.close();
			bis.close();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
