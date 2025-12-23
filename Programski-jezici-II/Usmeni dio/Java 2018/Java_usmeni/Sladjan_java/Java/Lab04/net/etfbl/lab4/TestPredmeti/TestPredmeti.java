package net.etfbl.lab4.TestPredmeti;

import net.etfbl.lab4.Predmet.*;
import net.etfbl.lab4.izuzetak.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.String;

public class TestPredmeti
{
  public static void main (String args[])
  {
    Kvadar k1 = new Kvadar (10);
    Kvadar k2 = new Kvadar (20);
    
    Sfera s1 = new Sfera (30);
    Sfera s2 = new Sfera(40);
    
    try
    {
      k1.read();
      k2.read();
    
      s1.read();
      s2.read();
    }catch (PredmetException Ex)
    {
    }
    
     k1.print();
    k2.print();
    
    s1.print();
    s2.print();
    System.out.println("Zapremina s1 je" + s1.zapremina());
    System.out.println("PI"+Math.PI);
  }
}