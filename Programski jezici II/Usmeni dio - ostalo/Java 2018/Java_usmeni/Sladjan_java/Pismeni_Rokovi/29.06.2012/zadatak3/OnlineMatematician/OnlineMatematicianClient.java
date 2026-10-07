import java.util.Scanner;
import java.lang.String;
import java.rmi.*;
import java.rmi.registry.*;


public class OnlineMatematicianClient
{
  public static void main (String[] args)
  {
    System.out.println("Client started...");
    System.setSecurityManager(new RMISecurityManager());
    
    try
    {
      OnlineMatematicianInterface omi = (OnlineMatematicianInterface) Naming.lookup("//localhost:1099/Server");
      
      Paralelogram p = new Paralelogram(10.0,5.0,30.0);
      
      System.out.println(omi.volume(p));
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
}