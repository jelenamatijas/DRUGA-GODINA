package net.etfbl.pj2.server;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

public class Server {

    //lista korisnika
    public static HashMap<String, PrintWriter> users = new HashMap<>();

    public static void broadcastUsers() {
        for (PrintWriter pw : users.values()) {
            String u = "";
            for (String s : Server.users.keySet()) {
                u += s + "@";
            }
            pw.println("USERS#" + u);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Unesite port:");
            Scanner scan = new Scanner(System.in);
            int port = scan.nextInt();
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server je pokrenut...");
            new ServerConsoleThread().start();
            while (true) {
                Socket sock = ss.accept();
                new ServerThread(sock).start();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class ServerConsoleThread extends Thread {

    public ServerConsoleThread() {
    }

    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {                
                System.out.print(">");
                String option = keyboard.readLine();
                if ("USERS".equals(option)) {
                    System.out.println("== Aktivni korisnici ==");
                    for (String s : Server.users.keySet()) {
                        System.out.println(s);
                    }
                    System.out.println("=======================");
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
