package simple_client_server.server.server1;

import Lab09.simple_client_server.server.*;
import Lab09.simple_client_server.client.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.lang.String;


public class Server
{
  public static void main(String[] args)
  {
    try
    {
      ServerSocket ss = new ServerSocket(9000);
      System.out.println("Server is running!!!");
      int clientCounter = 0;
      while (true)
      {
        Socket serv = ss.accept();
        System.out.println("Client accepted: "
                             + (++clientCounter));
        
        ServerThread st = new ServerThread(serv, clientCounter);
        
        
        st.sleep(5000);
      }
      
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}