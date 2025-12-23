import java.io.*;
import java.net.*;

public class TrkaKonjaServerThread extends Thread
{
  public Socket sock;
 
  
  
  
  public TrkaKonjaServerThread(Socket sock)
  {
    this.sock = sock;
  
    start();
  }
  
  
  public void run()
  {
    boolean usernameOk = false;
    try(
        BufferedReader br = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
        )
    {
      
      
      do
      {
        pw.println("##USR##");
      System.out.println("Poslao USR");
      String username = new String();
      
      username = br.readLine();
      
      System.out.println(username);
      synchronized(TrkaKonjaServer.usernames)
      {
        
        if(!TrkaKonjaServer.usernames.contains(username))
        {
          System.out.println("===");
          pw.println("##USROK##");
          System.out.println("USROK");
          
          TrkaKonjaServer.usernames.add(username);
          usernameOk = true;
        }
        else
        {
          pw.println("##USRNOK##");
          System.out.println("NOK");
        }
      }
      
      }while(!usernameOk);
      
      String request = new String();
      
      request = br.readLine();
      
      if ("ZAHTJEV".equalsIgnoreCase(request))
      {
        String response = new String();
        synchronized(TrkaKonjaServer.konji)
        {
        for(int i=0;i<TrkaKonjaServer.konji.size();i++)
        {
         Konj k = TrkaKonjaServer.konji.get(i);
         response += k.getPosition() + k.brojPozicijaDoKraja();
         response += "||";
        }
        
        pw.println(response);
        pw.println("FINISH");
        }
        
      }
      else
      {
        while(!TrkaKonjaServer.finish)
        {
          sleep(1000);
          String response = new String();
        
          synchronized(TrkaKonjaServer.konji)
          {
            for(int i=0;i<TrkaKonjaServer.konji.size();i++)
            {
              Konj st = TrkaKonjaServer.konji.get(i);
              response += st.getPosition() + " BROJ POZICIJA DO KRAJA: "+ st.brojPozicijaDoKraja();
              response += "#";
              
            }
            System.out.println(response);
            pw.println(response);
          }
        }
        pw.println("FINISH");
        
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    try
    {
      this.sock.close();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  
}
            
            
      
      
      