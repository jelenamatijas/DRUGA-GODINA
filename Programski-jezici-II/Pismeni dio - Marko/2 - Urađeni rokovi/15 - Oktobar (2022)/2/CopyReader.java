public class CopyReader extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 0; i < 10; i++) {
			synchronized (Main.lock) {
				try {
					Main.lock.wait();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
			System.out.println((i + 1) + ". CITANJE:\n");
			String sadrzajDokumenta = Main.dokument.sadrzaj;
			System.out.println(sadrzajDokumenta + "\n");
			
			try {
				sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
			synchronized (Main.lock) {
				Main.lock.notify();
			}
		}
	}
}