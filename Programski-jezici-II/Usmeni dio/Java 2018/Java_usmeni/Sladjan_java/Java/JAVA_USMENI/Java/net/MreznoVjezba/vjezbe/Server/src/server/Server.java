/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import java.net.*;
import java.util.ArrayList;
/**
 *
 * @author Milan
 */



public class Server {
    //podesavamo port na kom ce server osluskivati zahtjeve od klijenata
    public static final int TCP_PORT = 9000;
    public static ArrayList<Proizvod> stanje;
    Server(){
     Racunar rac= new Racunar();
        rac.cijena=100;
        rac.naziv="Pentijum";
        rac.konfiguracija="3GB RAM; 3GHz";
        rac.proizvodjac.drzava="Amerika";
        rac.proizvodjac.naziv="Intel";
        rac.sifra=1;
        
        Telefon tel=new Telefon();
        tel.cijena=100;
        tel.naziv="Pentijum";
        tel.konfiguracija="3GB RAM; 3GHz";
        tel.proizvodjac.drzava="Amerika";
        tel.proizvodjac.naziv="Intel";
        tel.sifra=1;
        
        stanje= new ArrayList <>();
        stanje.add(tel);
        stanje.add(rac);
    }

    public static void main(String[] args) {
//        Racunar rac= new Racunar();
//        rac.cijena=100;
//        rac.naziv="Pentijum";
//        rac.konfiguracija="3GB RAM; 3GHz";
//        rac.proizvodjac.drzava="Amerika";
//        rac.proizvodjac.naziv="Intel";
//        rac.sifra=1;
//        
//        Telefon tel=new Telefon();
//        tel.cijena=100;
//        tel.naziv="Pentijum";
//        tel.konfiguracija="3GB RAM; 3GHz";
//        tel.proizvodjac.drzava="Amerika";
//        tel.proizvodjac.naziv="Intel";
//        tel.sifra=1;
//        
//        ArrayList<Proizvod> stanje= new ArrayList <>();
//        stanje.add(tel);
//        stanje.add(rac);
        try {
            //ovaj server jednostavno ispisuje koliko je klijenata trenutno povezano na njega...
            int clientCounter = 0;
            // slušaj zahteve na datom portu
            ServerSocket ss = new ServerSocket(TCP_PORT);
            System.out.println("Server running...");
            while (true) {
                //prihvataj klijente
                Socket sock = ss.accept();
                System.out.println("Client accepted: "
                        + (++clientCounter));
                //startuj nit za svakog klijenta
                ServerThread st = new ServerThread(sock, clientCounter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
