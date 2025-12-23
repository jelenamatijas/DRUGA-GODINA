/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

/**
 *
 * @author Milan
 */

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
    public void run() {
        try {
            //obrada klijentskih zahtjeva 
            /*klijent posalje zahtjev, u vidu neke poruke, server ocita zahtjev, u zavisnosti od 
                         same poruke izvrsi odredjenu akciju, i vrati klijentu odgovor*/
            // procitaj zahtjev
            out.println("Dobro dosli u nasu prodavnicu. Ovo su proizvodi iz nase ponude\n");
            
            String request = in.readLine();
            // odgovori na zahtjev
            /*npr. out.println("(" + value + ")");*/
            // zatvori konekciju
            in.close();
            out.close();
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private Socket sock;
    private int value;
    private BufferedReader in;
    private PrintWriter out;
}
