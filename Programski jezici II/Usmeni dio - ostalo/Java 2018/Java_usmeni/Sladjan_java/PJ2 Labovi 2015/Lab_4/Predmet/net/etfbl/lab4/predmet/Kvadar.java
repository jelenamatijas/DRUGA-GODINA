package net.etfbl.lab4.predmet;

import java.util.Scanner;
import net.etfbl.lab4.izuzetak.*;

public class Kvadar extends Predmet{
  
  public int a, b, c;
  
  public Kvadar(double specificnaTezina){
    super(specificnaTezina);
    identifikator = Predmet.id;
  }
  
  @Override
  public void print(){
    System.out.println("Podaci za kvadar:");
    System.out.println("Identifikator: "+identifikator);
    System.out.println("Specificna tezina: " + specificnaTezina);
    System.out.println("a: " + a);
    System.out.println("b: " + b);
    System.out.println("c: " + c);
  }
  
  @Override
  public void read() {    
    System.out.println("Unesite podatke za kvadar:");
    Scanner scan = new Scanner(System.in);
    
    try{
      System.out.print("a = ");
      a = scan.nextInt();
      if(a < 1 || a > 100){
        throw new PredmetException();
      }
      System.out.print("b = ");
      b = scan.nextInt();
      if(b < 1 || b > 100){
        throw new PredmetException();
      }
      System.out.print("c = ");
      c = scan.nextInt();
      if(c < 1 || c > 100){
        throw new PredmetException();
      }
    }catch(PredmetException ex){
      System.out.println(ex);
    }
    
  }
  
  @Override
  public double zapremina(){
    return a*b*c;
  }
  
  @Override
  public String toString(){
    return super.toString() + "\na = " + a + "\nb = " + b + "\nc = " + c;
  }
  
}
