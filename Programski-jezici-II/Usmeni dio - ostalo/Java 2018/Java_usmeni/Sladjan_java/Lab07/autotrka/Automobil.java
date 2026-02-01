package Lab07.autotrka;

import java.util.Scanner;
import java.util.Random;
import java.lang.String;


public class Automobil extends Thread
{
  private String tip;
  private String serijskiBroj;
  private Object[][] staza;
  private int trenutnaPozicija;
  private int red;
  public static boolean kraj = false;
  
  public Automobil(Object[][] track,int r,String t,String s)
  {
    super();
    setName(t);
    this.tip = t;
    this.serijskiBroj = s;
    this.staza = track;
    this.red = r;
  }
  
  public void run()
  {
    trenutnaPozicija = 0;
    
    for(int i=0;i<15;i++)
    {
      if(kraj)
      {
        System.out.println("Automobil " + getName() + " serijskog broja " + this.serijskiBroj + " NIJE zavrsio PRVI");
        break;
      }
      else
      {
        if("STOP".equals(this.staza[this.red][i]))
        {
          this.trenutnaPozicija = i;
          System.out.println("STOP!!! " + this.toString() + " Ostalo do kraja " + (15-i));
          
          try
          {
            sleep(5000);
          }
          catch(InterruptedException ex)
          {
            System.out.println("Automobil " + getName() + " serijskog broja " + this.serijskiBroj + " NIJE zavrsio PRVI");
          }
        }
        else
        {
          this.trenutnaPozicija = i;
          System.out.println(this.toString());
          try
          {
            sleep(2000);
          }
          catch(InterruptedException ex)
          {
            System.out.println("Automobil " + getName() + " serijskog broja " + this.serijskiBroj + " NIJE zavrsio PRVI");
          }
        }
      }
      
      if (i == 14)
      {
        System.out.println("Pobijedio je " + this.toString());
        kraj = true;
        interrupt();
      }
    }
  }
                           
  

  @Override
  public String toString()
  {
    return "Auto " + this.tip + " serijski broj " + this.serijskiBroj + " trenutna pozicija " + this.trenutnaPozicija + " ";
  }
}