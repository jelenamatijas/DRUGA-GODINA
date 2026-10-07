import java.io.*;
import java.net.*;


public class TimeThread extends Thread
{
  Socket sock;
  BufferedReader br;
  PrintWriter pw;
  String omogucenNastavak;
  EscapeGameServerThread egst;
  public int opcija;
  
  public TimeThread(Socket sock,BufferedReader br,PrintWriter pw,EscapeGameServerThread egst,int opcija)
  {
    this.sock = sock;
    this.br = br;
    this.pw = pw;
    this.egst = egst;
    this.opcija = opcija;
    
    start();
  }
  
  
  public void run()
  {
    
    try
    {
      if(this.opcija == 1)
      {
      sleep(10);
      int brojTacnihOdgovora = 0;
      String odgovor = new String();
      
      pw.println("Kad je poceo II svjetski rat?");
      odgovor = br.readLine();
      
      if("1939".equalsIgnoreCase(odgovor)) ++brojTacnihOdgovora;
      
      pw.println("Kad se desio kosovski boj?");
      odgovor = br.readLine();
      
      if("1389".equalsIgnoreCase(odgovor)) ++brojTacnihOdgovora;
      
      pw.println("Sta je bio Nikola Telsa?");
      odgovor = br.readLine();
      if ("NAUCNIK".equalsIgnoreCase(odgovor)) ++brojTacnihOdgovora;
      
      if(brojTacnihOdgovora == 3) 
      {
        this.egst.omogucenNastavak = true;
        pw.println("USPJESNO ZAVRSENA IGRA 1");
      }
      else
      {
        pw.println("NEUSPJESNO ZAVRSENA IGRA 1");
        
        pw.println("FINISH");
        this.egst.postavioDrugi = true;
        this.br.close();
        this.pw.close();
        this.sock.close();
      }
      }
      else
      {
        System.out.println("Pokrenuti TIMETHREAD opcija 2");
        br.readLine();
        pw.println("OK");
        System.out.println("POSLAO OK");
        this.egst.omogucenNastavak2 = true;
      }
      
    }
    catch(SocketException sx )
    {

    }
    catch(IOException ioex)
    {
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
}
      
      
      
      
      
      