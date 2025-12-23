package net.etfbl.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOStream {

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("java2.txt");
			FileInputStream fis = new FileInputStream("java.txt");
			DataInputStream dis = new DataInputStream(fis);
			DataOutputStream dos = new DataOutputStream(fos);
			int l;
			while((l=dis.read())!=-1)
				dos.write(l);
			fos.close();
			fis.close();
			dis.close();
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
