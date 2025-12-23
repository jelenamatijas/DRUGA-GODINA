import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class EscapeGameServer
{
  public static ArrayList<String> usernames = new ArrayList<String>();
  
  static
  {
    File f = new File("usernames.ser");
    if (f.exists())
    {
    try
    {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usernames.ser"));
      usernames = (ArrayList<String>) ois.readObject();
      ois.close();
    }
    catch(Exception ex)
    {
      System.out.println("Greska kod ucitavanja usernames.ser fajla");
      ex.printStackTrace();
    }
    }
    
  }
  
  public static void main(String[] args)
  {
    try
    {
      ServerSocket ss = new ServerSocket(9000);
      
      while(true)
      {
      EscapeGameServerThread egst = new EscapeGameServerThread(ss.accept());
      System.out.println("Client accepted..");
      }
    }
    catch(Exception ex)
    { 
      ex.printStackTrace();
    }
    
  }
  
}