import java.nio.file.*;
import java.util.*;

public class Main {
	
	public static Dokument dokument = new Dokument(Paths.get("tekst.txt"));
	
	public static Object lock = new Object();
	
	public static int brojSlovaO = 0;
	public static int brojevaIzbaceno = 0;
	
	public static void main(String[] args) {
		CopyMaker copyMaker = new CopyMaker();
		CopyReader copyReader = new CopyReader();
		
		copyMaker.start();
		copyReader.start();
		
		try {
			copyMaker.join();
			copyReader.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}