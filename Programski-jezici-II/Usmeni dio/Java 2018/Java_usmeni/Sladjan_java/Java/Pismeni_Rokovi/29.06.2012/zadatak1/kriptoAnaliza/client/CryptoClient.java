package kriptoAnaliza.client;

import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;



public class CryptoClient
{

  
  public static void main (String [] args)
  {
   
    String fd = new String("Atanasijevic");
    String df = new String("Antonijevic");
    System.out.println("Poredjenje: " +fd.compareToIgnoreCase(df));
  Socket sock;
  PrintWriter printOut;
  BufferedReader readIn;
  File f;
  File f1;
  InputStream input = null;
  Scanner scan = new Scanner(System.in);
  OutputStream out;
  InputStream in;
    
    
    
    try
    {
      InetAddress ip = InetAddress.getByName("127.0.0.1");
      sock = new Socket (ip,9000);
      
      f = new File("kriptoAnaliza" + File.separator + "client" + File.separator + "cleanText.txt");
      f1 = new File("kriptoAnaliza" + File.separator + "client" + File.separator + "cypherText.txt");
      if (f1.exists()) f1.delete();
      
      readIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      printOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
      
      System.out.println("Kripto-Analiza pokrenuta...");
      
      System.out.println("Za kriptovanje teksta unosom sa konzole unesite 1");
      System.out.println("Za kriptovanje teskta iz tekstualnog fajla unesite 2");
      
      String keyboard = new String();
      keyboard = scan.nextLine();
      
      if ("1".equalsIgnoreCase(keyboard))
      {
        printOut.println("1");
        
        System.out.println("Unesite tekst za kriptovanje :");
        keyboard = scan.nextLine();
        
        
        
        printOut.println(keyboard);
        
        String c = new String();
        
        c = readIn.readLine();
        
        System.out.println("Kriptovan text je: ");
        System.out.println(c);
        
        
      }
      else if ("2".equalsIgnoreCase(keyboard))
      {
        printOut.println("2");
        
        byte[] buffer = new byte [1024*1024];
        int count = 0;
        
        out = sock.getOutputStream();
        in = sock.getInputStream();
        
        if (f.exists() && f.isFile())
        {
          input = new FileInputStream(f);
          
          Long l = new Long(f.length());
          printOut.println(l.toString());//salje se serveru velicina fajla
          
          String u = new String();
          //u = readIn.readLine(); // Ova linija sluzi samo da se onemoguci slanje bajtova dok ne stigne kontrolna poruka.
          // Postovani profesore, opet sto se tice klijenta, mislim da je problem ovdje.
          // Nakon sto se posalje serveru velicina fajla, bez linije u = readIn.readLine() nema kontrolne poruke nakon koje se zapocinje slanje bajtova, nego se salje odmah.
          // Ako je navedena linija u odkomentarisana, ceka se kontrolna poruka ##SEND## od servera.
          // Ako se testira bez navedene kontrolne poruke, vidi se da klijent posalje bajtove na stream. To sam testirao pomocu poruka "Saljem" i "Poslao".
          while((count = input.read(buffer)) >= 0)
          {
            System.out.println("Saljem");
            out.write(buffer,0,count);
            out.flush(); 
            System.out.println("Poslao");
            
          }
          
          input.close();
          
          //out.close();
          
          File newFile = new File("kriptoAnaliza" + File.separator + "client" + File.separator + "cypherText.txt");
          
          if(!newFile.exists() && !newFile.isFile()) f.createNewFile();
          
          OutputStream newFileOut = new FileOutputStream(newFile);
          
          byte[] newBuffer = new byte [1024*1024];
          
          count = 0;
          
          String control = new String();
          control = "";
          control = readIn.readLine();
          
          int intc = Integer.valueOf(control);
          
          do
          {
            count = in.read(newBuffer);
            newFileOut.write(newBuffer,0,count);
            newFileOut.flush();
            
          }
          while(newFile.length()< intc);
          
          
         newFileOut.close();
         in.close();
         out.close();
        }
        
        
      }//zatvoren if za opciju "2"
      
      else System.out.println("Pogresno izabrana opcija");
      readIn.close();
      
        printOut.close();
        sock.close();
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvoren main
  
  
}//zatvorena klasa
            
      
      
      
  