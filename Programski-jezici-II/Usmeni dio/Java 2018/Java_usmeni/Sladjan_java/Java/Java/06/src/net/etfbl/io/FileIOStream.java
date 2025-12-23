package net.etfbl.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOStream {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("google2.png");
			FileInputStream fis = new FileInputStream("google.png");
			byte b[] = new byte[1024];
			int l;
			while((l=fis.read(b))!=-1)
				fos.write(b,0,l);
			fos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
