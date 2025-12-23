//implementiranje klijenta
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import java.util.Scanner;
import Informator.*;
import Informator.ISPackage.*;
import java.io.*;

public class ISClient {
  
  public static int id =0;
  
  static
  {
    ++id;
  }
  
  public static void main(String[] args) {
    boolean logedin = false;
    Scanner scan = new Scanner(System.in);
    try {
      // inicijalizuj ORB, prosledi mu command-line parametre
      ORB orb = ORB.init(args, null);
      
      // pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
// pokupi kanalizu
      NameComponent nc = new NameComponent("IS", "");
      NameComponent path[] = {nc};
      IS a = ISHelper.narrow(ncRef.resolve(path));

      // koristi CORBA servant objekat
      String input = new String();
      
      Korisnik korisnik = new Korisnik();
      Integer inte = new Integer(id);
      korisnik.id = inte.toString();
      logedin = login(a,korisnik);
      do
      {
        System.out.println("OPCIJE:");
        System.out.println("Za repertoar bioskopa unesite BIOSKOP");
        System.out.println("Za loto kombinaciju unesite LOTO");
        System.out.println("Za horoskop unesite HOROSKOP");
        System.out.println("Za odjavu unesite ODJAVA");
        
        input = scan.nextLine();
        
        
        
        if(logedin)
        {
          if("BIOSKOP".equalsIgnoreCase(input)) repertoar(a);
          else if("LOTO".equalsIgnoreCase(input)) loto(a);
          else if ("HOROSKOP".equalsIgnoreCase(input)) horoskop(a);
          else if ("ODJAVA".equalsIgnoreCase(input)) logedin = logout(a,korisnik);
          else System.out.println("Nepravilna opcija!!");
        }
        
     
      
      }
      while(logedin);
      
      

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public static boolean login(IS a,Korisnik korisnik)
  {
    String response = new String();
    
    response = a.prijava(korisnik);
    
    if (!"##LOGINOK##".equalsIgnoreCase(response)) 
    {
      System.out.println("Prijava nije uspjela!!!");
      return false;
    }
    else 
    {
      System.out.println("Prijava uspjela");
    }
    
    return true;
  }
  
  public static boolean logout(IS a,Korisnik korisnik)
  {
    String response = new String();
    
    response = a.odjava(korisnik);
    
    if (!"##LOGOUTOK##".equalsIgnoreCase(response)) 
    {
      System.out.println("Odjava nije uspjela!!!");
      return true;
    }
    else 
    {
      System.out.println("Odjava uspjela");
    }
    
    return false;
  }
  
  public static void repertoar(IS a)
  {
    Buffer b = new Buffer();
    
    b = a.repertoar();
    
    try
    {
      File f = new File("RepertoarBioskopa.txt");
      
      if (!f.exists()) f.createNewFile();
      
      OutputStream out = new FileOutputStream(f);
      
      out.write(b.buffer);
      
      out.close();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  public static void loto (IS a)
  {
    System.out.println("Loto kombinacija: " + a.loto());
  }
  
  public static void horoskop(IS a)
  {
    System.out.println("Horoskop: " + a.horoskop());
  }
      
}
