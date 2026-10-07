import java.lang.String;
import java.util.ArrayList;
import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import Informator.ISPackage.*;
import Informator.*;
import java.util.HashMap;
import java.util.Random;

public class ISServant extends ISPOA{
  
  public static HashMap<String,Korisnik> aktivniKorisnici = new HashMap<String,Korisnik>();
  
  
  
  @Override
  public String prijava (Korisnik korisnik)
  {
    System.out.println("ID korisnika koji se prijavljuje je: "+ korisnik.id);
    synchronized(ISServant.aktivniKorisnici)
    {
      System.out.println("Lista korisnickih id-ova:");
      for(String s : aktivniKorisnici.keySet())
      {
        System.out.println(s);
        if(s.equalsIgnoreCase(korisnik.id)) 
        {
          return "##IDTAKEN##";
        }
      }
      aktivniKorisnici.put(korisnik.id,korisnik);
      
      
    }
    return "##LOGINOK##";
  }
        
    
   
    
  

  @Override
  public String odjava(Korisnik korisnik)
  {
    synchronized(aktivniKorisnici)
    {
      for(String s: aktivniKorisnici.keySet())
      {
        if(s.equalsIgnoreCase(korisnik.id)) 
        {
          aktivniKorisnici.remove(s);
          return "##LOGOUTOK##";
        }
      }
    }
    
    return "##LOGOUTNOK##";
  }
  
  @Override
  public String loto()
  {
    String s = new String();
    Random rand = new Random();
    
    
    for(int i=0;i<7;i++)
    {
      s += rand.nextInt(41);
      s += " ";
    }
    
    return s;
  }
  
  @Override
  public String horoskop()
  {
    Random rand = new Random();
    String horoskop = new String();
    
    int size = rand.nextInt(101);
    
    for(int i=0;i<size;i++)
    {
      int k = rand.nextInt(2);
      
      if(k==1)
      {
        horoskop += (char)('a' + rand.nextInt(26));
      }
      else
      {
        horoskop += (char)('A' + rand.nextInt(26));
      }
    }
    return horoskop;
  }
  
  @Override
  public Buffer repertoar()
  {
    File f = new File("repertoar.txt");
    Buffer b = new Buffer();
    
    try
    {
      if(f.exists())
      {
        
        b.buffer = new byte[(int)f.length()];
        
        byte[] niz = new byte[(int)f.length()];
        
        InputStream in = new FileInputStream(f);
        
        int count = 0;
        
        while((count = in.read(niz)) >= 0)
        {
          b.buffer = niz;
        }
        
        in.close();
      }
      return b;
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    return null;
  }

}
