import java.util.*;

public class Main {
	
	public static Random random = new Random();
	
	public static boolean cekanje = false;
	public static Object lockObject = new Object();
	
	public static void main(String[] args) {
		ArrayList<Robot> roboti = new ArrayList<>();
		
		AlarmWatcher alarmWatcher = new AlarmWatcher();
		alarmWatcher.start();
		
		// Montazni
		for (int i = 0; i < 10; i++) { // new Senzor("Opticki") new Senzor("Ultrazvucni")
			if (i % 2 == 0) {
				roboti.add(new Robot("Montazni", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Montazni", new Senzor("Ultrazvucni")));
		}
		
		// Cistacki
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				roboti.add(new Robot("Cistacki", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Cistacki", new Senzor("Ultrazvucni")));
		}
		
		// Istrazivacki
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				roboti.add(new Robot("Istrazivacki", new Senzor("Opticki")));
			}
			roboti.add(new Robot("Istrazivacki", new Senzor("Ultrazvucni")));
		}
		
		for (Robot robot : roboti) {
			robot.start();
		}
		
		try {
			for (Robot robot : roboti) {
				robot.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		alarmWatcher.radi = false;
	}
}