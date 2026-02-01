import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.lang.String;



public class Client 
{
  public static void main(String[] args)
  {
    try
    {
      InetAddress ip = InetAddress.getLocalHost();
      System.out.println(ip);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    ClientThread ct = new ClientThread();
    ct.start();
  }
  
}