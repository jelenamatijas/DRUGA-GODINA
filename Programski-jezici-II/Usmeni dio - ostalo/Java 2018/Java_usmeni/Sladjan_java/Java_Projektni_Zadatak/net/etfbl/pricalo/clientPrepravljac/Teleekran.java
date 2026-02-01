package net.etfbl.pricalo.clientPrepravljac;

import net.etfbl.pricalo.clientPrepravljac.ClientPrepravljac;

import java.lang.String;
import java.net.*;
import java.io.*;


public class Teleekran extends Thread
{
  private Socket sock;
  private BufferedReader in;
  private PrintWriter out;
  public static boolean logedout = false;
  
  public Teleekran ()
  {
    super();
    
        in = ClientPrepravljac.in;
        out = ClientPrepravljac.out;
    
      start();
    
    
  }//zatvoren konstrukor
  
  
  public void run()
  {
    boolean logedout = false;
    System.out.println("InputScannerThread je pokrenut");
    
//    if(!ClientRadnik.inputNeeded)try
//    {
//      sleep(2500);
//    }
//    catch(Exception ex)
//    {
//      System.out.println("Greska kod uspavljivanja inputStreamThread-a");
//      ex.printStackTrace();
//    }
    
    
    while (!ClientPrepravljac.stop && !logedout)
    {
      readLine();
    }//zatvoren while
    
    try
    {
      in.close();
      out.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvorena run metoda
  
  public  String getServerMsg ()
  {
    String s = new String();
    try
    {
      s = in.readLine();
    }
    catch (Exception ex)
    {
      System.out.println("Greska u getServerMsg metodi");
    }
    return s;
  }
  
  public void  readLine()
  {
    
    String s = new String();
    try
      {
     
        
        if ((s = in.readLine()) != null) 
        {
          
          
          if (!s.startsWith("MSG")&&!s.startsWith("TELEEKRAN"))
          {
            if ("##STOP##".equalsIgnoreCase(s))
            {
              System.out.println("Aplikacija zaustavljena!!!");
              
              ClientThreadPrepravljac.logedout = true;
              ClientPrepravljac.ref.interrupt();
              this.interrupt();
            }
            else
            {
            synchronized (ClientPrepravljac.lock)
            {
              
              if(!ClientPrepravljac.serverMsg.equals(""))
              {
                ClientPrepravljac.lock.wait();
              }
              ClientPrepravljac.serverMsg = s;
              ClientPrepravljac.lock.notifyAll();
            }
           }//zatvoren if od provjere da li je dobijena poruka poruka korisnika ili servera
          }
          else System.out.println(s);
          
        }
      }
     catch (SocketException se)
      {
        logedout = true;
      }
     catch (IOException ioe)
     {
       logedout = true;
     }
      catch (Exception ex)
      {
        System.out.println("Izuzetak u InputScannerThread kod citanja");
        ex.printStackTrace();
      }
     
      
  }
  
  
}//zatvorena klasa