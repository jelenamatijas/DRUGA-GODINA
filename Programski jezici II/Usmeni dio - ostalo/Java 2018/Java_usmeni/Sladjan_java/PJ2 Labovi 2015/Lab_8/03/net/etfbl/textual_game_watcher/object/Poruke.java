package net.etfbl.textual_game_watcher.object;
import java.util.concurrent.SynchronousQueue;
import java.util.Random;
public class Poruke{
  public SynchronousQueue<Message> poruke=new SynchronousQueue<Message>();
  
  public void dodajPoruku(){
     //pravimo generator slucajnih brojeva
  Random generator = new Random();
  // pravimo novu poruku
  Message poruka = new Message("Igrac" + generator.nextInt(1000), "Tim"
    + generator.nextInt(1));
  //ocitavamo trenutak vremena i slucajno generisemo koliko je poena dobio
  poruka.trenutakVremena = System.currentTimeMillis();
  poruka.dobioPoena = generator.nextInt(2) + 1;
  //azuriramo rezultat
  Message.rezultat += poruka.dobioPoena;
  System.out.println("Trenutni rezultat: "+Message.rezultat+" Broj poruka: "+(poruke.size()+1));
  //poruku smjestamo u queue
  poruke.offer(poruka);
  }
  
  public Message ocitajPoruku(){
    Message poruka=null;
    try{
    poruka = poruke.take();
    }catch(InterruptedException exc){
      System.out.println("izuzetak");
    }
    return poruka;
  }
}