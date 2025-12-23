package Lab09.simple_client_server.server;

import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.util.Random;
import java.net.*;


public class ServerThread extends Thread
{
  
  private Socket s;
  private int broj;
  
  public ServerThread (Socket sock,int broj)
  {
    this.s = sock;
    this.broj = broj;
    start();
  }
  
  
  public void run()
  {
    BufferedReader in;
    PrintWriter out;
    String request = new String();
    try
    {
      in = new BufferedReader(new InputStreamReader (this.s.getInputStream()));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.s.getOutputStream())),true);
      
      while(!"KRAJ".equals(request = in.readLine()))
      {
        System.out.println("Request je: "+request);
        System.out.println("Klijent [" +this.broj+"]: "+request);
        Random ran = new Random();
        int temp = ran.nextInt(80) - 30;
        out.println(temp);
        System.out.println("Vracena temperatura");
      }   
        
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
}
      
      