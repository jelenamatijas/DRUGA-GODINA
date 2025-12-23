import java.util.Scanner;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;


public class Pretraga
{
  public static void grad (ArrayList <Partner> partneri)
  {
    System.out.println("Unesite grad za pretragu: ");
    Scanner scan = new Scanner(System.in);
    String s = new String();
    s = scan.nextLine();
    
    for (int i=0;i<partneri.size();i++)
    {
      if ((partneri.get(i).getMaticniGrad()).startsWith(s) || (partneri.get(i).getMaticniGrad()).equalsIgnoreCase(s))
      System.out.println(partneri.get(i).toString());
    }
  }
  
   public static void naziv (ArrayList <Partner> partneri)
  {
    System.out.println("Unesite naziv za pretragu: ");
    Scanner scan = new Scanner(System.in);
    String s = new String();
    s = scan.nextLine();
    
    for (int i=0;i<partneri.size();i++)
    {
      if ((partneri.get(i).getNaziv()).startsWith(s) || (partneri.get(i).getNaziv()).equalsIgnoreCase(s))
      System.out.println(partneri.get(i).toString());
    }
  }
   
    public static void adresa (ArrayList <Partner> partneri)
  {
    System.out.println("Unesite adresa za pretragu: ");
    Scanner scan = new Scanner(System.in);
    String s = new String();
    s = scan.nextLine();
    
    for (int i=0;i<partneri.size();i++)
    {
      if ((partneri.get(i).getAdresa()).startsWith(s) || (partneri.get(i).getAdresa()).equalsIgnoreCase(s))
      System.out.println(partneri.get(i).toString());
    }
  }
}