import java.util.Scanner;
import java.lang.String;
import java.io.*;


public class Monitor extends Product implements Serializable
{
  private String model;
  private String[] configuration;
  
  public Monitor(){};
  
  public Monitor(String s,double d,int k,String[] h,String[] m,String q)
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
    
    this.model = q;
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
    u = "Produkt MONITOR\n";
    String s = new String();
    s = super.toString();
    return u + s + "\nKONFIGURACIJA" + "\nVelicina ekrana : " + configuration[0] + "\nHz rate: " + configuration[1] + "\nRezolucija: " + configuration[2] + "\nTip: " + configuration[3] + "\n";
  
  }
  
  @Override
  public boolean equals(Product monitor)
  {
    if (this.code == monitor.getCode()) return true;
     return false;
        }
  
  public String getName()
  {
    return super.getName();
  }
  
  public double getPrice()
  {
    return super.getPrice();
  }
}