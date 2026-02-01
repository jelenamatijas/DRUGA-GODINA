package net.etfbl.pj2.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class InfoServer {

    //podesavamo port na kom ce server osluskivati zahtjeve od klijenata

    public static final int TCP_PORT = 9000;
    public static boolean work = true;
    public static ServerSocket ss;
    public static ArrayList<String> towns = new ArrayList<>();

    public static void main(String[] args) {
        towns.add("Banja Luka");
        towns.add("Prijedor");
        towns.add("Bijeljina");
        towns.add("Doboj");
        towns.add("Trebinje");
        //...

        try {
            //ovaj server jednostavno ispisuje koliko je klijenata trenutno povezano na njega...
            int clientCounter = 0;
            // slu�aj zahteve na datom portu
            ss = new ServerSocket(TCP_PORT);
            System.out.println("Info Server running...");
            new InfoServerControl().start();
            while (work) {
                //prihvataj klijente
                Socket sock = ss.accept();
                System.out.println("Client accepted: "
                        + (++clientCounter));
                //startuj nit za svakog klijenta
                InfoServerThread st = new InfoServerThread(sock, clientCounter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

class InfoServerControl extends Thread {

    public InfoServerControl() {
        super();
    }

    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (InfoServer.work) {
            try {
                System.out.print(">");
                String option = in.readLine();
                if ("STOP".equals(option)) {
                    System.out.println("Server is shutting down.");
                    InfoServer.work = false;
                    InfoServer.ss.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
