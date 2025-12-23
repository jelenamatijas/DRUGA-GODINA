package net.etfbl.lab4.predmet;

import net.etfbl.lab4.izuzetak.*; 
 
public abstract class Predmet {
  protected double specificnaTezina;
  protected static int id;
  protected int identifikator;
  
  public Predmet(double specificnaTezina){
    this.specificnaTezina = specificnaTezina;
    id++;
  }
  
  public abstract void print();
  public abstract void read() throws PredmetException;
  public abstract double zapremina();
  
  
  public double tezina(){
    return specificnaTezina * zapremina();
  } 
  
  public static int poredjenje(Predmet p1, Predmet p2) {
    if(p1.zapremina()>p2.zapremina())
      return 1; //prvi je veci -> rezultat 1
    else if (p1.zapremina()<p2.zapremina())
      return -1; //drugi je veci -> rezultat -1
    else
      return 0; //ako su jednaki
  }
  
  /*poredjenje redefinisanjem metode equals klase Object*/
 public boolean equals(Predmet p){
   return this.zapremina()==p.zapremina();
 }

 /*ispis redefinisanjem toString metode*/
 public String toString(){
   return "Specificna tezina: "+specificnaTezina+"\nIdentifikator: "+identifikator
     +"\nZapremina: "+zapremina()+"\nTezina: "+tezina();
 }
  
  
}
