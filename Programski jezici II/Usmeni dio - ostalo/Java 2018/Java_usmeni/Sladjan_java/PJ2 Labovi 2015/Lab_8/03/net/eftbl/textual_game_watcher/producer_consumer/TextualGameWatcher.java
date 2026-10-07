package net.eftbl.textual_game_watcher.producer_consumer;

import net.etfbl.textual_game_watcher.object.Messages;

public class TextualGameWatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//kreiramo objekat informacija koje ce dijeliti producer i konsumer
		Messages informacije = new Messages();
		//kreiramo nit producer-a (Informer) i pokrenemo je
		Informer inf=new Informer(informacije);
		inf.start();
		//zaustavimo na 12 sekundi, da bi stigao da doda bar jedan rezultat
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// kreiramo nit consumer-a (Watcher) i pokrenemo je
		Watcher watch=new Watcher(informacije);
		watch.start();
	}

}
