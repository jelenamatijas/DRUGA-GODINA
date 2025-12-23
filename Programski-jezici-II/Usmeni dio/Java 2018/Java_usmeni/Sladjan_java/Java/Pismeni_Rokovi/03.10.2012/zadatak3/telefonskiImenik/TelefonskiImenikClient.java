import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;



public class TelefonskiImenikClient
{
  
  public static void main(String[] args)
  {
    try
    {
      System.out.println("RMIClient started.....");
      
      System.setSecurityManager(new RMISecurityManager());
      
      TelefonskiImenik ti = (TelefonskiImenik) Naming.lookup("//localhost:1099/server");
      
      String request = new String();
      Scanner scan = new Scanner(System.in);
      boolean stop = false;
      
      do
      {
        request = scan.nextLine();
        if ("1".equalsIgnoreCase(request)) 
        {
          String[] nizIspis = ti.pregledSadrzaja();
          if(nizIspis != null)
          {
            System.out.println("SADZAJ IMENIKA");
            for(String s : nizIspis) System.out.println(s);
            System.out.println("=====================================================");
            System.out.println("");
          }
          else 
          {
            System.out.println("Nema sadrzaja u imeniku");
            System.out.println("=====================================================");
            System.out.println("");
          }
        }
        else if("2".equalsIgnoreCase(request))
        {
          System.out.print("Unesite broj telefona za pretragu: ");
          request = scan.nextLine();
          System.out.println(ti.pretrazivanjePoBrojuTelefona(request));
          System.out.println("=====================================================");
          System.out.println("");
        }
        else if("3".equalsIgnoreCase(request))
        {
          String[] nizStringova = ti.prikazSortiranPoPrezimenu();
          System.out.println("IMENIK SORTIRAN PO PREZIMENU");
          for(String s : nizStringova) System.out.println(s);
          System.out.println("=====================================================");
          System.out.println("");
        }
        else if ("4".equalsIgnoreCase(request))
        {
          System.out.println("Unesite podatka za dodavanje u imenik u formatu IME#PREZIME#ADRESA#BROJ_TELEFONA");
          request = scan.nextLine();
          ti.dodavanjeUImenik(request);
        }
        else if ("STOP".equalsIgnoreCase(request))
        {
          stop = true;
        }
        else System.out.println("NEPRAVILNO IZABRANA OPCIJA");
      }
      while(!stop);
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
}