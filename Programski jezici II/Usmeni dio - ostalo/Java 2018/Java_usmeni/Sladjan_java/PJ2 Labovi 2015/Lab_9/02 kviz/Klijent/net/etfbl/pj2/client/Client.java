package net.etfbl.pj2.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    //podesavamo port na kom cemo osluskivati
    public static final int TCP_PORT = 9000;

    public static void main(String[] args) {
        try {
            System.out.println("Kviz");
            // odredi adresu racunara sa kojim se povezujemo
            // (povezujemo se sa nasim racunarom)
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            // otvori socket prema drugom racunaru
            Socket sock = new Socket(addr, TCP_PORT);
            // inicijalizuj ulazni stream
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    sock.getInputStream()));
            // inicijalizuj izlazni stream
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(
                                    sock.getOutputStream())), true);
            /*klijent salje zahtjeve serveru u vidu poruka i ocitava njegove odgovore; 
             same akcije zavise od protokola komunikacije*/
            //salji poruku
            Scanner scan = new Scanner(System.in);
            System.out.print("Username: ");
            String username = scan.nextLine();
            System.out.print("Password: ");
            String password = scan.nextLine();

            //slanje parametara za prijavu
            out.println(username + "#" + password);
            if ("OK".equals(in.readLine())) {
                //klijent je prijavljen i pocinje da igra kviz
                for (int i = 0; i < 5; i++) {
                    String s = in.readLine();
                    String question = s.split("#")[0];
                    String answer1 = s.split("#")[1];
                    String answer2 = s.split("#")[2];
                    String answer3 = s.split("#")[3];

                    System.out.println(question);
                    System.out.println("1 - " + answer1);
                    System.out.println("2 - " + answer2);
                    System.out.println("3 - " + answer3);
                    out.println(scan.nextLine());
                }
                String score = in.readLine();
                System.out.println(score);
            } else {
                System.out.println("Podaci za prijavu nisu korektni!");
            }

            // zatvori konekciju
            in.close();
            out.close();
            sock.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
    }
}
