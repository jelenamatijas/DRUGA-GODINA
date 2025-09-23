public class CopyMaker extends Thread {
	
	String posljednjiSadrzaj = "";
	
	@Override
	public void run() {
		
		for (int i = 0; i < 10; i++) {
			System.out.println((i + 1) + ". MODIFIKACIJA:\n");
			this.posljednjiSadrzaj = Main.dokument.sadrzaj;
			for (char c : posljednjiSadrzaj.toCharArray()) {
				if (c == 'o' || c == 'O') {
					Main.brojSlovaO++;
				} else if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
					Main.brojevaIzbaceno++;
				}
			}
			posljednjiSadrzaj = posljednjiSadrzaj.replaceAll("[oO0123456789]", "");
			Main.dokument.sadrzaj += posljednjiSadrzaj;
			
			try {
				sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
			synchronized (Main.lock) {
				Main.lock.notify();
			}
			
			synchronized (Main.lock) {
				try {
					Main.lock.wait();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}