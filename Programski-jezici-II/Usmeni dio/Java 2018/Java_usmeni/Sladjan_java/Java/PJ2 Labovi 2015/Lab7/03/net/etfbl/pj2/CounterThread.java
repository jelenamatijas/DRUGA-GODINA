package net.etfbl.pj2;

public class CounterThread extends Thread{

 private String name;
 private int count;
 
 public CounterThread(String name, int count) {
  super();
  this.name = name;
  this.count = count;
 }

 @Override
 public void run() {
  long sum = 0;
  for(int i=0;i<count;i++){
   sum += i;
  }
  System.out.println("Nit: " + name + " Suma: " + sum);
 } 
 
}
