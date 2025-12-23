package net.etfbl.pricalo.clientPrepravljac;

import net.etfbl.pricalo.clientPrepravljac.ClientPrepravljac;
import net.etfbl.stanovnik.*;
import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;


public class ClientThreadPrepravljac extends Thread
{
  private Socket sock;
  private BufferedReader in;
  private PrintWriter out;
  private HashMap <String,File> temporaryData = new HashMap<String,File>();
  private ArrayList<String> temporaryDataNameList = new ArrayList<String>();
  public static boolean back = false;
  public static boolean logedout = false;
  
  
  public ClientThreadPrepravljac()
  {
     super();
    boolean serverDostupan = false;
    try
    {
      String serverIP = new String();
      //InetAddress ip = InetAddress.getByName("ASUS-PC");
      File f = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "clientPrepravljac" + File.separator + "serverIP.txt");
      
      if (f.exists() && f.isFile())
      {
        BufferedReader readIP = new BufferedReader(new FileReader(f));
        serverIP = readIP.readLine();
        
        InetAddress ip = InetAddress.getByName(serverIP);
        this.sock = new Socket (ip,9000);
        ClientPrepravljac.clientSocket = this.sock;
        if(ip.isReachable(10000)) serverDostupan = true;
        readIP.close();
      }
    }
    catch (UnknownHostException uh)
    {
      System.out.println("Nedostupan host!!!");
    }
    catch (SecurityException se)
    {
      System.out.println("Security manager ne dozvoljava povezivanje");
    }
     catch(IOException ex)
     {
       System.out.println("Desio se IO Exception pri uspostavljanju veze");
     }//zatvoren catch
    
    if(serverDostupan)
    {
      try 
      {
        in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
        
        
        ClientPrepravljac.in = this.in;
        ClientPrepravljac.out = this.out;
        
        
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }//zatvoren catch;
      
    }
    ClientPrepravljac.ref = this;
    start();
  }//zatvoren konstrukor
  
  
  public void run()
  {
    Scanner scan = new Scanner(System.in);
    String response = new String();
    String keyboardInput = new String();
    keyboardInput = "";
    
    
    
    try
    {
      response = readInput();
      if("OK".equalsIgnoreCase(response))
      {
        out.println("PREPRAVLJAC");
        do
        {
          System.out.println("Za preuzimanje fajla za prepravljanje unesite 1");
          System.out.println("Za odjavu unesite LOGOUT.");
          keyboardInput = scan.nextLine();
          
          if ("1".equalsIgnoreCase(keyboardInput)&& !logedout)searchByJMB();
          else if ("LOGOUT".equalsIgnoreCase(keyboardInput) && !logedout) logedout = logout();
          else System.out.println("Pogresno unesena opcija!!!");
        }while(!logedout);
      }
    }
      
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
        
    
  }//zatvorena run metoda
  
  
   public boolean searchByJMB()
  {
    Scanner scan = new Scanner(System.in);
    String keyboardInput = new String();
    String streamInput = new String();
    ArrayList<String> fileList = new ArrayList<String>();
    Random ran = new Random();
    
    out.println("1");
    
    
    System.out.println("Unesite JMB radnika i datum od interesa za pretragu u formatu JMB DATUM(npr. JMB GGGG#MM#DD");
    
    keyboardInput = scan.nextLine();
    
    if (!"BACK".equalsIgnoreCase(keyboardInput))
    {
      out.println(keyboardInput);
    }
    try
    {
      streamInput = readInput();
      
      if ("0".equalsIgnoreCase(streamInput))
      {
        streamInput = readInput();
        if("NOFILES".equalsIgnoreCase(streamInput)) System.out.println("Nije pronadjen nijedan fajl za unesene podatke!");
      }
      else
      {
        int k = 0;
        System.out.println("Broj nadjenih fajlova je " + streamInput);
        k=Integer.valueOf(streamInput);
        streamInput = readInput();
        
        System.out.println("Fajlovi su: ");
        for(int i=0;i<k;i++)
        {
          System.out.println(streamInput.split(" ")[i]);
          fileList.add(streamInput.split(" ")[i]);
        }
        
        
        int numberOfFile = ran.nextInt(k);
       
        
        keyboardInput = fileList.get(numberOfFile);
        out.println(keyboardInput);
        
        
        
          
            String incomingFile = new String();
            String newFileName = new String();
            incomingFile = "";
            incomingFile = readInput();
            newFileName = incomingFile;
            
            File q = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "clientPrepravljac" + File.separator + "tempFile.txt");
            File nq = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "clientPrepravljac" + File.separator + newFileName);
            
            temporaryDataNameList.add(incomingFile);//ove dvije linije koda upisuju svako ime preuzete i novokreirane datoteke u ArrayList-u, a opet
            temporaryData.put(incomingFile,nq);//ime fajla i njegova apsolutna putanja se pomocu fajl objekta upisuje u HashMap-u sve radi 
                                               //lakseg pretrazivanja opcije prikazivanja jednog fajla i kasnijeg brisanja
            temporaryDataNameList.add("temp.txt");
            temporaryData.put("temp.txt",q);
            
            
            PrintWriter printMsgFile = new PrintWriter(new BufferedWriter(new FileWriter(q)),true);
            PrintWriter printModified = new PrintWriter(new BufferedWriter(new FileWriter("net" + File.separator + "etfbl" + File.separator + "pricalo"
                                                                 + File.separator + "clientPrepravljac" + File.separator + incomingFile )),true);
            BufferedReader readFromTable;
            
            int numberOfModifications = 0;
            do
            {
              incomingFile = "";
              incomingFile = readInput();
              System.out.println("");
              
              
              String contentFromTable = new String();
              String modifiedLine = new String();
              modifiedLine = "";
              
              String m = new String();
               m = incomingFile;
              
              
              readFromTable = new BufferedReader(new FileReader("net" + File.separator + "etfbl" + File.separator + "pricalo"
                                                                                + File.separator + "clientPrepravljac" + File.separator + "tableOfWords.txt"));
              modifiedLine = incomingFile;
              if (!"##EOF##".equalsIgnoreCase(incomingFile))
              {
                printMsgFile.println(incomingFile);//upisivanje originalnog sadrzaja u temp.txt fajl
                do
                {
                  contentFromTable = readFromTable.readLine();
                  
                  if (contentFromTable != null)
                  {
                    
                    
                    
                    if (modifiedLine.contains(contentFromTable.split(" ")[0])) numberOfModifications++;
                    
                    modifiedLine = modifiedLine.replaceAll(contentFromTable.split(" ")[0],contentFromTable.split(" ")[1]);//sadrzaj se ovdje presrece i modifikuje u skladu
                    //sa zadatkom i upisuje u fajl istog imena kao preuzeti fajl
                   
                    
                    
                   
                  }
                }while(contentFromTable != null);
                printModified.println(modifiedLine);
              }
             
            
                
            }while (!"##EOF##".equalsIgnoreCase(incomingFile));
            printMsgFile.close();
            printModified.close();
            readFromTable.close();
          
            BufferedReader readModifiedFile = new BufferedReader(new FileReader(nq));
            
           // out.println(newFileName);//proslijedjuje se prvo ime fajla
            String w = new String();
            do
            {
              w = "";
              
              w = readModifiedFile.readLine();
              
              if (w != null)
              {
                out.println(w);
              }
              else out.println("##EOF##");
            }
            while( w != null);
            
            readModifiedFile.close();
            
            Integer nom = new Integer(numberOfModifications);
            
            
            out.println(nom.toString());
      
      }
    }//zatvoren try blok u metodi searchByJMB
     catch(IOException ioex)
     {
       System.out.println("Greska pri citanju sa input streama.");
       ioex.printStackTrace();
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
      
      
    return true;
    
  }//zatvorena metoda searchMod
   
    public boolean logout()
  {
    out.println("LOGOUT");
    
    String response = new String();
    
    response = "";
    try
    {
      response = readInput();
      
      if ("LOGOUTOK".equalsIgnoreCase(response))
      {
        in.close();
        out.close();
        this.sock.close();
        
        int tempFileCounter = temporaryDataNameList.size();
        for (int i=0;i< tempFileCounter;i++)
        {
          String st = new String();
          st = temporaryDataNameList.get(i);
          File f = temporaryData.get(st);
          
          
          if (f.exists() && st.endsWith(".txt"))
          {
            
            temporaryData.remove(st);
            f.delete();
          }
        }
          
        System.out.println("Uspjesno ste se odjavili.");
        
        return true;// vraca istinitu vrijednost za logedout(boolean)
      }
      else
      {
        System.out.println("Greska pri odjavljivanju.");
        back = true;
      }
    }
    catch(IOException ioex)
    {
      System.out.println("Greska pri citanju sa streama pri odjavi.");
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
      
    return false;
      
  }//zatvorena funkcija logout
    
    
    
    
     public String readInput()
  {
        

   
      String s = new String();
      try
      {
    synchronized (ClientPrepravljac.lock)
    {
     
      if (ClientPrepravljac.serverMsg.equals("")) 
      {
       
        ClientPrepravljac.lock.wait();
        //wait();
        //notifyAll();
        
        s = readInput();
      }
      else
      {
       
        s = ClientPrepravljac.serverMsg;
         ClientPrepravljac.serverMsg = "";
         ClientPrepravljac.lock.notifyAll();
     }

    }
      }//zatvoren TRY blok
      catch (Exception ex)
      {
        System.out.println("Izuzetak kod ocitavanja serverMsg-a");
      }
    return s;
  }//zatvoren read
  
  
}//zatvorena klasa