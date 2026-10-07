import java.util.Scanner;
import java.lang.String;
import java.util.Arrays;
import java.util.Random;

public class Banka
{
  private Klijent[] nizKlijenata;
  private int brojKlijenata = 0;
  
  public Banka()
  {
    Scanner scan = new Scanner (System.in);
    
    System.out.println ("Unesite broj klijenata: ");
    
    brojKlijenata = scan.nextInt();
    
    nizKlijenata = new Klijent[brojKlijenata];
    for (int i=0;i<brojKlijenata;i++) nizKlijenata[i] = new Klijent();
    
    for (int i=0;i<brojKlijenata;i++)
    {
      String a = new String();
      String b = new String();
      System.out.println("Unesite ime: ");
      a = scan.next();
      System.out.println("Unesiste prezime: ");
      b = scan.next();
      
      nizKlijenata[i].randomUnos(a,b);
    }
    scan.close();
  }
  
  
  public void ispis ()
  {
    for (int i=0;i<brojKlijenata;i++)
    {
      nizKlijenata[i].ispis();
    }
  }
  
  
  
  public static void main (String args[]) 
  {
    Banka bank1 = new Banka();
    Random ran = new Random();
    
    bank1.ispis();
    
    double iznos = ((double)1.0 - ran.nextDouble()) * (100000000000.0);
    System.out.print("\nRandom broj je " + ran.nextDouble() +"\n");
    System.out.print("\nIznos za umanjivanje je " + iznos +"\n");
    try
    {
      for (int i=0;i<bank1.brojKlijenata;i++) bank1.nizKlijenata[i].umanjivanjeIznosa(iznos);
    }
    catch (Exception Ex)
    {
      System.out.println(Ex);
    }
    
    bank1.ispis();
      
  }
      
  
}