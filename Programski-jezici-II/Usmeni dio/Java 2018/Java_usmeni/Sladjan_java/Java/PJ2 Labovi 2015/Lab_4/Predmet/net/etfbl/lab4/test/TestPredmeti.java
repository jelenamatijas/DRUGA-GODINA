package net.etfbl.lab4.test;

import net.etfbl.lab4.predmet.*;
import net.etfbl.lab4.izuzetak.*;
import java.util.Arrays;

public class TestPredmeti {
  
  public static void main(String [] args){  
    // kreiramo dva objekta tipa sfera sa proizvoljnom specificnom tezinom
    Sfera[] sfere = { new Sfera(15), new Sfera(78) };
    // kreiramo dva objekta tipa kvadar sa proizvoljnom specificnom tezinom
    Kvadar[] kvadri = { new Kvadar(45), new Kvadar(32) };
    // pozivamo metode za sfere
    System.out.println("Pozivi metoda za sfere");
    for (int i = 0; i < sfere.length; i++) {
      try{
        System.out.println("Sfera redni broj " + i);
        sfere[i].read();
        sfere[i].print();
        System.out.println("Zapremina " + sfere[i].zapremina());
        System.out.println("Tezina " + sfere[i].tezina());
      }catch (PredmetException ex){
        ex.printStackTrace();
      }
      System.out.println("********************************");
    }
    
    System.out.println("Pozivi metoda za kvadre");
    for (int i = 0; i < kvadri.length; i++) {
      System.out.println("Kvadar redni broj " + i);
      kvadri[i].read();
      kvadri[i].print();
      System.out.println("Zapremina " + kvadri[i].zapremina());
      System.out.println("Tezina " + kvadri[i].tezina());
      System.out.println("********************************");
    }
    
    System.out.println("Poredjenje sfera");
    int rez = Predmet.poredjenje(sfere[0], sfere[1]);
    if (rez == 1)
      System.out.println("Sfera 1 je veca");
    else if (rez == -1)
      System.out.println("Sfera 2 je veca");
    else
      System.out.println("Sfere su jednake");
    
    System.out.println("Poredjenje kvadara");
    rez = Predmet.poredjenje(kvadri[0], kvadri[1]);
    if (rez == 1)
      System.out.println("Kvadar 1 je veci");
    else if (rez == -1)
      System.out.println("Kvadar 2 je veci");
    else
      System.out.println("Kvadri su jednaki");
    
    //ispis objekata - implicitni poziv toString metode
    System.out.println("Ispis sfera "+Arrays.toString(sfere));
    System.out.println("Ispis kvadara "+Arrays.toString(kvadri));
    
    //poredjenje objekata koristenjem equals metode
    System.out.println("Jednakost sfera "+sfere[0].equals(sfere[1]));
  }
  
}
