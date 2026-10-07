package net.etfbl.client.agencija;
import java.io.*;
import java.net.*;
import java.util.*;
import net.etfbl.client.interfejsi.UpravljaIzvjestajima;
import net.etfbl.client.Client;

public class Agencija {
  private String name;
  
  public Agencija(String name) {
    this.name = name;
  }
  
  // Radi
  public void makeReport() {
    String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam condimentum nisi est. Integer ac urna erat. Aliquam nec orci in augue fermentum pulvinar. Maecenas sed accumsan enim. Ut fringilla turpis at dui sodales pretium. Praesent vitae augue purus. Maecenas nec turpis ac risus iaculis maximus id nec nulla. Fusce et risus purus.";
    Client.out.println("MKREP: " + lipsum);
  }
  
  // Radi
  public void showDirectory() throws Exception {
    String request = "";
    Client.out.println("SHOWDIR");
    request = Client.in.readLine();
    if (request.equals("FALSE")) {
      System.out.println("Ne postoji nijedan izvjestaj.");
    }
    else {
      request = Client.in.readLine();
      while (!request.equals("END")) {
        System.out.println(request);
        request = Client.in.readLine();
      }
    }
  }
  
  // Radi
  public void showReport(String name) throws Exception {
    String request = "";
    Client.out.println("SHOWREP: " + name);
    request = Client.in.readLine();
    if (request.equals("FALSE"))
      System.out.println("Ne postoji izvjestaj sa tim imenom.");
    else
      System.out.println(Client.in.readLine());
  }
}