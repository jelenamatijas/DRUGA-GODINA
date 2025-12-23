package server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

    public ServerThread(Socket sock, int value) {
        this.sock = sock;
        this.value = value;
        try {
            // inicijalizuj ulazni stream
            in = new BufferedReader(
                    new InputStreamReader(
                            sock.getInputStream()));
            // inicijalizuj izlazni stream
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    sock.getOutputStream())), true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        start();
    }
    
    //implementiraj run metodu
    @Override
    public void run() {
        try {
            //obrada klijentskih zahtjeva 
            /*klijent posalje zahtjev, u vidu neke poruke, server ocita zahtjev, u zavisnosti od 
                         same poruke izvrsi odredjenu akciju, i vrati klijentu odgovor*/
            // procitaj zahtjev
            while (!login()){
                System.out.println("[Server]: Nespjesna prijava! Pritisnite enter pa pokusajte opet!");
                out.println("Neuspjesna prijava! Pokusajte opet!");
            }
            kviz();
               /*npr. out.println("(" + value + ")");*/
            // zatvori konekciju
          //  System.out.println("[Server]: Kraj");
            out.println("Kraj");
            in.close();
            out.close();
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void kviz(){
        try{
            //BufferedReader fIn = new BufferedReader (new FileReader ("./pitanja.txt"));
            String s,pitanje,tacanOdgovor;
            System.out.println("[Server]: Kviz pocinje! Unesite redni broj tacnog odgovara!");
            out.println("Kviz Pocinje! Unesite redni broj tacnog odgovora! Pritisnite enter da dobijete pitanje;");
            System.out.println("[Korisnik: "+in.readLine());
            do{
                BufferedReader fIn = new BufferedReader (new FileReader ("./pitanja.txt"));
                int skor = 0;
            while ((s = fIn.readLine()) != null){
                pitanje = s.split("%")[0];
                tacanOdgovor  = s.split("%")[1];
                
                System.out.println("[Server]: "+pitanje);
                out.println(pitanje);
                if (tacanOdgovor.equals(in.readLine())) {
                    skor++;
                    System.out.println("[Server]: TCNO!");
                    out.println("TACNO! Vas skor je: "+skor+"");
                    System.out.println("[Korisnik: "+in.readLine());
                }else{
                    System.out.println("[Server]: NETACNO! ");
                    out.println("NETACNO! ");
                    System.out.println("[Korisnik: "+in.readLine());
                }
            }
            System.out.println("[Server]:Vas skor je: "+skor);
            out.println("Vas skor je: "+skor);
            System.out.println("[Korisnik: "+in.readLine());
            System.out.println("[Server]: Da li zelite zaigrati ponovo? [unesite DA ili NE]");
            out.println("Da li zelite zaigrati ponovo? [unesite DA ili NE]");
            
            }while(in.readLine().contains("DA"));
   
            
        }catch (Exception e){
            out.println("Desila se greska! Oprostite!");
        }
    }
    
    private synchronized boolean login(){
        String username,pass,fUsername,fPass;
        System.out.println("[Server]: Unesite korisnicko ime");
        out.println("Unesite korisnicko ime");
        try{
            username = in.readLine();
            System.out.println("[Korisnik]: "+username);
            System.out.println("[Server]: Unesite sifru");
            out.println("Unesite sifru");
            pass = in.readLine();
            System.out.println("[Korisnik]: "+pass);
            
            BufferedReader fIn = new BufferedReader (new FileReader("./user.txt"));
            String s;
            while((s = fIn.readLine()) != null){
                fUsername = s.split("%")[0];
                fPass = s.split("%")[1];
                System.out.println("Testiranje: "+s+
                        "\n username:"+username+"\n"+fUsername+"\n pass: "+pass+"\n"+fPass);
                if (username.equals(fUsername) && pass.equals(fPass)) return true;
            }
            return false;
        }catch(Exception e){
            System.out.println("Greska u prijavi! "+e);
            return false;
        }
        
        
    }
    
  
    private Socket sock;
    private String vremena="";// = "BanjaLuka 18";
    private int value;
    private BufferedReader in;
    private PrintWriter out;
}
