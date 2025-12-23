
import java.io.*;
import java.net.*;


public class InputScanner extends Thread
{
  public BufferedReader in;
  
  public Socket sock;
  
  public InputScanner()
  {
    this.sock = SimulationClient.sock;
    this.in = SimulationClient.in;
   
   start(); 
  }
  
  public void run()
  {
    System.out.println("Pokrenut InputScanner");
    do
    {
      
      try
      {
        synchronized(SimulationClient.lock)
        {
          System.out.println("Usao u synchronized");
          if("".equalsIgnoreCase(SimulationClient.serverMsg))
          {
            System.out.println("Nije procitao jos");
            SimulationClient.serverMsg = in.readLine();
            System.out.println("procitao");
            SimulationClient.lock.notifyAll();
          }
          else
          {
            SimulationClient.lock.wait();
          }
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
    }
    while(SimulationClient.inputSc == true);
    
    
  }//zatvoren run
  
  
}//zatvorena klasa
      
    