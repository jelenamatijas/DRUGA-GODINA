package net.etfbl.io;

import java.util.zip.*;
import java.io.*;

public class ZIPTest {

	private static final int BUFFER_LENGTH = 1024;

	public static void main(String[] args) {
		byte[] buffer = new byte[BUFFER_LENGTH];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream("test.zip"));
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream("music.wav"));
			out.putNextEntry(new ZipEntry("music.wav"));
			int read;
			while ((read = fin.read(buffer, 0, BUFFER_LENGTH)) != -1) {
				out.write(buffer, 0, read);
			}
			fin.close();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
