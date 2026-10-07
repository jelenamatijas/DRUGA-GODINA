package net.etfbl.pricalo.agencija;

import java.net.*;
import java.io.*;



public class Agencija
{
  public static boolean stop = false;
  public static Socket clientSocket;
  public static BufferedReader in;
  public static PrintWriter out;
  public static String serverMsg = "";
  public static Object lock;
  
  
  
  
   
  public static void main(String[] args)
  {
    AgencijaThread at = new AgencijaThread();
    
  }
  
}