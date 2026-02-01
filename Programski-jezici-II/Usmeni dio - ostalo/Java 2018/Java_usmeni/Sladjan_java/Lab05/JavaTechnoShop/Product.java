import java.util.Scanner;
import java.lang.String;


abstract public class Product 
{
  protected String name;
  protected double price;
  protected int code;
  protected String[] manufacturer;
  
  
  abstract public boolean equals(Product p);
  
   public String toString()
   {
     return "Naziv proizvoda: " + name + "\nSifra proizvoda: " + code + "\nCijena proizvoda: " + price + "\nNaziv proizvodjaca: " + manufacturer[0] 
                + "\nDrzava: " + manufacturer[1] + "\nEmail adresa: " + manufacturer[2];
   }
   
   public int getCode()
   {
     return code;
   }
     
   public String getName()
   {
     return name;
   }
   
   public double getPrice()
   {
     return price;
   }
 
}