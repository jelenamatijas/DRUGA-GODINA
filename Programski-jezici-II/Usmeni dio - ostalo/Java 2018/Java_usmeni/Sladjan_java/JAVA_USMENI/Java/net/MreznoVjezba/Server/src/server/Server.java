/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.net.*;

/**
 *
 * @author Milan
 */
public class Server {

       public static final int TCP_PORT = 9000;

    public static void main(String[] args) {
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
