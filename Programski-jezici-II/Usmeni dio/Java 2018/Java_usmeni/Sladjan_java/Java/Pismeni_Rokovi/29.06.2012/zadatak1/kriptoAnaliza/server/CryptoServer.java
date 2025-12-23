package kriptoAnaliza.server;

import kriptoAnaliza.server.ServerThread;

import java.io.*;
import java.lang.String;
import java.net.*;

public class CryptoServer
{
  
  public static void main(String[] args)
  {
    int clientCount =0;
    try
    {
      ServerSocket ss = new ServerSocket(9000);
      System.out.println("Server running...");
      while(true)
      {
        
        Socket so = ss.accept();
        ServerThread st = new ServerThread(so);
        System.out.println("Client connected...");
        clientCount++;
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    
  }//zatvoren main
  
}//zatvorena klasa
          
          
      
      