package net.eftbl.textual_game_watcher.producer_consumer;

import net.etfbl.textual_game_watcher.object.Messages;

public class Informer extends Thread {
	Messages informacije;

	public Informer(Messages informacije) {
		this.informacije = informacije;
	}

	public void run() {
		long pocetak = System.currentTimeMillis();
		while (System.currentTimeMillis() - pocetak <= 120000) { // dvije minute
			try {
				// generisi poruku
				informacije.setMessage();
				// spavaj 10 sekundi
				sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}
		}
	}
}
