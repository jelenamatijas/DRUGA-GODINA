package net.etfbl.io;

import java.io.File;

public class FileTest {
	public FileTest(String s) {
		try {
			// Pozicioniramo se u zadati direktorijum.
			File f = new File(s);
			if(f.isDirectory()){
				// Izlistamo sadrzaj zadatog direktorijuma.
				File[] list = f.listFiles();
				for (int i = 0; i < list.length; i++)
					if (list[i].isDirectory()){ // ako je direktorijum, tj. poddirektorijum
						String st = list[i].isHidden()?"HIDDEN:":"";
						System.out.println(st + " <" + list[i].getCanonicalPath() + ">");
					}
					else
						System.out.println(list[i].getName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FileTest("E:");
	}
}