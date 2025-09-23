import java.util.*;

public class UzbunaNit extends Thread {
	
	@Override
	public void run() {
		
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		
		while (!this.isInterrupted()) {
			
			
			unos = scanner.nextLine();
			
			if ("ALARM".equals(unos)) {
				Main.stop = true;
				unos = "";
			}
			
			if ("ALARM_END".equals(unos)) {
				Main.stop = false;
				synchronized (Main.stopObject) {
					Main.stopObject.notifyAll();
				}
				unos = "";
			}
		}
	}
}