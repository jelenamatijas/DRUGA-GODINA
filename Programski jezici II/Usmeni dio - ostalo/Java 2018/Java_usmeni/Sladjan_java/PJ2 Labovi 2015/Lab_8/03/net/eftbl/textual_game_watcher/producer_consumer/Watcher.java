package net.eftbl.textual_game_watcher.producer_consumer;

import net.etfbl.textual_game_watcher.object.Message;
import net.etfbl.textual_game_watcher.object.Messages;

public class Watcher extends Thread{
 Messages informacije;
 
 public Watcher(Messages informacije){
  this.informacije=informacije;
 }
 
 public void run(){
  long pocetak=System.currentTimeMillis();
  while(System.currentTimeMillis()-pocetak<=120000){ //dvije minute
  try{
   //ocitaj poruku
   Message poruka = informacije.getMessage();
   //prikazi je na komandnoj liniji
   System.out.println(poruka);
   //sacekaj rezultate
   sleep(15000);
  }catch (InterruptedException e) {
   System.out.println("InterruptedException");
  }
  }
 }
}
