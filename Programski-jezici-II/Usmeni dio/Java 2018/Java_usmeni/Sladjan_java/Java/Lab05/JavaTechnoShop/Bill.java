import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.lang.Math;
import java.util.Calendar;
import java.io.*;


public class Bill implements Serializable
{
//  private Product[] products;
  private int number =0;
  
  public Bill()
  {
    
   // products = null;
    this.number =0;
    
  }
//  
//  public Bill (Product p)
//  {
//    ++number;
//    Product [] temp = new Product [number -1];
//    for (int i = 0;i<number - 1; i++) temp[i] = products[i];
//    this.products = new Product[number];
//    this.products[number] = p;
//  }
//  
//  public void add (Product p)
//  {
//    if (this.products!= null) 
//    {
//      Product [] temp = new Product [number];
//      for (int i = 0;i<number; i++) temp[i] = products[i];
//     ++this.number;
//      this.products = new Product[number];
//      for (int i = 0;i<number-1;i++) products[i] = temp[i];
//      this.products[number-1] = p;
//    }
//    else 
//    {
//      ++this.number;
//      this.products = new Product [number];
//      
//      
//      this.products[number-1] = p;
//    }
//  }
//  
//  public void sub (Product p)
//  {
//    int counter = 0;
//    
//    for (int i=0;i<this.number;i++)
//    {
//      if (this.products[i].equals(p)) break;
//      else counter++;
//    }
//    ++counter;
//   
//    
//    Product[] temp = new Product[number-1];
//    
//    for (int i=0;i<counter;i++) temp[i] = products[i];
//    for (int i=counter+1;i<number;i++)temp[i-1] = products[i];
//    
//    this.products = new Product[number-1];
//    
//    --this.number;
//    
//    for (int i=0;i<number;i++) products[i] = temp[i];
//  }
//    
//  
//  public double payOff()
//  {
//    double sum = 0;
//    for (int i=0;i<number;i++) sum+=products[i].getPrice();
//    return sum;
//  }
//  
//  public String toString()
//  {
//    String s = new String();
//    
//    for (int i=0;i<number;i++) s+= products[i].toString();
//    
//    return "\n==============================\nRACUN \nDatum      " + Calendar.getInstance().getTime() + "\n\n" + s + "\n\nIznos za placanje: " + this.payOff() + "\n==============================\n\n";
//  }
//  
}
//    