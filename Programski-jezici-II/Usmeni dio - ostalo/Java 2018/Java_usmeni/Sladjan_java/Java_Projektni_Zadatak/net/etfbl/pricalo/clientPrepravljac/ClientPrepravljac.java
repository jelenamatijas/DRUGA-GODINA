package net.etfbl.pricalo.clientPrepravljac;

import java.net.*;
import java.io.*;
import net.etfbl.pricalo.clientPrepravljac.ClientThreadPrepravljac;


public class ClientPrepravljac
{
  public static boolean stop = false;
  public static Socket clientSocket;
  public static BufferedReader in;
  public static PrintWriter out;
  public static String serverMsg = "";
  public static Object lock;
  public static Teleekran tele;
  public static boolean inputNeeded = false;
  public static ClientThreadPrepravljac ref;
  
  
   static {
    lock = new Object();
  }
   
  public static void main(String[] args)
  {
    ClientThreadPrepravljac ctp = new ClientThreadPrepravljac();
    Teleekran tele = new Teleekran();
  }
  
}