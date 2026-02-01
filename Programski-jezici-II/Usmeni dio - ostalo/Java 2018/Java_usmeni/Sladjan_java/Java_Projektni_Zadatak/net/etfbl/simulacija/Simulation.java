package net.etfbl.simulacija;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.lang.String;
import java.util.Date;
import java.util.Calendar;
import java.lang.Math;
import java.text.SimpleDateFormat;


public class Simulation
{
  public static Socket sock;
  public static PrintWriter out;
  public static BufferedReader in;
  
  
  public static void main (String[] args)
  {
    String serverIP = new String();
    Scanner scan = new Scanner(System.in);
    try
    {
      File f = new File("net" + File.separator + "etfbl" + File.separator + "simulacija" + File.separator + "serverIP.txt");
      
      if (f.exists() && f.isFile())
      {
       
        BufferedReader readIP = new BufferedReader (new FileReader (f));
        
        serverIP = readIP.readLine();
        
          InetAddress ip = InetAddress.getByName(serverIP);
          sock = new Socket(ip,9000);
          
          in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
          out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
          
          readIP.close();
      }
      
      String control = new String();
      control = in.readLine();
      
      
      if ("OK".equalsIgnoreCase(control))
      {
        
        String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("Veza sa serverom uspostavljena.");
        
        out.println("SIMULATION");
        
        System.out.println("Za zaustavljanje simulacije unesite STOP");
        
        String command = new String();
        
        command = scan.nextLine();
        
        if ("STOP".equalsIgnoreCase(command))
        {
          out.println(command);
          
          control = in.readLine();
          int k = Integer.valueOf(control);
          
          control = in.readLine();
          for(int i=0;i<k;i++)
          {
            System.out.println("");
            System.out.println("===================================================");
            String info = control.split("##")[i];
            System.out.println("Ime : " + info.split(" ")[0]);
            System.out.println("Prezime : " + info.split(" ")[1]);
            System.out.println("JMBG : " + info.split(" ")[2]);
            System.out.println("Plata : " + info.split(" ")[3]);
            System.out.println("===================================================");
            System.out.println("");
          }
          
          String time2 = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
          int seconds1 = Integer.valueOf(time.split(":")[2]);
          int seconds2 = Integer.valueOf(time2.split(":")[2]);
          int minutes1 = Integer.valueOf(time.split(":")[1]);
          int minutes2 = Integer.valueOf(time2.split(":")[1]);
          int hours1 = Integer.valueOf(time.split(":")[0]);
          int hours2 = Integer.valueOf(time2.split(":")[0]);
          
          int durationMinutes = Math.abs(minutes2 - minutes1);
          int durationSeconds = Math.abs(seconds2 - seconds1);
          int durationHours = Math.abs(hours2 - hours1);
          
          Integer s = new Integer(durationSeconds);
          Integer m = new Integer(durationMinutes);
          Integer h = new Integer(durationHours);
          
          String simulationDuration = new String();
          
          simulationDuration = h.toString() + " sati : "  + m.toString() + " minuta : " + s.toString() +" sekundi";
          
          System.out.println("Vrijeme trajanja simulacije : " + simulationDuration);
          
          in.close();
          out.close();
          sock.close();
                               
        }
        
        
        
      }
      
    }
    catch(SocketException se)
    {
      System.out.println("Nije uspostavljena veza sa serverom, problem sa socket-om.");
    }
    catch(IOException ioex)
    {
      System.out.println("Problem sa streamom");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvoren main
  
}//zatvorena klasa Simulation
      
      
      
      
      
          