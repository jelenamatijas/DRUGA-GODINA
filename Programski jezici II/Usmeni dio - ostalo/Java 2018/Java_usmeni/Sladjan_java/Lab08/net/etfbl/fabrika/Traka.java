package net.etfbl.fabrika;

import net.etfbl.fabrika.Proizvod;

import java.util.Scanner;
import java.util.Random;
import java.lang.String;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class Traka extends Thread
{
  private String jedinstveniBroj;
  private int brzina;
  
  public Traka(String broj)
  {
    super();
    this.jedinstveniBroj = broj;
    
    Random ran = new Random();
    
    this.brzina = ran.nextInt(2000+1);
  }
  
  public void run()
  {
    System.out.println("Traka broj " + this.jedinstveniBroj + " je krenula sa radom. Brzina trake je " + this.brzina);
    boolean kvar = false;
    
    for (int i=0;i<10;i++)
    {
      Random ran = new Random();
    int temp = ran.nextInt(100+1);
    boolean zastoj = false;
    if (temp>=0 && temp<10) zastoj = true;
    
    
    temp = ran.nextInt(100+1);
    if (temp>=0 && temp<5) kvar = true;
    
    if (kvar)
    {
     System.out.println("Traka broj " + this.jedinstveniBroj + " je u KVARU!!!");
     break;
    }
    else
    {
      try
      {
        sleep(this.brzina);
        if(zastoj) 
        {
          System.out.println("Traka broj " + this.jedinstveniBroj +" ZASTOJ 5s");
          sleep(5000);
        }
      }
      catch (InterruptedException ex)
      {
        System.out.println("Traka broj " + this.jedinstveniBroj + " je prekinuta INTERRUPTED");
      }
      
      String sn = new String();
      sn = this.jedinstveniBroj + new Date().getTime();
      Proizvod p = new Proizvod ("KAFA",sn);
      File file = new File("proizvodi");
      
      System.out.println("Traka " + this.jedinstveniBroj + " je proizvela proizvod broj " + i);
      
      if(file.exists() && file.isDirectory())
      {
        try
        {
        FileOutputStream pisac = new FileOutputStream("D:\\Java\\Lab08\\proizvodi\\"+ this.jedinstveniBroj +"_" + i);
        ObjectOutputStream out = new ObjectOutputStream(pisac);
        out.writeObject(p);
        pisac.close();
        out.close();
        }
        catch(Exception ex)
        {
          System.out.println("Problem sa upisom u dir");
          ex.printStackTrace();
        }
      }
      else
      {
        file.mkdir();
       try
        {
        FileOutputStream pisac = new FileOutputStream("\\proizvodi"+ this.jedinstveniBroj +"_" + i);
        ObjectOutputStream out = new ObjectOutputStream(pisac);
        out.writeObject(p);
        }
        catch(Exception ex)
        {
          System.out.println("Problem sa upisom u dir");
          ex.printStackTrace();
        }
      }
      
      
    }
    
    if (i == 9) System.out.println("Traka broj: " + this.jedinstveniBroj + " je zavrsila sa radom");
    }
    
    if(kvar) interrupt();
  }
  
  
  public static void main (String[] args)
  {
    Traka t1 = new Traka("1");
    
    t1.start();
  }
  
  
  
}