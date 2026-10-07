package net.etfbl.pricalo.server;



import net.etfbl.stanovnik.*;
import net.etfbl.pricalo.server.ServerThread;
import java.lang.String;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;




public class Server 
{
  public static HashMap<String, Radnik> radniciRegistar = new HashMap<>();
  public static HashMap<String, Socket> radniciSocket = new HashMap<>();
  public static HashMap<String, ServerThread> aktivniRadnici = new HashMap<>();
  public static ArrayList<ServerThread> clientList = new ArrayList<ServerThread>();
  public static ArrayList<String> onlineClientsList = new ArrayList<String>();
  public static PrintWriter out;
  public static BufferedReader in;
  public static int clientCounter = 0;
  public static int onlineClients = 1;
  public static Object regLock;
  public static boolean stop = false;
  
  
  static
  {
    regLock = new Object();
  }
  
  
  public static void main (String[] args)
  {
    readClientBase();
    boolean enterLoop = true;
    
    
   
    
  // System.out.println((radniciRegistar.get("source#morfium")).toString());//ZASTO??
    try
    {
      int v = 0;
       
      ServerSocket ss = new ServerSocket(9000);
      System.out.println("Server je pokrenut!");
      
      
      
      
      
      while (!stop)
      {
       
        Socket clientSocket = ss.accept();
        System.out.println("Klijent " + (clientCounter + 1) + " je povezan");
        clientCounter++;
        
        clientList.add(new ServerThread(clientSocket));
        //ServerThread str = new ServerThread(clientSocket);
       if (stop) System.out.println("Server se gasi.");
      }
       
    }//zatvoren TRY blok
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    try
    {
      for (int i=0;i< clientList.size();i++) clientList.get(i).join();
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
    System.out.println("Velicina je " + radniciRegistar.size());
    saveClientBase();
//    synchronized (clientCounter)
//    {
//      while(clientCounter != 0);
//    
//      if (clientCounter == 0) 
//      {
//        System.out.println("Velicina je "+ radniciRegistar.size());
//        saveClientBase();
//      }
//    }
  }//zatvoren main
  
  
  public static int koJePovezan(Socket sock)
  {
     String s = new String();
    try
    {
      BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
       //in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
       //out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
     
      
      out.println("KO SI");
      
      s = in.readLine();
      in.close();
      out.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
      
      if (s.equalsIgnoreCase("RADNIK")) return 1;
      else if (s.equalsIgnoreCase("NADZORNIK")) return 2;
      
      return 3;
  }//zatvorena koJePovezan
    


public synchronized static void saveClientBase ()
{
  File f;
  

  
  try
  {
    f = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "server" + File.separator+ "base");
    
   
    FileOutputStream foutRegistar;
    ObjectOutputStream outRegistar;
    
    
    if (f.exists() && f.isDirectory())
    {
      
      
      foutRegistar = new FileOutputStream(f+File.separator+"RadniciBase.base");
      outRegistar = new ObjectOutputStream(foutRegistar);
      outRegistar.writeObject(radniciRegistar);
      System.out.println(radniciRegistar.size());
    }
    else
    {
      f.mkdir();
      
      
      foutRegistar = new FileOutputStream(f+File.separator+"RadniciBase.base");
      outRegistar = new ObjectOutputStream(foutRegistar);
      outRegistar.writeObject(radniciRegistar);
      System.out.println(radniciRegistar.size());
    }
    outRegistar.close();
    foutRegistar.close();
  }
  catch(Exception ex)
  {
    System.out.println("Izuzetak pri upisivanjima u bazu.");
    ex.printStackTrace();
  }
}//zatvorena metoda saveClientBase


public synchronized static void readClientBase()
{
  File f;
  
  FileInputStream fis;
  ObjectInputStream ois;
  
  try
  {
     f = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "server" + File.separator+ "base" + File.separator + "RadniciBase.base");
     
     if (f.exists() && f.isFile())
     {
       fis = new FileInputStream (f);
       ois = new ObjectInputStream(fis);
       
       HashMap<String, Radnik> temp;
       
       temp = (HashMap<String,Radnik>) ois.readObject();
       radniciRegistar = temp;
       ois.close();
       fis.close();
       
       System.out.println("Registar radnika je uspjesno ucitan.");
       
       
       
     }
     
     else
     {
       System.out.println("Registar radnika nije uspjesno ucitan.");
     }
  }
  catch (Exception ex)
  {
    System.out.println("Greska pri ucitavanju registra radnika");
    ex.printStackTrace();
  }
  
}//zatvorena metoda readClientBase


}//zatvorena klasa