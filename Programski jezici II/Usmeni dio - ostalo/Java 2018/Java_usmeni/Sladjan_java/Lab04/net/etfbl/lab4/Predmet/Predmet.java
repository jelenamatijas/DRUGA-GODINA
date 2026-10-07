package net.etfbl.lab4.Predmet;

import net.etfbl.lab4.izuzetak.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;


 public abstract  class Predmet
{
  protected static int ID = 0;
  protected double ST = 0;
  protected int identifikator;
  
  public Predmet (double tezina)
  {
   this.ST = tezina;
   this.ID++;
  }
  
  
  public abstract void print ();
  public abstract void read () throws PredmetException;
  public abstract double zapremina ();
  
  public double tezina ()
  {
    return ST * zapremina();
  }
  
  public int poredjenje (Predmet p)
  {
    if (this.zapremina() > p.zapremina()) return 1;
    else if (this.zapremina() < p.zapremina()) return 2;
    else return 0;
  }
  
  
}