import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TelefonskiImenikServer extends UnicastRemoteObject implements TelefonskiImenik,Comparator<String>
{
  public File location = new File("teleImenik.txt");
  
  
  public TelefonskiImenikServer()throws RemoteException {} ;
  
  @Override
  public int compare(String a,String b)
  {
    int result = 0;
    
    if((a.split("#")[1]).compareTo(b.split("#")[1]) < 0) return -1;
    else if ((a.split("#")[1]).compareTo(b.split("#")[1]) > 0) return 1;
    else return 0;
    
  
  }
    
    
  
  public String[] pregledSadrzaja() throws RemoteException
  {
    String[] s = { "Dati folder za pretragu ne postoji!!!"};
    BufferedReader  br;
    try
    {
    if(location.exists())
    {
      br = new BufferedReader(new FileReader(location));
      String[] nizStringova;
      
      String ispis = new String();
      
      ArrayList<String> linije = new ArrayList<String>();
      
      while((ispis = br.readLine()) != null)
      {
        linije.add(ispis);
                   }
      
      nizStringova = new String[linije.size()];
      
      
      for(int i=0;i<linije.size();i++)
      {
        nizStringova[i] = linije.get(i);
      }
      br.close();
      return nizStringova;
      
      
    }
    else
    {
      return s;
    }
    
    
    
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    return null;
    
  }
  
  
  public String pretrazivanjePoBrojuTelefona(String telefon) throws RemoteException
  {
    boolean mark = false;
    if(location.exists() )
    {
      try
      {
        
         BufferedReader br = new BufferedReader(new FileReader(location));
         
         String citac = new String();
         
         while((citac = br.readLine()) != null)
         {
           if(telefon.equalsIgnoreCase(citac.split("#")[3]))
           {
             if(telefon.startsWith("065"))
             {
               return "MOBILNI BROJ || " + "Ime: " + citac.split("#")[0] + " Prezime: " + citac.split("#")[1] + " Adresa: " + citac.split("#")[2] 
                                    + " Broj telefona: " + citac.split("#")[3];
              
             }
             else
             {
               mark = true; 
                return "FIKSNI BROJ || " + "Ime: " + citac.split("#")[0] + " Prezime: " + citac.split("#")[1] + " Adresa: " + citac.split("#")[2] 
                                    + " Broj telefona: " + citac.split("#")[3];
             }
               
           }
         }
         
         if (!mark) return "Dati broj nije pronadjen";
         br.close();
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
    }
    else
    {
      System.out.println("Dati fajl ne postoji");
    }
    return null;
  }
  
  
  public String[] prikazSortiranPoPrezimenu()
  {
    ArrayList<String> lista = new ArrayList<String>();
    try(  BufferedReader br = new BufferedReader(new FileReader(location)); )
    {
     
      
      String s = new String();
      
      while((s=br.readLine()) != null)
      {
        lista.add(s);
      }
      
      String[] array = new String[lista.size()];
      
      Collections.sort(lista,new Comparator<String>(){
         @Override
         public int compare(String a,String b)
         {
           int result = 0;
           
           if((a.split("#")[1]).compareTo(b.split("#")[1]) < 0) return -1;
           else if ((a.split("#")[1]).compareTo(b.split("#")[1]) > 0) return 1;
           else return 0;
           
           
         }
      } );
      
      for(int i=0;i<lista.size();i++) array[i] = lista.get(i);
      
      
      
      return array;
        
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    return null;
    
  }
  
  
  public void dodavanjeUImenik(String podatak) throws RemoteException
  {
    
    try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(location,true)),true);)
    {
      pw.println(podatak);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    System.setSecurityManager(new RMISecurityManager());
    
    try
    {
      TelefonskiImenikServer tis = new TelefonskiImenikServer();
      
      Naming.rebind("//localhost:1099/server",tis);
      
      System.out.println("RMI server started....");
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvoren main
  
  
}//zatvorena klasa
    
    
    