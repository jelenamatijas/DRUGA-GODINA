import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EuroBasketServer extends UnicastRemoteObject implements EuroBasketInterface
{
  public static ArrayList<String> danasnjeUtakmice = new ArrayList<String>();
  
  
  public EuroBasketServer() throws RemoteException {};
  
  public String pregledRezultataUtakmicaZaDanasnjiDatum() throws RemoteException
  {
    String response = new String();
    String date = new String();
   
    date = new SimpleDateFormat("YYYY#MM#dd").format(Calendar.getInstance().getTime());
    
    synchronized(danasnjeUtakmice)
    {
    try
    {
      File f = new File("rezultati");
      
      File[] fileList = f.listFiles();
      
      for(File tempFile : fileList)
      {
        if(date.equalsIgnoreCase(tempFile.getName()))
        {
          ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
          danasnjeUtakmice = (ArrayList<String>) ois.readObject();
        }
      }
      
    
    for (int i =0;i< danasnjeUtakmice.size();i++) 
    {
      response += danasnjeUtakmice.get(i);
      response += "#";
    }
     }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    }
    return response;
  }
  
  public String pregledRezultataJednogTima(String tim)
  {
    String response = new String();
    
    File f = new File("rezultati");
    
    try
    {
      File[] listFiles = null;
      listFiles = f.listFiles();
      
      if(listFiles != null) System.out.println("Nije NULL");
      else System.out.println("Jeste NULL");
      
      for(File tempFile : listFiles)
      {
        System.out.println("Usao u for ##");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
        
        ArrayList<String> tempArray = (ArrayList<String>) ois.readObject();
        
        for(int i = 0 ;i<tempArray.size();i++)
        {
          String s = new String();
          s = tempArray.get(i);
          System.out.println("PRJT : S = "+s);
          
          if(s.contains(tim)) 
          {
            response += s;
            response += "#";
          }
        }
        ois.close();
      }
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    return response;
  }
  
  
  public static void main(String[] args) throws ImpossibleCombination
  {
    
    Random rand = new Random();
    String[] timovi = {"Njemakca","Danska","Srbija","Francuska"};
    
    for(int i=0;i<2;i++)
    {
      int p1 = rand.nextInt(4);
      int p2 =  rand.nextInt(4);
      
      if (p1 == p2) throw new ImpossibleCombination();
      else
      {
        synchronized(danasnjeUtakmice)
        {
          String upis = new String();
          upis += timovi[p1] + " - " + timovi[p2];
          
          int rez1 = 30 + rand.nextInt(70);
          int rez2 = 30 + rand.nextInt(70);
          
          upis += " -> " + rez1 +":" + rez2;
          danasnjeUtakmice.add(upis);
        }
      }
    }
    synchronized(danasnjeUtakmice)
    {
      String date = new SimpleDateFormat("YY#MM#dd").format(Calendar.getInstance().getTime());
      try
      {
        File f = new File("rezultati");
        
        if(!f.exists()) f.mkdir();
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f+ File.separator + date));
        oos.writeObject(danasnjeUtakmice);
        oos.close();
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    try
    {
      
    System.setSecurityManager (new RMISecurityManager());
    
    EuroBasketServer ebs = new EuroBasketServer();
    
    Naming.rebind("//localhost:1099/server",ebs);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvoren main
  
  
  
  
}//zatvorena klasa
      
      
    
    