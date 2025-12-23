package net.etfbl.pj2.info;


import java.net.*;
import java.util.HashMap;

public class AuthServer {
    //podesavamo port na kom ce server osluskivati zahtjeve od klijenata
    public static final int TCP_PORT = 9001;
    public static HashMap<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        users.put("java", "123456");
        users.put("test", "test");
        //...
        
        try {
            //ovaj server jednostavno ispisuje koliko je klijenata trenutno povezano na njega...
            int clientCounter = 0;
            // slu�aj zahteve na datom portu
            ServerSocket ss = new ServerSocket(TCP_PORT);
            System.out.println("Auth Server running...");
            while (true) {
                //prihvataj klijente
                Socket sock = ss.accept();
                System.out.println("Client accepted: "
                        + (++clientCounter));
                //startuj nit za svakog klijenta
                AuthServerThread st = new AuthServerThread(sock, clientCounter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
