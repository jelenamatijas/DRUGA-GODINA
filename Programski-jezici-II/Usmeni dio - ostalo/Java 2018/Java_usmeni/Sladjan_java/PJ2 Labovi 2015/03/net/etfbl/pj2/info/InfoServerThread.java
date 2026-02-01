package net.etfbl.pj2.info;

import java.io.*;
import java.net.*;

public class InfoServerThread extends Thread {

    private Socket sock;
    private int value;
    private BufferedReader in;
    private PrintWriter out;

    public InfoServerThread(Socket sock, int value) {
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

    public void run() {
        try {
            //zahtjev je u formatu INFO#grad#username#password
            String request = in.readLine();
            if (request.startsWith("INFO#") && request.split("#").length == 4) {
                String town = request.split("#")[1];
                String user = request.split("#")[2];
                String pass = request.split("#")[3];
                //provjera korisnika na drugom serveru
                InetAddress addr = InetAddress.getByName("127.0.0.1");
                Socket authSock = new Socket(addr, 9001);
                BufferedReader authIn = new BufferedReader(new InputStreamReader(
                        authSock.getInputStream()));
                // inicijalizuj izlazni stream
                PrintWriter authOut = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(
                                        authSock.getOutputStream())), true);
                authOut.println("AUTH#" + user + "#" + pass);
                String auth = authIn.readLine();
                authIn.close();
                authOut.close();
                authSock.close();

                if ("AUTH OK".equals(auth)) {
                    if (InfoServer.towns.contains(town)) {
                        out.println("Info za grad " + town + " je nesto...");
                    } else {
                        out.println("UNKNOWN TOWN");
                    }
                } else if ("AUTH NOK".equals(auth)) {
                    out.println("NOT AUTHORIZED");
                } else {
                    out.println("INVALID REQUEST");
                }
            } else {
                out.println("INVALID REQUEST");
            }

            in.close();
            out.close();
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
