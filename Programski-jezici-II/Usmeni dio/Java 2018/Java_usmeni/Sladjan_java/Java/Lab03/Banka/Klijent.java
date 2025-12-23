import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.util.Random;
  
  
public class Klijent 
{
  private String ime;
  private String prezime;
  private int[] JMBG;
  private double stanjeRacuna;
  
  
  public Klijent()
  {
    ime = "N/N";
    prezime = "N/N";
    
    JMBG = new int [13];
    for (int i=0;i<13;i++)
    {
      JMBG[i] = 0;
    }
    
    stanjeRacuna = 0;
  }
  
  public void unos1 (String a, String b, int[] c, double d)
  {
    ime = a;
    prezime = b;
    stanjeRacuna = d;
    
    for(int i = 0;i<13;i++)
    {
      JMBG[i] = c[i];
    }
  }
  
  public void randomUnos(String a,String b)
  {
    Random ran = new Random();
   // double temp = 9999999999999999999999999.0;
    ime = a;
    prezime = b;
    stanjeRacuna = (1 - ran.nextDouble()) * (10000000 - 0) + 0;
    for (int i=0;i<13;i++)
    {
      JMBG[i] = ran.nextInt(9)+1;
    }
  }
  
  public void unos ()
  {
    Scanner scan = new Scanner(System.in);
    String temp = new String();
    char []pom = new char [14];
    
    System.out.println("Unesite ime: ");
    ime = scan.next();
    System.out.println("Unesite prezime" );
    prezime = scan.next();
    System.out.println("Unesite JMBG: ");
    temp = scan.next();
    
    pom = temp.toCharArray();
    for (int i=0;i<13;i++)
    {
      JMBG[i] = pom[i] - '0';
    }
    
    System.out.println("Unesite stanje racuna: ");
    stanjeRacuna = scan.nextDouble();
    
    scan.close();
  }
  
  public void umanjivanjeIznosa(double iznos) throws IznosException
  {
    if (iznos>stanjeRacuna) throw new IznosException();
    else stanjeRacuna-=iznos;
  }
  
  public void ispis ()
  {
    System.out.println("\n\nKlijent:");
    System.out.print("Ime : "+ ime +"\nPrezime: "+ prezime +"\nJMBG: ");
    for (int i=0;i<13;i++)
    {
      System.out.print(JMBG[i]);
    }
    System.out.print("\n");
    System.out.println("Stanje na racunu: "+ stanjeRacuna);
  }
  
  public static void main (String agrs[])
  {
    Klijent k1 = new Klijent();
    k1.ispis();
    
    Klijent k2 = new Klijent();
    k2.randomUnos("Marko","Markovic");
    k2.ispis();
    
    Klijent k3 = new Klijent();
    k3.unos();
    k3.ispis();
  }
}
  
  