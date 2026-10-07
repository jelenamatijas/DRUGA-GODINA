package net.etfbl.lab4.predmet;

import java.util.Scanner;
import net.etfbl.lab4.izuzetak.PredmetException;

public class Sfera extends Predmet {
  // atributi karakteristicni za sferu
 protected int poluprecnik;

 public Sfera(double specificnaTezina) {
   super(specificnaTezina);
   identifikator=Predmet.id;
 }

 @Override
 public void print() {
   System.out.println("Podaci za sferu:");
   System.out.println("Identifikator: "+identifikator);
   System.out.println("Specificna tezina: " + specificnaTezina);
   System.out.println("Poluprecnik: " + poluprecnik);
 }

 @Override
 public void read() throws PredmetException{
   Scanner skener = new Scanner(System.in);
   System.out.println("Unesite poluprecnik");
   poluprecnik = skener.nextInt();
   if(poluprecnik <1 || poluprecnik > 100){
     throw new PredmetException("Poluprecnik treba da ima vrijednost u opsegu od 1 do 100");
   }
   
 }

 @Override
 public double zapremina() {
   return 4/3*(Math.PI*Math.pow(poluprecnik, 3));
 }
 
 /*ispis redefinisanjem toString metode*/
 public String toString(){
   return super.toString()+"\nPoluprecnik: " + poluprecnik;
 }
  
}
