package net.eftbl.textual_game_watcher.producer_consumer;

import net.etfbl.textual_game_watcher.object.*;

public class Komentator extends Thread {
 Poruke informacije;

 public Komentator(Poruke informacije) {
  this.informacije = informacije;
 }

 public void run() {
  long pocetak = System.currentTimeMillis();
  while (System.currentTimeMillis() - pocetak <= 120000) { // dvije minute
   try {
    // generisi poruku
    informacije.dodajPoruku();
    // spavaj 10 sekundi
    sleep(10000);
   } catch (InterruptedException e) {
    System.out.println("InterruptedException");
   }
  }
 }
}
