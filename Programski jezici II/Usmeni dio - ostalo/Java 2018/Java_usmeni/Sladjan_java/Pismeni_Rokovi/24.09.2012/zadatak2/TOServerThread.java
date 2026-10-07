
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;


public class TOServerThread extends Thread
{
  public BufferedReader in;
  public PrintWriter out;
  public Socket sock;
  private Korisnik korisnik;
  public String username;
  
  
  
  public TOServerThread() {};
  
  public TOServerThread(Socket s)
  {
    this.sock = s;
    
    try
    {
      in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
      start();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  
  public void run()
  {
    System.out.println("ServerThread pokrenut");
    
    try
    {
      
     out.println("##OK##");
     
     
     String request = new String();
     System.out.println("Poslao");
     do 
     {
       request = in.readLine();
       
       if ("##LOGIN##".equalsIgnoreCase(request)) login();
       else if("##MSG##".equalsIgnoreCase(request)) sendMsg();
       else if ("##CHARGE##".equalsIgnoreCase(request)) charge();
       else if ("##STOP##".equalsIgnoreCase(request)) logout();
       else out.println("##INVALID##");
       
     }
     while (!"##LOGIN##".equalsIgnoreCase(request) ||!"##MSG##".equalsIgnoreCase(request) || !"##CHARGE##".equalsIgnoreCase(request)
              || !"##STOP##".equalsIgnoreCase(request) );
      
      
    }
     catch(Exception ex)
     {
       ex.printStackTrace();
     }
     
  }//zatvoren run
  
  
  public boolean login()
  {
    String request = new String();
    boolean mark = false;
    
    System.out.println("Usao u login");
    try
    {
      request = in.readLine();
      
      synchronized(TOServer.korisnici)
      {
        for(int i=0;i<TOServer.korisnici.size();i++)
        {
          Korisnik temp = new Korisnik();
          temp = TOServer.korisnici.get(i);
          String br = new String();
          String pin = new String();
          br += temp.br;
          pin = temp.pin;
          
          
          if((br).equalsIgnoreCase(request.split("#")[0]) && pin.equalsIgnoreCase(request.split("#")[1]))
          {
            this.korisnik = temp;
            TOServer.aktivniKorisnici.put(request,this);
            username = request;
            mark = true;
             File fi = new File("reg.usr");
             if (fi.exists()) 
             {
               
               ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(fi,true));
               oos.writeObject(TOServer.korisnici);
             }
            out.println("##LOGINOK##");
            
          }
          
         
          
        }
        
        if(mark == false)
           {
            Random ran = new Random();
            double k = (double)(ran.nextInt(4));
            Korisnik temp = new Korisnik(request.split("#")[0],request.split("#")[1],k);
            TOServer.korisnici.add(temp);
            TOServer.aktivniKorisnici.put(request,this);
            
               ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("reg.usr",true));
               oos.writeObject(TOServer.korisnici);
               System.out.println("Upisao");
            username = request;
          }
        
      }
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    return true;
  }//zatvoren login
  
  
  public void forward(String msg)
  {
    try
    {
      out.println(msg);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  
  public void sendMsg()
  {
    String request = new String();
    boolean sent = false;
    try
    {
      request = in.readLine();
      synchronized(TOServer.aktivniKorisnici)
      {
        for(String s : TOServer.aktivniKorisnici.keySet())
        {
          if((s.split("#")[0]).equalsIgnoreCase(request.split("#")[0]))
          {
            TOServerThread tempThread = TOServer.aktivniKorisnici.get(s);
            tempThread.forward(this.korisnik.br + " : " + (request.split("#")[1]));
            sent = true;
            out.println("##SENT##");
          }
        }
        if(!sent) out.println("##NOTSENT##");
      }
            
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  
  }
  
  
  public void charge()
  {
    Random rand = new Random();
    
    
    synchronized(TOServer.korisnici)
    {
      for(int i=0;i<TOServer.korisnici.size();i++)
      {
        Korisnik temp = TOServer.korisnici.get(i);
        if( temp.equals(korisnik))
        {
          TOServer.korisnici.remove(temp);
          korisnik.racun = (double)(rand.nextInt(3));
          TOServer.korisnici.add(korisnik);
          
          try
          {
          File fi = new File("reg.usr");
             if (fi.exists()) 
             {
               ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(fi,true));
               oos.writeObject(TOServer.korisnici);
             }
          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }
          
        }
      }
      TOServer.korisnici.notifyAll();
    }
    
  }//zatvorena funkcija charge
  
  
  public boolean logout()
  {
    try
    {
      synchronized(TOServer.aktivniKorisnici)
      {
        TOServer.aktivniKorisnici.remove(username);
        out.println("##LOGOUTOK##");
        this.out.close();
        this.in.close();
        this.sock.close();
      }
      
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
    return true;
    
  }//zatvoren logout
   
  
}//zatvorena klasa
      
      
      
      
      