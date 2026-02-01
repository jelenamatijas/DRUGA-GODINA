package net.etfbl.pj2;

import java.util.Scanner;
import java.util.Random;


public class CounterMain
{
  public static void main (String[] args)
  {
    CounterThread[] niti;
    int brojNiti = 0;
    
    Scanner scan = new Scanner(System.in);
    Random ran = new Random();
    
    brojNiti = scan.nextInt();
    
    if (brojNiti != 0)
    {
      niti = new CounterThread[brojNiti];
      for(int i=0;i<brojNiti;i++)
      {
        int randomBroj = ran.nextInt(5001) + 5000;
        Integer k =i;
        niti[i] = new CounterThread(k.toString(),randomBroj);
        System.out.println("Nit_"+i);
        niti[i].start();
      }
    }
  }
}
        
  
  