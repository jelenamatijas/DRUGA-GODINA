package net.etfbl.textual_game_watcher.object;

import java.util.ArrayList;
import java.util.Random;

public class Messages {
 
 // polja
 private ArrayList<Message> poruke = new ArrayList<Message>();
 private boolean free=true; //da li je lista poruka slobodna

 public synchronized void setMessage() {
   if(!free){ //ako su poruke zauzete, postavljamo u stanje cekanja
     try {
       wait();
   } catch (Exception ex) {
     System.out.println("Izuzetak!");
   }
   }else{
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
  //poruku smjestamo u kolekciju
  poruke.add(poruka);
  free=false; //kad jednom doda poruku, postavljamo flag na false
  //da ne smije dodati novu, dok watcher ne ocita trenutnu
   }
   
 }

 public synchronized Message getMessage() {
  // ocitavamo uvijek posljednju poruku
   Message poruka=poruke.get(poruke.size() - 1);
   free = true; //kad ocita poruku, postaljamo free na true i obavjestavamo sa notify da informer moze dodavati dalje
   notify();
  return poruka;
 }
 
 //ako ne koristimo wait() i notify() moze se desiti da informer doda vise poruka, a da onda watcher ocita samo posljednju dodatu
}
