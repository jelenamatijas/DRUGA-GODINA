package net.etfbl.pricalo.agencija;



import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;


public class AgencijaThread extends Thread
{
  private Socket sock;
  private BufferedReader in;
  private PrintWriter out;
  
  public static boolean back = false;
  public static boolean logedout = false;
  
 
  
  
  public AgencijaThread()
  {
     super();
    boolean serverDostupan = false;
    try
    {
     
      
       String serverIP = new String();
      //InetAddress ip = InetAddress.getByName("ASUS-PC");
      File f = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "agencija" + File.separator + "serverIP.txt");
      
      if (f.exists() && f.isFile())
      {
        BufferedReader readIP = new BufferedReader(new FileReader(f));
        serverIP = readIP.readLine();
        
        InetAddress ip = InetAddress.getByName(serverIP);
        this.sock = new Socket (ip,9000);
        
        if(ip.isReachable(10000)) serverDostupan = true;
        readIP.close();
      }
    }
    catch (UnknownHostException uh)
    {
      System.out.println("Nedostupan host!!!");
    }
    catch (SecurityException se)
    {
      System.out.println("Security manager ne dozvoljava povezivanje");
    }
     catch(IOException ex)
     {
       System.out.println("Desio se IO Exception pri uspostavljanju veze");
     }//zatvoren catch
    
    if(serverDostupan)
    {
      try 
      {
        in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
        
        
        Agencija.in = this.in;
        Agencija.out = this.out;
        
        
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }//zatvoren catch;
      
    }
    start();
  }//zatvoren konstrukor
  
  public void run()
  {
    String input = new String();
    String keyboardInput = new String();
    Scanner scan = new Scanner(System.in);
    
    boolean logedout = false;
    
    try
    {
      input = in.readLine();
      
      if ("OK".equalsIgnoreCase(input))
      {
        System.out.println("Veza sa serverom uspostavljena");
        out.println("AGENCIJA");
        
        do
        {
          System.out.println("Za pravljenje novog izvjestaja unesite 1");
          System.out.println("Za citanje sadrzaja izvjestaja unesite 2");
          System.out.println("Za odjavu unesite LOGOUT");
          
          keyboardInput = scan.nextLine();
          
          if ("1".equalsIgnoreCase(keyboardInput) && !logedout)logedout = makeReport();
          else if ("2".equalsIgnoreCase(keyboardInput) && !logedout)logedout = readReport();
          else if ("LOGOUT".equalsIgnoreCase(keyboardInput) && !logedout)logedout = logout();
          else System.out.println("Pogresno unijeta opcija!!!");
        }
        while (!logedout);
        
      }
      else
      {
        System.out.println("Veza sa serverom nije uspostavljena.");
      }
    }
    catch (IOException ioex)
    {
      System.out.println("Greska pri citanju sa stream-a.");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
      
  }//zatvorena funkcija run
  
  
   public boolean makeReport()
  {
    Scanner scan = new Scanner(System.in);
    out.println("1");
    
    System.out.println("Unesite sadrzaj izvjestaja");
    
    String keyboardInput = new String();
    
    keyboardInput = scan.nextLine();
    
    out.println(keyboardInput);
    
    return false;
  }
  
  public boolean readReport()
  {
    out.println("2");
    
    String input = new String();
    String keyboardInput = new String();
    Scanner scan = new Scanner(System.in);
    
    try
    {
      input = in.readLine();
      
      
      if ("##STOP##".equalsIgnoreCase(input) || "##PAUSE##".equalsIgnoreCase(input))
      {
        if("##PAUSE##".equalsIgnoreCase(input))
        {
        if (simulationPause()) return true;
        }
        else return true;
      }
      
      if ("FILESFOUND".equalsIgnoreCase(input))
      {
        System.out.println("Files found");
        input = in.readLine();
        
        
        
        if ("##STOP##".equalsIgnoreCase(input) || "##PAUSE##".equalsIgnoreCase(input))
      {
        if("##PAUSE##".equalsIgnoreCase(input))
        {
        if (simulationPause()) return true;
        }
        else return true;
      }
        
        
        
        int k = Integer.valueOf(input);
        input = "";
        
        input = in.readLine();
        
        if ("##STOP##".equalsIgnoreCase(input) || "##PAUSE##".equalsIgnoreCase(input))
      {
        if("##PAUSE##".equalsIgnoreCase(input))
        {
        if (simulationPause()) return true;
        }
        else return true;
      }
        
        String files = input;
        
        for (int i=0;i<k;i++) System.out.println((i+1) + ". " + files.split(" ")[i]);
        
        System.out.println("Za preuzimanje sadrzaja odredjenog izvjestaja unesite redni broj fajla.");
        
        keyboardInput = scan.nextLine();
        
        k=0;
        k=Integer.valueOf(keyboardInput);
        k -= 1;
        
        Integer m = new Integer(k);
        
        out.println(m.toString());
        
        input = in.readLine();
        
        if ("##STOP##".equalsIgnoreCase(input) || "##PAUSE##".equalsIgnoreCase(input))
      {
        if("##PAUSE##".equalsIgnoreCase(input))
        {
        if (simulationPause()) return true;
        }
        else return true;
      }
        
        System.out.println("Sadrzaj izvjestaja: " + input);
        
        return false;
      }
      else
      {
        System.out.println("Nema kreiranih sadrzaja");
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
    return false;
  }//zatvorena metoda readReport
  
   public boolean logout()
  {
    out.println("LOGOUT");
    
    try
    {
      String s = new String(in.readLine());
      
      if ("##STOP##".equalsIgnoreCase(s) || "##PAUSE##".equalsIgnoreCase(s))
      {
        if("##PAUSE##".equalsIgnoreCase(s))
        {
        if (simulationPause()) return true;
        }
        else return true;
      }
      
      if ("LOGOUTOK".equalsIgnoreCase(s)) 
      {
        System.out.println("Uspjesno ste se odjavili");
        
        try
        {
          in.close();
          out.close();
          this.sock.close();
        }
        catch (Exception ex)
        {
          System.out.println("Greska pri zatvaranju konekcija.");
        }
        
        return true;
        
      }//zatvoren u logout funkciji
      else System.out.println("Greska pri odjavljivanju");
    }
    catch (IOException ioex)
    {
      System.out.println("Greska pri citanju sa stream-a pri odjavljivanju");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return false;
    
  }
   
   
   public boolean simulationPause()
   {
     System.out.println("Paused....");
     
     
     try
     {
       String control = new String();
       
       control = in.readLine();
       
       if (control != null && "RESUME".equalsIgnoreCase(control)) return false;
     }
      catch (SocketException se)
      {
       se.printStackTrace();
       return true;
     }
     catch(IOException ioex)
     {
       System.out.println("Greska pri citanju sa stream-a");
       return true;
     }
     catch(Exception ex)
     {
       
     ex.printStackTrace();
     return true;
   }
     return false;
   }//zatvorena funkcija simulation pause
  
  
}//zatvorena klasa agencija thread