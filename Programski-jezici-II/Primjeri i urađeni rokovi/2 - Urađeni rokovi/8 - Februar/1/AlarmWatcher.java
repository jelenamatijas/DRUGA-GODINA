import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.*;

public class AlarmWatcher extends Thread {
	
	boolean radi = false;
	
	public AlarmWatcher() {
		super();
	}
	
	@Override
	public void run() {
		radi = true;
		Path fajlPutanja = Paths.get("alarm.txt");
		
		String posljednjiSadrzaj = "";
		
		while (radi) {
			try {
				String trenutniSadrzaj = Files.readString(fajlPutanja);
				
				if (!trenutniSadrzaj.equals(posljednjiSadrzaj)) {
					posljednjiSadrzaj = trenutniSadrzaj;
					
					if (posljednjiSadrzaj.trim().equals("1")) {
						System.out.println("Alarm aktiviran!");
						Main.cekanje = true;
					}
					else if (posljednjiSadrzaj.trim().equals("0")) {
						if (Main.cekanje == true) {
							synchronized (Main.lockObject) {
								Main.cekanje = false;
								Main.lockObject.notifyAll();
							}
						}
					}
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom rada Alarma");
			}
		}
	}
}