package com.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class FTPServerThread extends Thread {
 public FTPServerThread(Socket sock, int value) {
  this.sock = sock;
  // podesavanje korisnika
  User k1 = new User();
  k1.setUsername("test");
  k1.setPassword("test");
  User k2 = new User();
  k2.setUsername("student");
  k2.setPassword("student");
  korisnici.add(k1);
  korisnici.add(k2);
  try {
   // inicijalizuj ulazni stream
   in = new BufferedReader(
     new InputStreamReader(sock.getInputStream()));
   // inicijalizuj izlazni stream
   out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
     sock.getOutputStream())), true);
  } catch (Exception ex) {
   ex.printStackTrace();
  }
  start();
 }

 // implementiraj run metodu
 public void run() {
  try {
   // obrada klijentskih zahtjeva
   /*
    * klijent posalje zahtjev, u vidu neke poruke, server ocita
    * zahtjev, u zavisnosti od same poruke izvrsi odredjenu akciju, i
    * vrati klijentu odgovor
    */
   // procitaj zahtjev, da se uspostavi komunikacija

   String clientInput;
   String serverOutput;
   while ((clientInput = in.readLine()) != null) {
    if (clientInput.contains("USER")) {
     serverOutput = FTPUtilities.checkUser(clientInput, korisnici);
     out.println(serverOutput);
    } else if (clientInput.equals("LIST")) {
     System.out.println("LIst od klijenta");
     FTPUtilities.listaFajlova(".");
     serverOutput = FTPUtilities.getListaDokumenata();
     System.out.println(serverOutput);
     out.println(serverOutput);
    } else if (clientInput.equals("QUIT")) {
     System.out.println("Klijent odjavljen!");
     out.println("231");
    }else if(clientInput.startsWith("MKD")){
     System.out.println("Kreiranje direktorijuma");
     String tmp[]=clientInput.split(" ");
     String msg=FTPUtilities.kreirajFolder(tmp[1]);
     out.println(msg);
    }else if(clientInput.startsWith("RETR")){
     System.out.println("Citanje fajla");
     String tmp[]=clientInput.split(" ");
     /*ako sadrzaj saljemo kao String - String msg=FTPUtilities.ocitajFajl(tmp[1]);
     out.println(msg);*/
     File zaCitanje=new File(tmp[1]);
     if(!zaCitanje.exists())
      out.println("550");
     else
     {
      //ocitavamo duzinu fajla i saljemo je klijentu
      out.println(zaCitanje.length());
      //kreiramo citac za citanje sadrzaja kao niza byte-a
      InputStream isr=new FileInputStream(zaCitanje);
      //definisemo buffer za citanje ogranicene velicine, npr. 2MB
      byte[] buffer=new byte[2*1024*1024];
      int ocitano=0, kolikoJeOstalo=0;
      //kreiramo stream za slanje byte-a klijentu
      OutputStream os=sock.getOutputStream();
      System.out.println("Pocetak ocitavanja i slanja fajla");
      while((ocitano=isr.read(buffer))>0){
       os.write(buffer);
       kolikoJeOstalo+=ocitano;
       System.out.println("Ostalo je jos "+(zaCitanje.length()-kolikoJeOstalo));
      }
      System.out.println("Kraj ocitavanja i slanja fajla");
      os.close();
      isr.close();
     }
    }
    else {
     out.println("POGRESNA KOMANDA");
    }
    // odgovori na zahtjev
    /* npr. out.println("(" + value + ")"); */
    // zatvori konekciju
   }
   in.close();
   out.close();
   sock.close();
  }catch(SocketException se){
   System.out.println("Odjavio se klijent...");
  }
  catch (Exception ex) {
   ex.printStackTrace();
  }
 }

 private static ArrayList<User> korisnici = new ArrayList<User>();
 private Socket sock;
 private BufferedReader in;
 private PrintWriter out;
}
