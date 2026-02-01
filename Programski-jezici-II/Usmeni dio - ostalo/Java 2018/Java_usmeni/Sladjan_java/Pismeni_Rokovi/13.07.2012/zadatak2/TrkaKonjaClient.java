import java.net.*;
import java.io.*;
import java.util.Scanner;


public class TrkaKonjaClient
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    try
    {
      InetAddress ip = InetAddress.getByName("127.0.0.1");
      Socket sock = new Socket(ip,9000);
      
      BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
      
      String response = new String();
      
      response = br.readLine();
      
      if("##USR##".equalsIgnoreCase(response))
      {
        System.out.println("Unesite username: ");
        
        String input = new String();
        input = scan.nextLine();
        
        pw.println(input);
        
        
        while(!"##USROK##".equalsIgnoreCase(response = br.readLine()))
        {
          System.out.println("Unesite username: ");
         
           input = scan.nextLine();
        
           pw.println(input);
        }
        
        System.out.println("Za rezultat unesite ZAHTJEV");
        System.out.println("Za pracenje unestite PRACENJE");
        
        
        input = scan.nextLine();
        
        pw.println(input);
        
        
          while(!"FINISH".equalsIgnoreCase(response = br.readLine()))
          {
            System.out.println("======================");
            System.out.println(response.split("#")[0]);
            System.out.println(response.split("#")[1]);
            System.out.println(response.split("#")[2]);
            System.out.println(response.split("#")[3]);
            System.out.println(response.split("#")[4]);
            System.out.println(response.split("#")[5]);
            System.out.println("======================");
            System.out.println("");
          }
        
        
        
        
      }
      else System.out.println("Veza sa serverom nije uspostavljena");
      
      
      br.close();
      pw.close();
      sock.close();
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    
  }
  
  
}
          
          
          
          
          
        