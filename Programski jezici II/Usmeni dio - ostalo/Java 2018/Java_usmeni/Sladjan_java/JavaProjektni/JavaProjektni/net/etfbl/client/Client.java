
package net.etfbl.client;
import java.io.*;
import java.net.*;
import java.util.*;
import net.etfbl.client.stanovnici.*;
import net.etfbl.client.agencija.Agencija;

public class Client {
  public static int SERVER_PORT = 1729;
  public static InetAddress ia;
  public static Socket s;
  public static BufferedReader in;
  public static PrintWriter out;
  public static String username;
  public static String password;
  public static ArrayList<String> login = new ArrayList<String>();
  public static boolean run = true;
  public static boolean pause = false;
  public static boolean logged = false;
  public static Stanovnik stanovnik;
  public static int count = 0;
  
  public static void main(String[] args) throws Exception {
    // Client-server protocol
    ia = InetAddress.getByName("127.0.0.1");
    s = new Socket(ia, SERVER_PORT);
    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
    
    Scanner scan = new Scanner(System.in);
    String choice = "";
    update();
    
    while ((!logged) && (!choice.equals("EXIT"))) {
      menu();
      choice = scan.nextLine();
      switch(choice) {
        case "1":
          register();
          break;
        case "2":
          menu2();
          choice = scan.nextLine();
          switch (choice) {
            case "1":
              menu3();
              choice = scan.nextLine();
              switch (choice) {
                case "1":
                  login();
                  break;
                case "2":
                  autologin();
                  break;
                case "0":
                  choice = "EXIT";
                  break;
                default:
                  break;
              }
              break;
            case "2":
              logged = true;
              username = "nadzornik";
              password = "nadzornik";
              out.println("NOLOGIN: Nadzornik");
              stanovnik = new Nadzornik();
              break;
            case "3":
              logged = true;
              username = "prepravljac";
              password = "prepravljac";
              out.println("NOLOGIN: Prepravljac");
              stanovnik = new Prepravljac();
              break;
            case "0":
              choice = "EXIT";
              break;
            default:
              break;
          }
          break;
        case "3":
          uputstvo();
          break;
        case "0":
          choice = "EXIT";
          break;
        default:
          break;
      }
      clear();
    }
    if (stanovnik != null)
      stanovnik.join();
    in.close();
    out.close();
    s.close();
  }
  
  public static void register() throws IOException {
    String request = "";
    stanovnik = new Radnik();
    Scanner scan = new Scanner(System.in);
    
    while (!request.equals("TRUE")) {
      System.out.print("Unesite korisnicko ime (bez razmaka): ");
      username = scan.nextLine();
      out.println("CHECK: " + username);
      request = in.readLine();
      if (request.equals("TRUE"))
        System.out.println("Korisnicko ime je dostupno!");
      else
        System.out.println("Korisnicko ime nije dostupno!");
    }
    System.out.print("Unesite lozinku: ");
    password = scan.nextLine();
    stanovnik.input();
    logged = true;
    String reg = "REG: " + username + "#" + password + "#" + stanovnik;
    out.println(reg);
    System.out.println("Uspjesno ste se registrovali!");
    stanovnik.start();
  }
  
  public static void login() throws IOException {
    String request = "";
    Scanner scan = new Scanner(System.in);
    
    while (!request.contains("TRUE")) {
      System.out.print("Unesite korisnicko ime (bez razmaka): ");
      username = scan.nextLine();
      System.out.print("Unesite lozinku: ");
      password = scan.nextLine();
      out.println("LOG: " + username + "#" + password);
      request = in.readLine();
      if (request.contains("TRUE"))
        System.out.println("Uspjesno ste se prijavili!");
      else
        System.out.println("Korisnicko ime ili lozinka nisu ispravni!");
    }
    String[] info = request.split("#");
    stanovnik = new Radnik(info[3], info[4], info[5], Integer.valueOf(info[6]));
    logged = true;
  }
  
  public static void autologin() throws IOException {
    String request = "";
    Random rand = new Random();
    
    while (!request.contains("TRUE")) {
      int n = rand.nextInt(login.size());
      out.println("LOG: " + login.get(n));
      request = in.readLine();
      if (request.contains("TRUE"))
        username = login.get(n).split("#")[0];
    }
    
    String[] info = request.split("#");
    stanovnik = new Radnik(info[3], info[4], info[5], Integer.valueOf(info[6]));
    logged = true;
  }
  
  public static void update() {
    try {
      String temp;
      ArrayList<String> tempLog = new ArrayList<String>();
      String path = "net" + File.separator + "etfbl" + File.separator + "server" + File.separator + "login.txt";
      BufferedReader br = new BufferedReader(new FileReader(path));
      
      while ((temp = br.readLine()) != null)
        tempLog.add(temp.split("#")[0] + "#" + temp.split("#")[1]);
      
      login = tempLog;
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void menu() {
    System.out.println("1. Registracija");
    System.out.println("2. Prijava");
    System.out.println("3. Uputstvo");
    System.out.println("0. Izlaz");
  }
  
  public static void menu2() {
    System.out.println("1. Radnik");
    System.out.println("2. Nadzornik");
    System.out.println("3. Prepravljac");
    System.out.println("0. Izlaz");
  }
  
  public static void menu3() {
    System.out.println("1. Odredjeni");
    System.out.println("2. Nasumicni");
    System.out.println("0. Izlaz");
  }
    
  public static void uputstvo() {
    System.out.println("\n\n\n");
    System.out.println("Ako zelite radnika:");
    System.out.println("Izaberite odgovorajuce opcije i radnici ce sami slati jedni drugima poruke.");
    System.out.println("\nAko zelite nadzornika:");
    System.out.println("Izaberite odgovarajuce opcije i prijavite se kao nadzornik."); 
    System.out.println("Nadzornik moze koristiti sljedece opcije:");
    System.out.println("Prikaz poruka korisnika za odredjeni datum - SHOWMSG: <jmbg> <dd-mm-gggg>");
    System.out.println("Pretrazivanje poruka po kljucnoj rijeci - SEARCHKEY: <argument1> <argument2> ...");
    System.out.println("Pravljenje novog izvjestaja - MKREP");
    System.out.println("Prikaz direktorijuma sa izvjestajima - SHOWDIR");
    System.out.println("Prikaz odredjenog izvjestaja - SHOWREP: <naziv>");
    System.out.println("\nAko zelite prepravljaca:");
    System.out.println("Izaberite odgovarajuce opcije i prijavite se kao prepravljac."); 
    System.out.println("Prepravljac moze koristiti sljedece opcije:");
    System.out.println("Prepravljanje poruke korisnika za odredjeni datum - SHOWMSG: <jmbg> <dd-mm-gggg>");
    System.out.println("\nPritisnite bilo koji taster da biste nastavili...");
    try {
      System.in.read();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void clear() {
    String res = "";
    for (int i = 0; i < 25; i++)
      res += "\n";
    System.out.println(res);
  }
}