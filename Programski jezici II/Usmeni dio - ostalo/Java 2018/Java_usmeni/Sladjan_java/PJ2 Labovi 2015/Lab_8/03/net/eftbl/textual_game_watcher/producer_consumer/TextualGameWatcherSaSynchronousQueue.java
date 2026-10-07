package net.eftbl.textual_game_watcher.producer_consumer;

import net.etfbl.textual_game_watcher.object.Poruke;

public class TextualGameWatcherSaSynchronousQueue {

 /**
  * @param args
  */
 public static void main(String[] args) {
  //kreiramo objekat informacija koje ce dijeliti producer i konsumer
  Poruke informacije = new Poruke();
  //kreiramo nit producer-a (Komentator) i pokrenemo je
  Komentator inf=new Komentator(informacije);
  inf.start();
  //zaustavimo na 12 sekundi, da bi stigao da doda bar jedan rezultat
  try {
   Thread.sleep(12000);
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  // kreiramo nit consumer-a (Gledalac) i pokrenemo je
  Gledalac watch=new Gledalac(informacije);
  watch.start();
 }

}
