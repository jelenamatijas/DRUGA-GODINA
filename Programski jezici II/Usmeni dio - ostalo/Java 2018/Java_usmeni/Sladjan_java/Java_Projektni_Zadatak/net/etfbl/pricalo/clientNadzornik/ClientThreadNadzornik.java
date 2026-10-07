package net.etfbl.pricalo.clientNadzornik;


import net.etfbl.stanovnik.*;

import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.ArrayList;


public class ClientThreadNadzornik extends Thread
{
  private Socket sock;
  private BufferedReader in;
  private PrintWriter out;
  private HashMap <String,File> temporaryData = new HashMap<String,File>();
  private ArrayList<String> temporaryDataNameList = new ArrayList<String>();
  public static boolean back = false;
  public static boolean logedout = false;
  
  
  public ClientThreadNadzornik()
  {
     super();
    boolean serverDostupan = false;
    try
    {
      String serverIP = new String();
      //InetAddress ip = InetAddress.getByName("ASUS-PC");
      File f = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "clientNadzornik" + File.separator + "serverIP.txt");
      
      if (f.exists() && f.isFile())
      {
        BufferedReader readIP = new BufferedReader(new FileReader(f));
        serverIP = readIP.readLine();
        
        InetAddress ip = InetAddress.getByName(serverIP);
        this.sock = new Socket (ip,9000);
        ClientNadzornik.clientSocket = this.sock;
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
        
        
        ClientNadzornik.in = this.in;
        ClientNadzornik.out = this.out;
        
        
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }//zatvoren catch;
      
    }
    ClientNadzornik.ref = this;
    start();
  }//zatvoren konstrukor
  
  public void run()
  {
    Scanner scan = new Scanner(System.in);
    
    String keyboardInput = new String();
    
    try
    {
      if (("OK").equalsIgnoreCase(readInput()))
      {
        System.out.println("Veza sa serverom uspostavljena");
        out.println("NADZORNIK");
        
        do
        {
          System.out.println("OPCIJE:");
          System.out.println("Za pregled poruka radnika unesite 1");
          System.out.println("Za pretragu fajlova po kljucnim rijecima unesite 2");
          System.out.println("Za prikaz poruka radnika iz preuzetih fajlova unesite 3");
          System.out.println("Za pravljenje novog izvjestaja unesite 4");
          System.out.println("Za citanje izvjestaja unesite 5");
          System.out.println("Za slanje poruke prepravljacu unesite 6");
          System.out.println("Za odjavu unesite LOGOUT");
          
          keyboardInput = scan.nextLine();
          
          if("1".equalsIgnoreCase(keyboardInput) && !logedout)searchByJMB();
          else if ("2".equalsIgnoreCase(keyboardInput) && !logedout)searchByKeyword();
          else if ("3".equalsIgnoreCase(keyboardInput) && !logedout)messageReview();
          else if ("4".equalsIgnoreCase(keyboardInput) && !logedout)makeReport();
          else if ("5".equalsIgnoreCase(keyboardInput) && !logedout)readReport();
          else if ("6".equalsIgnoreCase(keyboardInput) && !logedout)sendMsg();
          else if ("LOGOUT".equalsIgnoreCase(keyboardInput) && !logedout) logedout = logout();
          else System.out.println("Nepravilno unesena opcija!!!");
        }
        while(!"1".equalsIgnoreCase(keyboardInput) && !"2".equalsIgnoreCase(keyboardInput) && !"LOGOUT".equalsIgnoreCase(keyboardInput) 
                 && !"3".equalsIgnoreCase(keyboardInput) && !"4".equalsIgnoreCase(keyboardInput) && !"5".equalsIgnoreCase(keyboardInput) || !logedout);
        
      }//zatvoren if(OK)
      else System.out.println("Veza sa serverom nije uspostavljena, restartujte aplikaciju.");
      
    }//zatvoren try blok
   
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
        for(int i=0;i<k;i++) System.out.println(streamInput.split(" ")[i]);
        System.out.println("Unesite broj fajlova za preuzimanje: ");
        keyboardInput = scan.nextLine();
        out.println(keyboardInput);
        if (Integer.valueOf(keyboardInput) != 0)
        {
          for (int i=0;i<Integer.valueOf(keyboardInput);i++)
          {
            String incomingFile = new String();
            incomingFile = "";
            incomingFile = readInput();
            File q = new File("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "clientNadzornik" + File.separator + incomingFile);
            
            temporaryDataNameList.add(incomingFile);//ove dvije linije koda upisuju svako ime preuzete i novokreirane datoteke u ArrayList-u, a opet
            temporaryData.put(incomingFile,q);//ime fajla i njegova apsolutna putanja se pomocu fajl objekta upisuje u HashMap-u sve radi 
                                               //lakseg pretrazivanja opcije prikazivanja jednog fajla i kasnijeg brisanja
            
            
            PrintWriter printMsgFile = new PrintWriter(new BufferedWriter(new FileWriter("net" + File.separator + "etfbl" + File.separator + "pricalo"
                                                                 + File.separator + "clientNadzornik" + File.separator + incomingFile )),true);
            do
            {
              incomingFile = readInput();
              if (!"##EOF##".equalsIgnoreCase(incomingFile))printMsgFile.println(incomingFile);
            }while (!"##EOF##".equalsIgnoreCase(incomingFile));
            printMsgFile.close();
          }//zatvoren for za upis
        }//zatvoren if za provjeru broja fajlova za preuzimanje
            
      }
    }//zatvoren try blok u metodi searchByJMB
     catch(IOException ioex)
     {
       System.out.println("Greska pri citanju sa input streama.");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
      
      
    return true;
    
  }//zatvorena metoda searchByJMB
  
  public synchronized void messageReview()
  {
     System.out.println("Za pregled poruka iz jednog fajla unesite redni broj fajla koji ce biti prikazani u formatu, REDNI_BROJ IME FAJLA");
    Scanner scan = new Scanner(System.in);
    try
    {
      if (temporaryDataNameList.size()>0)
      {
        
        for(int i=0;i<temporaryDataNameList.size();i++) System.out.println((i+1) + ". " + temporaryDataNameList.get(i));
        String keyboardInput = new String();
        
        
        keyboardInput = scan.nextLine();
        
        int input = Integer.valueOf(keyboardInput);
        
        File f = temporaryData.get(temporaryDataNameList.get(input-1));
        
        if (f.exists())
        {
          System.out.println("");
          System.out.println("==============================================================");
          System.out.println("Sadrzaj fajla " + temporaryDataNameList.get(input-1));
          BufferedReader readFile = new BufferedReader(new FileReader(f));
          String str = new String();
          str = "";
          do
          {
            str = readFile.readLine();
            if (str != null) System.out.println(str);
          }
          while (str != null);
          readFile.close();//zatvoren reader fajla
        }
        System.out.println("");
        System.out.println("==============================================================");
        System.out.println("");
      }
      else System.out.println("Nema preuzetih fajlova sa porukama");
    }
    catch (IOException ioex)
    {
      System.out.println("Greska pri citanju iz fajla u metodi reviewMsg.");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
        
  }
  
  public void searchByKeyword()
  {
    String keywords = new String();
    
    do
    {
    System.out.println("Pretragu kljucnih rijeci unijetu u formatu BROJ_KLJUCNIH_RIJECI KLJUCNA_RIJEC KLJUCNA_RIJEC...");
    
    Scanner scan = new Scanner(System.in);
    
    
    keywords = scan.nextLine();
    if(keywords.contains(" "))
    {
      out.println("2");
      out.println(keywords);
      
      String response = new String();
      
      try
      {
        
        response = readInput();
        
        
        if ("FILESFOUND".equalsIgnoreCase(response))
        {
          response = readInput();
          int numberOfSuspects = Integer.valueOf(response);
          
          System.out.println("Broj nadjenih radnika za date kljucne rijeci je: " + numberOfSuspects);
          
          response = readInput();
          
          for(int i=0;i<numberOfSuspects;i++)
          {
            System.out.println((i+1) + ". " + response.split(" ")[i]);
          }
          
          System.out.println("");
          System.out.println("");
        }
        else
        {
          System.out.println("Nema snimljenih razgovora radnika za pretragu.");
        }
        
      }
     
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    }while(!keywords.contains(" ") && !"BACK".equalsIgnoreCase(keywords));
  }//zatvorena funkcija searchByKeyword
  
  
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
        System.out.println("U arraylisiti ima " + temporaryDataNameList.size());
        int tempFileCounter = temporaryDataNameList.size();
        for (int i=0;i< tempFileCounter;i++)
        {
          String st = new String();
          st = temporaryDataNameList.get(i);
          File f = temporaryData.get(st);
          System.out.println("F.exists = "+ f.exists());
          System.out.println("st.endsWith = " + st.endsWith(".txt"));
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
      
  
  public void makeReport()
  {
    Scanner scan = new Scanner(System.in);
    out.println("3");
    
    System.out.println("Unesite sadrzaj izvjestaja");
    
    String keyboardInput = new String();
    
    keyboardInput = scan.nextLine();
    
    out.println(keyboardInput);
  }
  
  public void readReport()
  {
    out.println("4");
    
    String input = new String();
    String keyboardInput = new String();
    Scanner scan = new Scanner(System.in);
    
    try
    {
      input = readInput();
      
      
      if ("FILESFOUND".equalsIgnoreCase(input))
      {
        input = readInput();
        int k = Integer.valueOf(input);
        input = "";
        
        input = readInput();
        String files = input;
        
        for (int i=0;i<k;i++) System.out.println((i+1) + ". " + files.split(" ")[i]);
        
        System.out.println("Za preuzimanje sadrzaja odredjenog izvjestaja unesite redni broj fajla.");
        
        keyboardInput = scan.nextLine();
        
        k=0;
        k=Integer.valueOf(keyboardInput);
        k -= 1;
        
        Integer m = new Integer(k);
        
        out.println(m.toString());
        
        input = readInput();
        
        System.out.println("Sadrzaj izvjestaja: " + input);
      }
      else
      {
        System.out.println("Nema kreiranih sadrzaja");
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvorena metoda readReport
  
  
   public void sendMsg()
   {
     out.println("5");
     
     Scanner scan = new Scanner(System.in);
     
     String s = new String();
     System.out.println("Poruku prepravljacu unesite u formatu MSG#PREPRAVLJAC#PORUKA");
     
     s = scan.nextLine();
     out.println(s);
     
     String control = new String();
     
     control = readInput();
     
     if ("SENT".equalsIgnoreCase(control));
     else System.out.println("Korisnik " + s.split("#")[1] + " trenutno nije dostupan.");
   }
  
  
   public String readInput()
  {
      String s = new String();
      try
      {
    synchronized (ClientNadzornik.lock)
    {
     
      if (ClientNadzornik.serverMsg.equals("")) 
      {
       
        ClientNadzornik.lock.wait();
        //wait();
        //notifyAll();
        
        s = readInput();
      }
      else
      {
       
        s = ClientNadzornik.serverMsg;
         ClientNadzornik.serverMsg = "";
         ClientNadzornik.lock.notifyAll();
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