import java.net.*;
import java.io.*;


public class Client1
{
  public static void main(String[] args)
  {
    try
    {
      InetAddress ip = InetAddress.getByName("127.0.0.1");
      
      Socket sock = new Socket(ip,9000);
     // BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
      //PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
      //ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
      ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
      
     
      
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
}