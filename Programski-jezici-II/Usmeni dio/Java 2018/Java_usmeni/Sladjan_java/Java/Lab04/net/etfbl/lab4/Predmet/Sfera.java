package net.etfbl.lab4.Predmet;

import net.etfbl.lab4.izuzetak.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;


public class Sfera extends Predmet
{
  protected double r;
  
  
  public Sfera (double specificnaTezina)
  {
    super(specificnaTezina);
    this.identifikator = Predmet.ID;
  }
 
  
  @Override
  public void print ()
  {
    System.out.println ("Predmet SFERA");
    System.out.println("Broj predmeta "+identifikator);
    System.out.println ("Specificna tezina je "+ST);
    System.out.println ("Zapremina je "+this.zapremina());
  }
  
  @Override
  public double zapremina ()
  {
    double temp;
    temp = (4.0/3.0)*Math.PI*Math.pow(r,3.0);
    System.out.println("Temp "+temp);
    return  temp;
  }
  
  @Override
  public void read () throws PredmetException
  {
    double temp = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println ("Unesite podatke za predmet SFERA.");
    System.out.println ("Identifikacioni broj je "+this.identifikator);
    System.out.println ("Unesite poluprecnik SFERE: ");
    temp = scan.nextDouble();
    if (temp <1 || temp>100) throw new PredmetException(); 
    else this.r=temp;
  }
  
}