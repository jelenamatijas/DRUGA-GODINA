package kriptoAnaliza.server;

import java.lang.String;
import java.net.*;
import java.io.*;


public class ServerThread extends Thread
{
  public Socket sock;
  public InetAddress ip;
  public PrintWriter pw;
  public BufferedReader br;
  
  public ServerThread(Socket s)
  {
    super();
    this.sock = s;
    
    try
    {
      
      br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
       pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    start();
  }
  
  public void run()
  {
    System.out.println("ServerThread started...");
    try
    {

      
      String input = new String();
      
      input = "";
      
      input = br.readLine();
      
      if ("1".equalsIgnoreCase(input))
      {
        input = br.readLine();
        System.out.println(input);
        char temp;
        String cypherString = new String();
        
        for (int i=0;i<input.length();i++)
        {
          temp = input.charAt(i);
          System.out.println(temp);
          if (temp>='A' && temp<='Z')
          {
            temp = (char)('A' + (((int)(temp - 'A') + 13) % 26));
            System.out.println(temp);
            cypherString += temp;
          }
          else if (temp>='a' && temp<='z')
          {
            temp = (char)('a' + (((int)(temp - 'a') + 13) % 26));
            System.out.println(temp);
            cypherString += temp;
          }
        }
        System.out.println(cypherString);
        pw.println(cypherString);
        
      }
      else if("2".equalsIgnoreCase(input))
      {
        File f1 = new File("kriptoAnaliza" + File.separator + "server" + File.separator + "originalText.txt");
        
        if (!f1.exists()) f1.createNewFile();
        
        InputStream ins = sock.getInputStream();
        OutputStream outs = sock.getOutputStream();
        
        byte[] buffer = new byte [1024 * 1024];
        
        int count = 0;
        
        if (!f1.exists()) 
        {
          f1.createNewFile();
          System.out.println("Nema fajla");
        }
        OutputStream orgOut = new FileOutputStream(f1,true);
        System.out.println("Spot 1");
        
        String control = new String();
        control = "";
        
        control = br.readLine();//sa klijentske strane je proslijedjena velicina fajla koji se salje u stringu, ovdje je procitana ta vrijednost
        System.out.println(control);//ispis procitane vrijednosti
        int bc = Integer.valueOf(control);//pretvaranje vrijednosti iz stringa u int
        //pw.println("##SEND##");
        // Postovani profesore, koliko sam ja shvatio situaciju je ovdje. ako se odkomentarise linija iznad sve radi, medjutim ako linija iznad nije u funkciji
        // to jest, ako se ne salje ova kontrolna poruka da klijent moze zapoceti slanje fajla, desava se situacija kao da metoda readLine klase BufferedReader
        // nekako kompromituje stream tako da funkcija read(), u petlji ispod ne moze da ocita bajtove sa strima i dolazi do zastoja programa, kao da klijent 
        // nije nista poslao na stream. U klijentu se pomocu ispisa nekih kontrolnih poruka vidi da on jeste poslao bajtove na stream.
        // Da li nekako ova metoda readLine() pokupi te bajtove?
        // Kako metoda readLine() cita sa streama samo do delimitera koji oznacava kraj stringa, i koji metoda println() sama postavi, mislim da ne bi trebalo da
        // metoda readLine moze nekako pokupiti bajtove sa streama jer su proslijedjeni nakon velicine fajla koja je proslijedjena metodom println().
        // Ipak, i bez navedene linije pw.println("##SEND##") na serverskoj strani i u = readIn.readLine() na klijentskoj, ovo nekad radi ali u vecini slucajeva ne.
        
        do
        {
          
          count = ins.read(buffer);
          System.out.println("Procitao");
          orgOut.write(buffer,0,count);
          orgOut.flush();
          System.out.println("Length = " + f1.length());
        }
        while (f1.length()< bc);
        
        
        System.out.println("Spot 2");
        orgOut.close();
       
        
        BufferedReader ct = new BufferedReader(new FileReader("kriptoAnaliza" + File.separator + "server" + File.separator + "originalText.txt"));
        PrintWriter pc = new PrintWriter(new BufferedWriter(new FileWriter("kriptoAnaliza" + File.separator + "server" + File.separator + "cypherText.txt")),true);
        
        String fromFile = new String();
        
        do
        {
          
          String m = new String();
          fromFile = ct.readLine();
          char ch;
          if (fromFile != null)
          {
            
            
            for (int i=0;i<fromFile.length();i++)
            {
              ch = fromFile.charAt(i);
              System.out.println(ch);
              if (ch>='A' && ch<='Z')
              {
                ch = (char)('A' + (((int)(ch - 'A') + 13) % 26));
                System.out.println(ch);
                m += ch;
              }
              else if (ch>='a' && ch<='z')
              {
                ch = (char)('a' + (((int)(ch - 'a') + 13) % 26));
                System.out.println(ch);
                m += ch;
              }
            }//zatvoren for
            
            pc.println(m);
            
          }//zatvoren if
          
        }while (fromFile != null);
        
        pc.close();
        ct.close();
        
        
        byte[] bufferc = new byte[1024*1024];
        
        InputStream inc = new FileInputStream("kriptoAnaliza" + File.separator + "server" + File.separator + "cypherText.txt");
        
        int countc = 0;
        
        File fct = new File("kriptoAnaliza" + File.separator + "server" + File.separator + "cypherText.txt");
        
        Long len = new Long(fct.length());
        pw.println(len.toString());
        
        while((countc = inc.read(bufferc)) >= 0)
        {
          outs.write(bufferc,0,countc);
          outs.flush();
         
        }
        pw.close();
        br.close();
        inc.close();
        f1.delete();
        fct.delete();
        
        
      }//zatvoren else if "2"
      
      sock.close();
    }//zatvoren try
    
  catch (Exception ex)
  {
    ex.printStackTrace();
  }
  
  }//zatvoren run
  
}//zatvorena klasa
  