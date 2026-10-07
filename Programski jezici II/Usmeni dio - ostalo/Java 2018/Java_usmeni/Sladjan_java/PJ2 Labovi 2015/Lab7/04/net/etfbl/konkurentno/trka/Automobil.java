package net.etfbl.konkurentno.trka;

import java.util.Random;

public class Automobil extends Thread {
  public String tipAutomobila;
  public int id;
  public Object[][] staza;
  public int ostaloDoKraja=0;
  public int red;
  public static boolean kraj=false;
  
  public Automobil(Object[][] staza, int red, String tipAutomobila) {
    this.staza = staza;
    this.red = red;
    //podesavamo slucajan id i tip automobila
    Random generatorSlucajnihBrojeva=new Random();
    this.id=generatorSlucajnihBrojeva.nextInt(100);
    this.tipAutomobila=tipAutomobila;
  }
  
  // redefinisemo run metodu
  public void run(){
    
    //krecemo se kroz jedan red
    for(int i=0; i<15; i++){
      if(kraj){
        System.out.println("Automobil nije zavrsio trku prvi... Podaci o automobilu: "+this);
        break;
      }else{
        //provjeravamo da li smo naisli na "STOP"
        if("STOP".equals(staza[red][i])){
          //podesimo koliko mu je ostalo do kraja
          ostaloDoKraja=14-i;
          //ispisemo podatke
          System.out.println("Automobil je zaustavljen na 5 sekundi. Podaci o automobilu: "+this);
          //uspavljujemo ga na 5 sekundi
          try {
            sleep(5000);
          } catch (InterruptedException e) {
            System.out.println("Automobil nije zavrsio trku prvi... Podaci o automobilu: "+this);
          }
        }else{
          //prebacimo automobil na sljedecu poziciju
          staza[red][i]=this;
          //podesimo koliko mu je ostalo do kraja
          ostaloDoKraja=14-i;
          //ispisemo podatke
          System.out.println("Automobil se krece... Podaci o automobilu: "+this);
          //uspavljujemo nit na 2 sekunde
          try {
            sleep(2000);
          } catch (InterruptedException e) {
            System.out.println("Automobil nije zavrsio trku prvi... Podaci o automobilu: "+this);
          }
        }
        if(i==14){ 
          kraj=true;
          System.out.println("Pobijedio je automobil u traci "+red+" Podaci o automobilu: "+ this);
          interrupt();
        }
      }
    }
  }
  
  //redefinisemo toString metodu, radi lakseg ispisa
  @Override
  public String toString() {
    return "Automobil [tipAutomobila=" + tipAutomobila + ", id=" + id
      + ", ostaloDoKraja="
      + ostaloDoKraja +"]";
  }
  
  
  
}
