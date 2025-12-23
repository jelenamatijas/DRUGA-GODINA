package net.etfbl.pj2.server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

    private Socket sock;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ServerThread(Socket sock) {
        this.sock = sock;
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            sock.getInputStream()));
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    sock.getOutputStream())), true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * server treba da obradjuje zahtjeve: * LOGIN#username - vraca OK ako je
     * login prosao ili NOK ako je username zauzet * USERS - vraca listu
     * prijavljenih korisnika * LOGOUT - odjava sa sistema*
     * MSG#destination#message - slanje poruke*
     */
    public void run() {

        try {
            boolean work = true;
            while (work) {
                String request = in.readLine();
                if (request.startsWith("LOGIN")) {
                    username = request.split("#")[1];
                    System.out.println("Korisnik " + username + " se prijavljuje.");
                    if (Server.users.containsKey(username)) {
                        out.println("NOK");
                        username = "";
                    } else {
                        out.println("OK");
                        Server.users.put(username, out);
                        Server.broadcastUsers();
                    }
                } else if (request.startsWith("USERS")) {
                    String u = "";
                    for (String s : Server.users.keySet()) {
                        u += s + "@";
                    }
                    out.println("USERS#" + u);
                } else if (request.startsWith("LOGOUT")) {
                    Server.users.remove(username);
                    Server.broadcastUsers();
                    work = false;
                } else if (request.startsWith("MSG")) {
                    try {
                        String dest = request.split("#")[1];
                        String msg = request.split("#")[2];
                        PrintWriter pw = Server.users.get(dest);
                        if (pw != null) {
                            pw.println("MSG#" + username + "#" + msg);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }

            in.close();
            out.close();
            sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Korisnik " + username + " se odjavljuje.");
    }

}
