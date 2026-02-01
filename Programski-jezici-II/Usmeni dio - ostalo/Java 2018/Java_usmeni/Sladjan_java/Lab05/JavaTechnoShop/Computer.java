import java.util.Scanner;
import java.lang.String;
import java.io.*;

public class Computer extends Product implements Serializable
{
  private String[] configuration;
  
  public Computer(){};
  
  public Computer(String s,double d,int k,String[] h,String[] m)
  {
    this.name = s;
    this.price = d;
    this.code = k;
    
    this.manufacturer = new String [3];
    for (int i=0;i<3;i++)
    {
      this.manufacturer[i] = new String();
      this.manufacturer[i] = h[i];
    }
    
    this.configuration = new String [4];
    for(int i=0;i<4;i++)
    {
      this.configuration[i] = new String();
      this.configuration[i] = m [i];
    }
  }
  
  @Override
  public int getCode ()
  {
    return this.code;
  }
  
  @Override
  public String toString()
  {
    String u = new String();
    u = "Produkt PC\n";
    String s = new String();
    s = super.toString();
    return u + s + "\nKONFIGURACIJA" + "\nProcesor : " + configuration[0] + "\nRAM: " + configuration[1] + "\nGraficka karta: " + configuration[2] + "\nHDD: " + configuration[3] + "\n";
  
  }
  
  @Override
  public boolean equals(Product PC)
  {
    if (this.code == PC.getCode()) return true;
     return false;
        }
  
  public String getName ()
  {
    return super.getName();
  }
  
  public double getPrice()
  {
    return super.getPrice();
  }
}