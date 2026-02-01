import java.util.ArrayList;
import java.net.*;

public class TrkaKonjaServer
{
 public static ArrayList<String> usernames = new ArrayList<String>();
 public static ArrayList<Konj> konji = new ArrayList<Konj>();
 public static boolean finish = false;
 
 
 public static void main(String[] args)
 {
   try
   {
     ServerSocket ss = new ServerSocket(9000);
     
     TrkaKonjaSimulation tks = new TrkaKonjaSimulation();
     
     while(true)
     {
       TrkaKonjaServerThread st = new TrkaKonjaServerThread(ss.accept());
       System.out.println("Client accepted...");
     }
   }
   catch(Exception ex)
   {
     ex.printStackTrace();
   }
   
 }
 
 
}