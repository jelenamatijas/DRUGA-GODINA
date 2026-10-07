import java.net.*;
import java.io.*;
import java.util.Scanner;


public class EscapeGameClient
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    boolean omogucenNastavak = false;
    
    try   
    {
      InetAddress ip = InetAddress.getByName("127.0.0.1");
       Socket sock = new Socket(ip,9000);
        BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();
      String response = new String();
      
      System.out.println(br.readLine());
      
      String input = new String();
      
      System.out.println("Unesite username: ");
      input = scan.nextLine();
      
      pw.println(input);
      
      response = br.readLine();
      
      if(!"USROK".equalsIgnoreCase(response)) System.out.println("Neispravan USERNAME");
      else
      {
        pw.println("1");
        do
        {
          response = br.readLine();
          System.out.println(response);
          
          if(!"NEUSPJESNO ZAVRSENA IGRA 1".equalsIgnoreCase(response) && !"USPJESNO ZAVRSENA IGRA 1".equalsIgnoreCase(response) 
               && !"FINISH".equalsIgnoreCase(response) && !"VRIJEME ISTEKLO!!!".equalsIgnoreCase(response))
          {
          System.out.println("Unesite odgovor:");
          input = scan.nextLine();
          pw.println(input);
          }
          
        }
        while(!"FINISH".equalsIgnoreCase(response) && !"USPJESNO ZAVRSENA IGRA 1".equalsIgnoreCase(response));
        
        if(!"FINISH".equalsIgnoreCase(response))
        {
          System.out.println("Ako zelite nastaviti igru unesite 2, ako ne unesite bilo sta drugo");
          input = scan.nextLine();
          
          pw.println(input);
          
          response = br.readLine();
          
         long velicinaFajla = Long.valueOf(response);
         
         pw.println("##SEND##");
         
         File of = new File("opis_client.txt");
         if(!of.exists()) of.createNewFile();
         OutputStream tf = new FileOutputStream("opis_client.txt");
         
         byte[] buffer = new byte [1024*1024];
         int count = 0;
         do
         {
           count = in.read(buffer);
           tf.write(buffer,0,count);
           tf.flush();
         }
         while(of.length() < velicinaFajla);
         tf.close();
         
         System.out.println("Za odgovor prvo unesite ODGOVOR");
         input = scan.nextLine();
         
         if("ODGOVOR".equalsIgnoreCase(input))
         {
           pw.println(input);
           response = br.readLine();
           
           if("OK".equalsIgnoreCase(response))
           {
             System.out.println("Unesite odgovor");
             input = scan.nextLine();
             PrintWriter odg = new PrintWriter(new BufferedWriter(new FileWriter(of,true)),true);
             odg.println(input);
             odg.close();
             InputStream ff = new FileInputStream(of);
             
             pw.println(of.length());
             
             br.readLine();
             
             byte[] buffer1 = new byte[1024*1024];
             
             int count1 = 0;
             
             while((count1 = ff.read(buffer1))>=0)
             {
               out.write(buffer1,0,count1);
               out.flush();
             }
             ff.close();
             System.out.println("POSLAO SAM BAJTOVE");
             
             System.out.println(br.readLine());
             System.out.println(br.readLine());
           }//ako je primljeno OK
           else
           {
             //System.out.println(br.readLine());
             //System.out.println(br.readLine());
           }
         }
         else
           {
             System.out.println(br.readLine());
             System.out.println(br.readLine());
           }
        }
      }//if usrok
      
      
    }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
  }
  
  
}
      
      
      
      
      
      
      
      
      
      