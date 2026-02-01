import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.net.*;
public class TOServer
{
  public static ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
  public static HashMap<String,TOServerThread> aktivniKorisnici = new HashMap<String,TOServerThread>();
  
  
  public static void main(String[] args)
  {
    ServerSocket ss;
  try
  {
    File f = new File("reg.usr");
    if(f.exists())
    {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reg.usr"));
    
    korisnici = (ArrayList<Korisnik>) ois.readObject();
    ois.close();
    }
    ss = new ServerSocket(9000);
    
    while(true)
  {
    try
    {
      TOServerThread st = new TOServerThread(ss.accept());
      System.out.println("Client accepted");
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    }
    
    
    
  }
  catch(Exception ex)
  {
    ex.printStackTrace();
  }
  
  
  
  }
  
  
}