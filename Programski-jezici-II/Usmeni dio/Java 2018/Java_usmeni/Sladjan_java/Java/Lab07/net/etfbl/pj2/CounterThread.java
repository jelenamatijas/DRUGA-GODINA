package net.etfbl.pj2;

import java.util.Scanner;
import java.io.*;


public class CounterThread extends Thread
{
  private String name;
  private int count;
  
  
 public CounterThread(String name, int count) {
  super();
  setName(name);
  this.name = name;
  this.count = count;
 }
  
  public void run()
  {
    int suma = 0;
    for(int i=0;i<this.count;i++) suma+=i;
    
    System.out.println("Nit " + getName() + " Suma: " + suma);
    //Console cons = System.console();
    //cons.printf("Nit %s",this.name);
  }
  
  public static void main (String[] args)
  {
    CounterThread t = new CounterThread("1",100);
    t.start();
    
  }
  
}
    