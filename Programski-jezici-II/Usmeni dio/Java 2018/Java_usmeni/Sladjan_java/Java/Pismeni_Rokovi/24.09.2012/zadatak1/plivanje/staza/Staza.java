package plivanje.staza;

import plivanje.plivac.*;
import plivanje.bazen.*;
import java.lang.String;
import java.lang.Math;
import java.util.ArrayList;


public class Staza extends Thread
{
  private int lenght;
  private int row;
  private int collumn;
  public static int k=0;
  public static String controlCommand;
  
  
  public Staza(int lenght)
  {
    this.lenght = lenght;
    synchronized(Bazen.staze)
    {
      Bazen.staze.add(this);
    }
    this.collumn = k;
    
    k++;
    
    
    start();
  }
  
  public void run()
  {
    System.out.println("Staza pokrenuta...");
    double swimmerSpeed = 0;
    this.row = 0;
    
    Plivac p = new Plivac(5.6,0);
   do
   {
     System.out.println("DO STAZA");
     int position = 0;
     for(int i=0;i<100;i++)
     {
       try
       {
       sleep(100);
       }
       catch(Exception ex)
       {
         ex.printStackTrace();
       }
       swimmerSpeed += p.currentSpeed();
     
     //swimmerSpeed /= 10.0;
    
     position += swimmerSpeed;
     
     int tempPosition = (int)position;
     if (tempPosition >= 20) 
     {
       synchronized(Bazen.staze)
       {
       for(int k=0;k<Bazen.staze.size();k++)
       {
         Staza sta = Bazen.staze.get(k);
         sta.interrupt();
       }
       }
       this.interrupt();
     }
     
     p.setWay(position);
     synchronized(Bazen.bazen)
     {
       for(int j=0;j<tempPosition && tempPosition<20;j++)
       {
         
         String ut = new String();
         ut = "*";
         try
         {
         if(j == (tempPosition-1)) Bazen.bazen[j][this.collumn] = ut;
          else Bazen.bazen[j][this.collumn] = null;
         Bazen.bazen.notifyAll();
         }
         catch(ArrayIndexOutOfBoundsException ex)
         {
           System.out.println(j + " " + this.collumn);
         }
        
       }
     }
     }
     
   }
   while(!"STOP".equalsIgnoreCase(controlCommand) && !this.isInterrupted());
   
   
  }
    
  
}