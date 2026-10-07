import java.nio.file.*;
import java.io.*;

public class Dokument {
	
	String naziv;
	String sadrzaj;
	
	public Dokument(Path path) {
		this.naziv = path.getFileName().toString();
		try {
			this.sadrzaj = Files.readString(path);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja sadrzaja");
		}
	}
}