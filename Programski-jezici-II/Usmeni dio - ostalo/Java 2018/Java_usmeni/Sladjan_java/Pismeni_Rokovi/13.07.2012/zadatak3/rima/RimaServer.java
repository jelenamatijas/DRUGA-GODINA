import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.Arrays;


public class RimaServer extends UnicastRemoteObject implements RimaInterface
{
  public RimaServer() throws RemoteException {};
  public boolean savrsenaRima(String[] strofa) throws RemoteException
  {
    System.out.println("Usao u funckiju savrsenaRima()");
    String[] stihovi = new String[4];
    for(int i=0;i<4;i++)
    {
      String stih = strofa[i];
      
    
      
      stih = stih.toUpperCase();
      
      int najveciIndeks = 0;
      for(int j=0;j<stih.length();j++)
      {
        if(stih.charAt(j) == 'A' || stih.charAt(j) == 'E' || stih.charAt(j) == 'I' || stih.charAt(j) == 'O'
             || stih.charAt(j) == 'U' || stih.charAt(j) == ' ')
        {
          if(j!= (stih.length()-1)) 
          {
            if(stih.charAt(j) == ' ')najveciIndeks = j+1;
            else najveciIndeks = j;
          }
        }
        
        
      }
      
      stihovi[i] = stih.substring(najveciIndeks,stih.length());
      System.out.println("SAVRSENA RIMA: stihovi["+i+"] " + stihovi[i]);
      
    }
    
    if(stihovi[0].equalsIgnoreCase(stihovi[1]) && stihovi[0].equalsIgnoreCase(stihovi[2]) && stihovi[0].equalsIgnoreCase(stihovi[3])) return true;
    else return false;
    
  }
  
  
  public boolean ukrstenaRima(String[] strofa) throws RemoteException
  {
    System.out.println("Usao u funckiju unakrsnaRima()");
    String[] stihovi = new String[4];
    for(int i=0;i<4;i++)
    {
      String stih = strofa[i];
      
    
      
      stih = stih.toUpperCase();
      int najveciIndeks = 0;
      for(int j=0;j<stih.length();j++)
      {
        if(stih.charAt(j) == 'A' || stih.charAt(j) == 'E' || stih.charAt(j) == 'I' || stih.charAt(j) == 'O'
             || stih.charAt(j) == 'U' || stih.charAt(j) == ' ')
        {
          if(j!= (stih.length()-1)) 
          {
            if(stih.charAt(j) == ' ')najveciIndeks = j+1;
            else najveciIndeks = j;
          }
        }
        
        
      }
      
      stihovi[i] = stih.substring(najveciIndeks,stih.length());
      System.out.println("UKRSTENA RIMA: stihovi["+i+"] " + stihovi[i]);
      
    }
    
    if(stihovi[0].equalsIgnoreCase(stihovi[2]) && stihovi[1].equalsIgnoreCase(stihovi[3]) ) return true;
    else return false;
    
  }
  
   
  public boolean parnaRima(String[] strofa) throws RemoteException
  {
    System.out.println("Usao u funckiju parnRima()");
    String[] stihovi = new String[4];
    for(int i=0;i<4;i++)
    {
      String stih = strofa[i];
      
    
      
      stih = stih.toUpperCase();
      int najveciIndeks = 0;
      for(int j=0;j<stih.length();j++)
      {
        if(stih.charAt(j) == 'A' || stih.charAt(j) == 'E' || stih.charAt(j) == 'I' || stih.charAt(j) == 'O'
             || stih.charAt(j) == 'U' || stih.charAt(j) == ' ')
        {
          if(j!= (stih.length()-1)) 
          {
            if(stih.charAt(j) == ' ')najveciIndeks = j+1;
            else najveciIndeks = j;
          }
        }
        
        
      }
      
      stihovi[i] = stih.substring(najveciIndeks,stih.length());
      System.out.println("PARNA RIMA: stihovi["+i+"] " + stihovi[i]);
      
    }
    
    if(stihovi[0].equalsIgnoreCase(stihovi[1]) && stihovi[2].equalsIgnoreCase(stihovi[3]) ) return true;
    else return false;
    
  }
  
  
   public boolean obgrljenaRima(String[] strofa) throws RemoteException
  {
     System.out.println("Usao u funckiju obgrljenaRima()");
     String[] stihovi = new String[4];
    for(int i=0;i<4;i++)
    {
      String stih = strofa[i];
      
    
      
      stih = stih.toUpperCase();
      int najveciIndeks = 0;
      for(int j=0;j<stih.length();j++)
      {
        if(stih.charAt(j) == 'A' || stih.charAt(j) == 'E' || stih.charAt(j) == 'I' || stih.charAt(j) == 'O'
             || stih.charAt(j) == 'U' || stih.charAt(j) == ' ')
        {
          if(j!= (stih.length()-1)) 
          {
            if(stih.charAt(j) == ' ')najveciIndeks = j+1;
            else najveciIndeks = j;
          }
        }
        
        
      }
      
      stihovi[i] = stih.substring(najveciIndeks,stih.length());
      System.out.println("OBGRLJENA RIMA: stihovi["+i+"] " + stihovi[i]);
    }
    
    if(stihovi[0].equalsIgnoreCase(stihovi[3]) && stihovi[1].equalsIgnoreCase(stihovi[2]) ) return true;
    else return false;
    
  }
   
   public String rima(String[] pjesma) throws RemoteException
   {
     System.out.println("Usao u funckiju rima()");
     int brojac = 0;
     int brojStrofe = 1;
     String[] strofa = new String[4];
     String response = new String();
     System.out.println("STIHOVI");;
     for(String s : pjesma)
     {
       System.out.println(s);
       strofa[brojac] = s;
       System.out.println("BROJAC ==== "+brojac);
       if(brojac == 3)
       {
         if(savrsenaRima(strofa))
         {
           response += brojStrofe + ". STROFA: SAVRSENA RIMA";
           response += "\n";
           ++brojStrofe;
         }
         else if(ukrstenaRima(strofa))
         {
           response += brojStrofe + ". STROFA: UKRSTENA RIMA";
           response += "\n";
           ++brojStrofe;
         }
         else if(obgrljenaRima(strofa))
         {
           response += brojStrofe + ". STROFA: OBGRLJENA RIMA";
           response += "\n";
           ++brojStrofe;
         }
         else if(parnaRima(strofa))
         {
           response += brojStrofe + ". STROFA: PARNA RIMA";
           response += "\n";
           ++brojStrofe;
         }
         else 
         {
           response += brojStrofe + ". STROFA: SLOBODAN STIL";
           response += "\n";
           ++brojStrofe;
         }
         
       }
       if (brojac == 3) 
       {
         brojac =0;
         strofa = new String[4];
       }
       else ++brojac;
       
     }
     
     return response;
     
   }
   
   
   public static void main(String[] args)
   {
     
     System.setSecurityManager(new RMISecurityManager());
     
     try
     {
       RimaServer rs = new RimaServer();
        Naming.rebind("//localhost:1099/server",rs);
        
        
        System.out.println("RMI RimaServer started....");
     }
     catch(Exception ex)
     {
       ex.printStackTrace();
     }
     
   }
     
     
   
   
   
}//zatvorena klasa
                                 
        

        