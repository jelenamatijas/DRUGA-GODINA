import java.util.*;
import java.nio.file.*;

public class Uredjaj extends Thread {
	
	String naziv;
	String proizvodjac;
	int brojRadnihMiliSekundi;
	Path path;
	boolean radi = true;
	
	Random random = new Random();
	
	private static int redniBroj = 1;
	
	public Uredjaj(Path path) {
		naziv = "naziv" + redniBroj;
		proizvodjac = "proizvodjac" + redniBroj;
		brojRadnihMiliSekundi = 0;
		this.path = path;
	}
}