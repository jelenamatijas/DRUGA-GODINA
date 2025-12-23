import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EscapeGameServerThread extends Thread
{
  public Socket sock;
  public boolean logedin = false;
  public boolean omogucenNastavak = false;
  public boolean omogucenNastavak2 = false;
  public boolean postavioDrugi = false;

  
  
  
  public EscapeGameServerThread (Socket sock)
  {
    this.sock = sock;
    start();
  }
  
  public void run()
  {
   
    boolean usrok = true;
    
    
    try( BufferedReader br = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
         PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
         InputStream in = this.sock.getInputStream();
         OutputStream out = this.sock.getOutputStream();
        )
    {//pocetak try bloka
      
      pw.println("USR");
      
      String request = new String();
      
      request = br.readLine();
      
      synchronized(EscapeGameServer.usernames)
      {
        for(int i=0;i<EscapeGameServer.usernames.size();i++)
        {
          if(request.equalsIgnoreCase(EscapeGameServer.usernames.get(i)))
          {
            pw.println("USRNOK");
            usrok = false;
            break;
          }
        }
        
        if(usrok)
        {
          pw.println("USROK");
          
          EscapeGameServer.usernames.add(request);
          logedin = true;
          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usernames.ser"));
          oos.writeObject(EscapeGameServer.usernames);
          oos.close();
        }
        
      }
      
      if(logedin)
      {
        String opcija = new String();
        opcija = br.readLine();
        if("1".equalsIgnoreCase(opcija))
        {
          TimeThread tt = new TimeThread(this.sock,br,pw,this,1);
          
          
          
          int brojac = 0;
          while(brojac < 20 && !omogucenNastavak)
          {
            sleep(1000);
            ++brojac;
          }
          
          
          
         if(!omogucenNastavak && !postavioDrugi) 
         {
           pw.println("NEUSPJESNO ZAVRSENA IGRA 1"); 
           pw.println("VRIJEME ISTEKLO!!!");
           pw.println("FINISH");
           br.close();
           pw.close();
          
         }
          else
          {
            opcija = br.readLine();//bira se da li ce igrac nastaviti igru;
            
            if("2".equalsIgnoreCase(opcija))
            {
              File pp = new File("opis.txt");
              InputStream ff = new FileInputStream("opis.txt");
              
              int brojKaraktera = 0;
              
              byte[] buffer = new byte[1024*1024];
              
             int count = 0;
             
             pw.println(pp.length());
             br.readLine();//pocinje se slanje tek kad se dobije dozvola
             
             while((count = ff.read(buffer)) >=0)
             {
               out.write(buffer,0,count);
               out.flush();
             }
             
             BufferedReader rd = new BufferedReader(new FileReader("opis.txt"));
             int zbir = 0;
             String line = new String();
             while((line = rd.readLine()) != null)
             {
               
               zbir += line.length();
             }
              System.out.println("Dosao do SLEEPANJA");
              TimeThread tt1 =  new TimeThread(this.sock,br,pw,this,2);
             int brojac2 = 0;
             while(brojac2 < zbir && !omogucenNastavak2)
             {
               sleep(1000);
             }
             
             
             
             
             tt1.interrupt();
             
             if(!omogucenNastavak2)
             {
               pw.println("NEUSPJESAN ZAVRSETAK IGRE 2");
               pw.println("VRIJEME ISTEKLO!!!");
               br.close();
               pw.close();
             }
             else
             {
               System.out.println("OMOGUCEN Nastavak2");
               String sk = br.readLine();
               
               long velicinaFajla = Long.valueOf(sk);
               
               pw.println("##SEND##");
               File newFile = new File("rjesenje.txt");
               
               OutputStream tf = new FileOutputStream(newFile);
               
               byte[] buffer1 = new byte[1024 * 1024];
               int count1 = 0;
               System.out.println("Velicina bajtova koja se prima: " + velicinaFajla);
               //InputStream in1 = this.sock.getInputStream();
               do
               {
                 count1 = in.read(buffer1);
                 tf.write(buffer1,0,count1);
                 tf.flush();
               }
               while(newFile.length() < velicinaFajla);
               
               tf.close();
               System.out.println("Upisao sam fajl");
               
               BufferedReader rr = new BufferedReader(new FileReader(newFile));
               
               line = new String();
               String odgovor = new String();
               
               while((line = rr.readLine()) != null)
               {
                 odgovor = line;
               }
               rr.close();
               System.out.println("odgovor je: " + odgovor);
               
               if("PRAVO".equalsIgnoreCase(odgovor))
               {
                 pw.println("USPJESAN ZAVRSETAK IGRE 2");
                 pw.println("FINISH");
               }
               else
               {
                 pw.println("NEUUSPJESAN ZAVRSETAK IGRE 2");
                 pw.println("FINISH");
               }
               
             }
            
            }
            else//ako se poslije zavrsetka igre nije htjelo nastavaiti sa igrom 2
              
            {
              
            }
            
          }
               
        }//zatvoren if "1"
          
      }//zatvoren if logedin
      
       
        this.sock.close();
      
    }//zatvoren try blok
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvorena metoda run
  
}//zatvorena klasa
          
    
    
      