package net.etfbl.client.stanovnici;
import java.io.*;
import java.net.*;
import java.util.*;
import net.etfbl.client.interfejsi.UpravljaIzvjestajima;
import net.etfbl.client.*;

public class Nadzornik extends Stanovnik implements UpravljaIzvjestajima {
  
  public Nadzornik() {
    super("Nikola", "Blagojevic", "1801994120014");
    tel = new Teleekran();
    start();
  }
  
  public Nadzornik(String name, String surname, String jmbg) {
    super(name,surname,jmbg);
  }
  
  @Override
  public void run() {
    String request = "";
    String[] info;
    String[] argument;
    Scanner scan = new Scanner(System.in);
    
    // Nadzornik radi dok ne unese EXIT ili dok server ne prekine simulaciju
    while ((!request.equals("EXIT")) && (Client.run)) {
      request = scan.nextLine();
      argument = info = request.split(": ");
      if (info.length >= 2)
        argument = info[1].split(" ");
      
      if ((info[0].equals("SHOWMSG")) && (Client.run))
        showMessages(argument[0], argument[1]);
      
      else if ((info[0].equals("SEARCHKEY")) && (Client.run))
        searchByKeyword(argument);
      
      else if ((info[0].equals("MKREP")) && (Client.run))
        makeReport();
      
      else if ((info[0].equals("SHOWDIR")) && (Client.run))
        showDirectory();
      
      else if ((info[0].equals("SHOWREP")) && (Client.run))
        showReport(info[1]);
      
      else if (info[0].equals("EXIT"))
        System.out.println("Dovidjenja, nadzornice.");
      
      else if (Client.run)
        System.out.println("Nepostojeca naredba.");
    }
  }
  
  
  public String getUser(String jmbg) throws IOException {
    Client.out.println("GETUSER: " + jmbg);
    return Client.in.readLine();
  }
  
  
  public void showMessages(String jmbg, String date) {
    String request = "";
    Scanner scan = new Scanner(System.in);
    
    try {
      Client.out.println("SHOWMSG: " + jmbg + ": " + date);
      
      // Primanje broja fajlova
      int n = Integer.valueOf(Client.in.readLine());
      System.out.println("Dostupno fajlova: " + n);
      System.out.println("Koliko fajlova biste zeljeli preuzeti?");
      
      // Unos broja fajlova za preuzimanje
      int b = scan.nextInt();
      while (b > n) {
        System.out.println("Unesite broj koji je manji ili jednak broju dostupnih fajlova!");
        b = scan.nextInt();
      }
      Client.out.println(b);
      
      // Primanje fajlova
      for (int i = 0; i < b; i++) {
        System.out.println((i + 1) + ". fajl:");
        while (!(request = Client.in.readLine()).equals("END"))
          System.out.println(request);
        System.out.println();
      }
    }
    catch (IOException e) {
      System.out.println("IOException in showMessages()");
    }
  }
  
  public void searchByKeyword(String[] keyword) {
    ArrayList<String> user = new ArrayList<String>();
    ArrayList<String> userJmbg = new ArrayList<String>();
    String request = "";
    String message = "Zbog neadekvatnog ponasanja, vasa plata ce biti umanjena za 100.";
    
    // Slanje svih kljucnih rijeci
    Client.out.println("SEARCHKEY");
    for (String i : keyword)
      Client.out.println(i);
    Client.out.println("END");
    
    // Primanje svih JMBG
    try {
      while (!(request = Client.in.readLine()).equals("END"))
        userJmbg.add(request);
    }
    catch (IOException e) {
      System.out.println("IOException in searchByKeyword() while getting JMBGs!");
    }
    
    // Dobijanje svih korisnickih imena
    try {
      for (int i = 0; i < userJmbg.size(); i++)
        user.add(getUser(userJmbg.get(i)));
    }
    catch (IOException e) {
      System.out.println("IOException in searchByKeyword() while getting usernames!");
    }
    
    // Slanje poruka korisnicima
    try {
      for (int i = 0; i < user.size(); i++) {
        send(user.get(i), message);
        System.out.println(Client.in.readLine());
      }
    }
    catch (IOException e) {
      System.out.println("Exception in searchByKeyword() while getting server response!");
    }
  }
  
  public void send(String user, String message) {
    Client.out.println("@" + user + ": " + message + ": nadzornik");
  }
  
  // Radi
  public void makeReport() {
    String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam condimentum nisi est. Integer ac urna erat. Aliquam nec orci in augue fermentum pulvinar. Maecenas sed accumsan enim. Ut fringilla turpis at dui sodales pretium. Praesent vitae augue purus. Maecenas nec turpis ac risus iaculis maximus id nec nulla. Fusce et risus purus.";
    Client.out.println("MKREP: " + lipsum);
  }
  
  // Radi
  public void showDirectory() {
    String request = "";
    Client.out.println("SHOWDIR");
    try {
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
    catch (IOException e) {
      System.out.println("IOException in showDirectory()");
    }
  }
  
  public void showReport(String name) {
    String request = "";
    Client.out.println("SHOWREP: " + name);
    try {
      request = Client.in.readLine();
      if (request.equals("FALSE"))
        System.out.println("Ne postoji izvjestaj sa tim imenom.");
      else
        System.out.println(Client.in.readLine());
    }
    catch (IOException e) {
      System.out.println("Exception in showReport()");
    }
  }
}