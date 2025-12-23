import java.net.*;
import java.io.*;


public class Server1
{
  public static void main(String[] args)
  {
  
  try
  {
    System.out.println("Server started....");
                       
    ServerSocket ss = new ServerSocket(9000);
    while(true)
    {
    ServerThread st = new ServerThread(ss.accept());
    System.out.println("Client accepted...");
    }
    
  }
  catch(Exception ex)
  {
    ex.printStackTrace();
  }
  }
  
}