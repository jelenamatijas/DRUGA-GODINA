import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

public class EuroBasketClient
{
  public static void main(String[] args)
  {
    try
    {
      System.setSecurityManager(new RMISecurityManager());
      
      EuroBasketInterface ebi = (EuroBasketInterface) Naming.lookup("//localhost:1099/server");
      
      System.out.println("Za prikazivanje danasnjih rezultata unesite REZULTATI");
      System.out.println("Za prikaz rezultata jednog tima unesite NAZIV_TIMA");
      
      String request = new String();
      
      Scanner scan = new Scanner (System.in);
      
      request = scan.nextLine();
      
      if("REZULTATI".equalsIgnoreCase(request))
      {
        System.out.println(ebi.pregledRezultataUtakmicaZaDanasnjiDatum());
      }
      else
      {
        System.out.println(ebi.pregledRezultataJednogTima(request));
      }
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
}
        
        
        
        
        
      
        