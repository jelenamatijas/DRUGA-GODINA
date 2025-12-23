package Lab09.simple_client_server.client;

import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.Random;


public class ClientThread extends Thread
{
  private String grad;
  private Socket sock;
  
  public ClientThread ()
  {
    super();
    Random ran = new Random();
    int izbor = ran.nextInt(10);
    try
    {
        InetAddress addr = InetAddress.getByName("127.0.0.1");
            // otvori socket prema drugom racunaru
            this.sock = new Socket(addr, 9000);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    switch (izbor){
    case 0 : this.grad = "Banja Luka";
    break;
    case 1 : this.grad = "Brcko";
    break;
    case 2 : this.grad = "Bijeljina";
    break;
    case 3 : this.grad = "Trebinje";
    break;
    case 4 : this.grad = "Prijedor";
    break;
    case 5 : this.grad = "Modrica";
    break;
    case 6 : this.grad = "Samac";
    break;
    case 7 : this.grad = "Doboj";
    break;
    case 8 : this.grad = "Derventa";
    break;
    case 9 : this.grad = "Prnjavor";
    break;
    }
  }
  
  public void run()
  {
    System.out.println("Client pokrenut");
    BufferedReader in;
    PrintWriter out;
    try
    {
      in = new BufferedReader(new InputStreamReader (this.sock.getInputStream()));
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter (this.sock.getOutputStream())));
      out.println(this.grad);
      System.out.println("Temperatura za grad " + this.grad + " je " +in.readLine());
       in.close();
       out.close();
       this.sock.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
   
    
  
}
  
}
  