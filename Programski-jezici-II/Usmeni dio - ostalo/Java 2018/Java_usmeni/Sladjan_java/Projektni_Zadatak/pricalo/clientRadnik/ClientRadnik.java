package Projektni_Zadatak.pricalo.clientRadnik;

import Projektni_Zadatak.pricalo.clientRadnik.ClientThreadRadnik;
import Projektni_Zadatak.pricalo.clientRadnik.Teleekran;


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
  
  static {
    lock = new Object();
  }
  
  public static void main(String[] args)
  {
    ClientThreadRadnik ctr = new ClientThreadRadnik();
    tele = new Teleekran();
    ctr.start();
    tele.start();
  }//zatvoren main
  
}//zatvorena klasa