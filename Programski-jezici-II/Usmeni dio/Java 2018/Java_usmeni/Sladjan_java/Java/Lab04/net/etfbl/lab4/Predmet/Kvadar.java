package net.etfbl.lab4.Predmet;

import net.etfbl.lab4.izuzetak.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;

public class Kvadar extends Predmet
{
  protected double a,b,c;
  
  public Kvadar (double specificnaTezina)
  {
    super (specificnaTezina);
    this.identifikator = Predmet.ID;
  }
  
  @Override
  public void print()
 {
    System.out.println ("Predmet KVADAR");
    System.out.println("Broj predmeta "+identifikator);
    
    System.out.println ("Specificna tezina je "+ST);
    System.out.println ("Zapremina je "+this.zapremina());
  }
    
  @Override
  public double zapremina ()
  {
    return  a*b*c;
  }
  
  @Override
  public void read () throws PredmetException
  {
    double temp = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println ("Unesite podatke za predmet SFERA.");
    System.out.println ("Identifikacioni broj je "+this.identifikator);
    System.out.println ("Unesite ivicu KVADRA a: ");
    
    temp = scan.nextDouble();
    if (temp<1 || temp>100) throw new PredmetException();
    else this.a = temp;
    System.out.println ("Unesite ivicu KVADRA b: ");
    
    temp = scan.nextDouble();
    if (temp<1 || temp>100) throw new PredmetException();
    else this.b = temp;
    System.out.println ("Unesite ivicu KVADRA c: ");
    
    temp = scan.nextDouble();
    if (temp<1 || temp>100) throw new PredmetException();
    else this.c = temp;
    
  }
  
}