import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.lang.String;
import java.lang.Math;

  public class IznosException extends Exception
{
   static double brojPuta = 0;
  public IznosException ()
  {
    super ("Iznos za umanjivanje je prevelik!!!");
  }
  
  public IznosException(String msg)
  {
    super(msg);
  }
  
  public IznosException(Klijent k)
  {
    brojPuta++;
    
    Random ran = new Random();
    System.out.println("Iznos za umanjivanje je prevelik!!!");
    
    double iznos = ((double)1.0 - ran.nextDouble()) * (100000000000.0);
    iznos/=Math.pow(10,brojPuta);
    
   try
   {
     k.umanjivanjeIznosa(iznos);
   }
   catch (IznosException Ex)
   {
     System.out.println("Drugi puta se desio izuzetak");
     iznos/=Math.pow(10,brojPuta);
   }
   System.out.println("Iznos ce biti umanjen za " + iznos);
  
  }
     
    
    
  
}