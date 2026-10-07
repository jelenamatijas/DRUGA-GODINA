

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;


public class TOClientThread extends Thread
{
  public String br;
  public String pin;
  public double racun;
  public BufferedReader in;
  public PrintWriter out;
  public Socket sock;
  
  

  
  public TOClientThread()
  {
    
    
    
    
     
      
      try
      {
         InetAddress adr = InetAddress.getByName("127.0.0.1");
        this.sock = new Socket(adr,9000);
        in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
        SimulationClient.sock = this.sock;
        SimulationClient.in = this.in;
        SimulationClient.out = this.out;
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
      start();
    //zatvoren Try
    
  }//zatvoren konstruktor sa parametrima
  
  
  
  public void run()
  {
    System.out.println("Pokrenut ClientThread");
    try
    {
      sleep(1500);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
   System.out.println(readInput());
   Scanner scan = new Scanner(System.in);
   
   String input = new String();
   input = scan.nextLine();
   if ("1".equalsIgnoreCase(input)) login();
   else if ("2".equalsIgnoreCase(input)) sendMsg();
   else if ("3".equalsIgnoreCase(input)) logout();
   else System.out.println("Nevazeca opcija");
  }
  
  
  public void login()
  {
    
    
    String s = new String();
    
    Scanner scan = new Scanner(System.in);
    
    do
    {
      System.out.println("Unesite broj broj telefona i pin u formatu BROJTEL#PIN");
      s = scan.nextLine();
      
      
    }
    while((s.split("#")[1]).length()!=4);
    
    try
    {
      
      out.println("##LOGIN##");
      
      out.println(s);
      
      String response = new String();
      
      response = readInput();
      
     
      
      if(!"##LOGINOK##".equalsIgnoreCase(response)) System.out.println("Greska pri prijavi");
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvorena metoda login
  
  
  public void sendMsg()
  {
    Scanner scan = new Scanner(System.in);
    try
    {
      out.println("##MSG##");
      
      String response = new String();
      
      response = readInput();
    
      Double racun = Double.valueOf(response);
      
      if (racun < 0.11) System.out.println("Nemate dovoljno na racunu da biste poslali poruku!!!");
      else
      {
        System.out.println("Unesite poruku u formatu BROJ_TELEFONA_KORISNIKA#PORUKA");
        
        String message = new String();
        
        message = scan.nextLine();
        
        out.println(message);
        
        response = readInput();
        
        if("##SENT##".equalsIgnoreCase(response)) System.out.println("Poruka poslata");
        else System.out.println("Poruka nije poslata");
        
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvorena funkcija sendMsg
  
  
  public String readInput()
  {
    try
    {
      synchronized(SimulationClient.lock)
      {
      do
      {
        
        
          if(!"".equalsIgnoreCase(SimulationClient.serverMsg))
          {
            System.out.println("Usao u if");
            String s  = SimulationClient.serverMsg;
            SimulationClient.serverMsg = "";
            SimulationClient.lock.notifyAll();
            return s;
          }
          else
          {
            SimulationClient.lock.wait();
          }
        
      }while("".equalsIgnoreCase(SimulationClient.serverMsg));
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    return "";
  }
  
  
  public boolean logout()
  {
    try
    {
      out.println("##STOP##");
      String response = readInput();
      System.out.println(response);
      this.out.close();
      this.in.close();
      this.sock.close();
      return true;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    return false;
  }
    
  }
  
  
  
  