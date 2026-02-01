package net.etfbl.pricalo.clientRadnik;

import net.etfbl.pricalo.clientRadnik.ClientThreadRadnik;
import net.etfbl.pricalo.clientRadnik.Teleekran;


import java.util.Scanner;
import java.lang.String;
import java.net.*;
import java.io.*;


public class ClientRadnik
{
  public static boolean stop = false;
  public static Socket clientSocket;
  public static BufferedReader in;
  public static PrintWriter out;
  public static String serverMsg = "";
  public static Object lock;
  public static Teleekran tele;
  public static boolean inputNeeded = false;
  public static ClientThreadRadnik ref;
  
  static {
    lock = new Object();
  }
  
  public static void main(String[] args)
  {
    ClientThreadRadnik ctr = new ClientThreadRadnik();
    ref = ctr;
    tele = new Teleekran();
    ctr.start();
    tele.start();
  }//zatvoren main
  
}//zatvorena klasa