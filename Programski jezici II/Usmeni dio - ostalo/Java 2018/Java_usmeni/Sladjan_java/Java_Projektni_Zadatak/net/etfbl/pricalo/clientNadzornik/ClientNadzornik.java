package net.etfbl.pricalo.clientNadzornik;

import net.etfbl.pricalo.clientNadzornik.InputScannerThread;
import net.etfbl.stanovnik.*;
import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;


public class ClientNadzornik
{
  public static BufferedReader in;
  public static PrintWriter out;
  public static boolean stop = false;
  public static Socket clientSocket;
  public static String serverMsg = "";
  public static Object lock;
  public static InputScannerThread insc;
  public static boolean inputNeeded = false;
  public static ClientThreadNadzornik ref;
  
    static {
    lock = new Object();
  }
  
  
  public ClientNadzornik ()
  {
    ClientThreadNadzornik ctn = new ClientThreadNadzornik();
    
  }
  
  public static void main (String[] args)
  {
    ClientThreadNadzornik ctn = new ClientThreadNadzornik();
    insc = new InputScannerThread();
  }
  
}