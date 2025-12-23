import java.io.*;

public class Software extends Product implements Serializable
{
  private String description;
  
  public Software () {};
  
  public Software (String s,double d,int k,String[] h,String q)
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
    
    this.description = q;
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
    u = "Produkt SOFTVER\n";
    String s = new String();
    s = super.toString();
    return u + s + "\nOPIS: \n" + description + "\n";
  
  }
  
  @Override
  public boolean equals(Product software)
  {
    if (this.code == software.getCode()) return true;
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


