package net.etfbl.pj2.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    //podesavamo port na kom cemo osluskivati
    public static final int TCP_PORT = 9000;

    public static void main(String[] args) {
        try {
            System.out.println("Provjera temperature za mjesto");
            System.out.println("Opcija KRAJ prekida komunikaciju");
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
            String msg = "";
            while (!"KRAJ".equals(msg)) {
                System.out.print("Unesite naziv mjesta: ");
                msg = scan.nextLine();
                out.println(msg);
                if (!"KRAJ".equals(msg)) {
                    String response = in.readLine();
                    System.out.println("[Server]: " + response);
                }
            }

            // zatvori konekciju
            in.close();
            out.close();
            sock.close();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
