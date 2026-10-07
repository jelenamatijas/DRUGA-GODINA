package Projektni_Zadatak.pricalo.clientRadnik;


import Projektni_Zadatak.stanovnik.*;
import java.lang.String;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class ClientThreadRadnik extends Thread
{
  private String username;
  private String password;
  private Socket sock;
  private BufferedReader in;
  private PrintWriter out;
  boolean loginOK = false;
  boolean passOK = false;
  boolean logedin = false;
  
  public ClientThreadRadnik ()
  {
    super();
    boolean serverDostupan = false;
    try
    {
      InetAddress ip = InetAddress.getByName("ASUS-PC");
      this.sock = new Socket (ip,9000);
      ClientRadnik.clientSocket = this.sock;
      if(ip.isReachable(10000)) serverDostupan = true;
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
        
        
        ClientRadnik.in = this.in;
        ClientRadnik.out = this.out;
        
        
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }//zatvoren catch;
      
    }
    
  }//zatvoren konstrukor
  
  // *************************************************
  //metoda run
  
  
  public void run()
  {
    System.out.println("Klijent je pokrenut");
    String msg = new String();
    Scanner scan = new Scanner(System.in);
    boolean logedout = false;
    
    
    String response = new String();
    
   
    
    try
    {
      
      
      String st = new String();
      boolean back = false;
        
        st = readInput();
        
      
        
        
      
      
      if ("OK".equalsIgnoreCase(st))
      {
        System.out.println("Veza sa serverom uspostavljena");
        do
        {
          
          System.out.println("OPCIJE:");
          System.out.println("Za logovanje unesite LOGIN");
          System.out.println("Za registraciju unesite REG");
          System.out.println("Za odjavu unesite LOGOUT");
          System.out.println("Za prekid unesite STOP");
          System.out.println("Za povratak nazad na MENU unesite BACK");
          
          
          if (!"STOP".equalsIgnoreCase(msg))//ako nije naredba STOP nastavljamo sa LOGIN-om
          {
            do
            {
              
              msg = scan.nextLine();
              if ("LOGIN".equalsIgnoreCase(msg)) 
              {
                back = login();
                
              }
              else if ("REG".equalsIgnoreCase(msg))
              {
                back = registration();
              }
              else 
              {
                System.out.println("Morate se prvo prijaviti na sistem pomocu LOGIN ili ako nemate nalog prvo se morate registrovati");
                System.out.println("OPCIJE:");
                System.out.println("Za logovanje unesite LOGIN");
                System.out.println("Za registraciju unesite REG");
                System.out.println("Za odjavu unesite LOGOUT");
                System.out.println("Za prekid unesite STOP");
                System.out.println("Za povratak nazad na MENU unesite BACK");
                
              }
             
            }
            while((!"LOGIN".equalsIgnoreCase(msg) || !"REG".equalsIgnoreCase(msg)) && !back && !logedin);
            
            if (logedin)
            {
              do
              {
                msg = "";
                  
                  System.out.println("OPCIJE:");
                System.out.println("Za odjavu unesite LOGOUT");
                System.out.println("Za poruku unesite MSG#USERNAME korisnika kojem zelite da posaljete poruku, # , napisite poruku koju zelite poslati.");
                
                do 
                {
                  msg = scan.nextLine();
                  
                  if (msg.startsWith("MSG#") || msg.startsWith("msg#"))sendMsg(msg);
                  else if ("LOGOUT".equalsIgnoreCase(msg)) logedout = logout();
                  else msg = "BACK";
                } while (!"BACK".equalsIgnoreCase(msg) && logedin);
              }while(logedin && !logedout);
            }//zatvoren if za logedin
                  
              
          }//zatvoren if za razlicito od STOP
          else
          {
            out.println("STOP");//salje se serveru STOP da obustavi komunikaciju
          }//zatvoren else za STOP
          
        } while (back);
          
      }//zatvoren if(OK)
      else
      {
        System.out.println("Veza sa serverom nije uspostavljena");
      }//zatvoren else(OK)
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
                                
  }//zatvoren run
  
  public String readInput()
  {
        

   
      String s = new String();
      try
      {
    synchronized (ClientRadnik.lock)
    {
     
      if (ClientRadnik.serverMsg.equals("")) 
      {
       
        ClientRadnik.lock.wait();
        //wait();
        //notifyAll();
        
        s = readInput();
      }
      else
      {
       
        s = ClientRadnik.serverMsg;
         ClientRadnik.serverMsg = "";
         ClientRadnik.lock.notifyAll();
     }

    }
      }//zatvoren TRY blok
      catch (Exception ex)
      {
        System.out.println("Izuzetak kod ocitavanja serverMsg-a");
      }
    return s;
  }//zatvoren read
  
  
  public boolean login ()
  {
    Scanner scan = new Scanner(System.in);
    String msg = new String("LOGIN");
    String response = new String();
    response = "";
    out.println(msg);
            do
            {
            System.out.println("Unesite USERNAME:");
            msg ="";
            
            msg += scan.nextLine();
            
            if(!"BACK".equalsIgnoreCase(msg))
            {
              System.out.println("Unesite PASSWORD:");
              msg += "#";
              msg += scan.nextLine();
              
              out.println(msg);
              
              response = readInput();
              
            
            if ("LOGINOK".equalsIgnoreCase(response)) 
            {
              System.out.println("Uspjesno ste se prijavili.");
              logedin = true;
            }
            else if ("INVALIDPASS".equalsIgnoreCase(response)) 
            {
              System.out.println("Uneseni password nije ispravan");
             
              
            }
            else
            {
              System.out.println("Nepostojeci USERNAME, ako niste registrovani odaberite opciju REG za registraciju");
              
            }
            
            }
            else 
            {
              out.println(msg);
              return true;
            }
            } while(!"LOGINOK".equalsIgnoreCase(response));
            return false;
  }//zatvorena metoda login
  
  public boolean registration()
  {
    Scanner scan = new Scanner(System.in);
    String msg = new String("REG");
    String response = new String();
    boolean jmbOK = true;
    out.println(msg);
    
    
    do
    {
      System.out.println("Unesite USERNAME:");
      msg ="";
      msg += scan.nextLine();//scan za USERNAME
      
      System.out.println("Unesite PASSWORD: ");
      msg += "#";
      msg += scan.nextLine();
      
      System.out.println("Unesite IME: ");
      msg += "#";
      msg += scan.nextLine();
      
      System.out.println("Unesite PREZIME: ");
      msg += "#";
      msg += scan.nextLine();
      
      System.out.println("Unesite JMBG: ");
      String s = new String();
      
      s = scan.nextLine();
      if (Stanovnik.ispravanMaticniBroj(s))
      {
        msg += "#";
        msg += s;
        out.println(msg);
        s = readInput();
        if ("USERNAME TAKEN".equalsIgnoreCase(s))
        {
          System.out.println("USERNAME koje ste unijeli je vec zauzeto. Morate unijeti drugo USERNAME.");
          return false;
        }
        else System.out.println("Uspjesno ste se registrovali.");
          
        return true;
      }
      else
      {
        System.out.println("Neispravan maticni broj");
        jmbOK = false;
        
      }
      
      
    }//zatvoren do
    while(!"BACK".equalsIgnoreCase(msg) || !jmbOK);
    
      return false;
  }//zatvorena metoda za registraciju
  
  public boolean logout()
  {
    out.println("LOGOUT");
    
    String s = new String(readInput());
    
    if ("LOGOUTOK".equalsIgnoreCase(s)) 
    {
      System.out.println("Uspjesno ste se odjavili");
      logedin = false;
      try
      {
        in.close();
        out.close();
      }
      catch (Exception ex)
      {
        System.out.println("Greska pri zatvaranju konekcija.");
      }
        
      return true;
      
    }//zatvoren u logout funkciji
    else System.out.println("Greska pri odjavljivanju");
    return false;
    
  }
      
   public void sendMsg(String s)
   {
     out.println(s);
     
     String control = new String();
     
     control = readInput();
     
     if ("SENT".equalsIgnoreCase(control));
     else System.out.println("Korisnik " + s.split("#")[1] + " trenutno nije dostupan.");
   }
    
  
}//zatvorena klasa
  
    
  
  