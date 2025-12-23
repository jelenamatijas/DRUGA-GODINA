package net.etfbl.pj2.client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import net.etfbl.pj2.proizvodi.Proizvod;

public class Client {

    //podesavamo port na kom cemo osluskivati
    public static final int TCP_PORT = 9000;

    public static void main(String[] args) {
        ArrayList<Proizvod> racun = new ArrayList<>();
        try {
            // odredi adresu racunara sa kojim se povezujemo
            // (povezujemo se sa nasim racunarom)
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            // otvori socket prema drugom racunaru
            Socket sock = new Socket(addr, TCP_PORT);

            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            /*klijent salje zahtjeve serveru u vidu poruka i ocitava njegove odgovore; 
             same akcije zavise od protokola komunikacije*/
            //salji poruku

            Scanner scan = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            int opcija = 0;
            do {

                if (opcija == 1) {
                    oos.writeObject("LIST");
                    Proizvod[] p = (Proizvod[]) ois.readObject();
                    for (Proizvod pr : p) {
                        System.out.println(pr);
                    }
                } else if (opcija == 2) {
                    System.out.print("Unesite sifru proizvoda:");
                    String sifra = scan2.nextLine();
                    oos.writeObject("BUY#" + sifra);
                    oos.flush();
                    Proizvod p = (Proizvod) ois.readObject();
                    if (p != null) {
                        System.out.println("Kupljen proizvod: " + p);
                        racun.add(p);
                    } else {
                        System.out.println("Proizvod sa navedenom sifrom ne postoji");
                    }
                } else if (opcija == 3) {
                    oos.writeObject("VIEW");
                    oos.flush();
                    racun = (ArrayList<Proizvod>) ois.readObject();
                    for (Proizvod p : racun) {
                        System.out.println(p);
                    }

                } else if (opcija == 4) {
                    System.out.print("Unesite sifru proizvoda:");
                    String sifra = scan2.nextLine();
                    oos.writeObject("DEL#" + sifra);
                    oos.flush();
                    Proizvod p = (Proizvod) ois.readObject();
                    if (p != null) {
                        if (racun.remove(p)) {
                            System.out.println("Vracen proizvod: " + p);
                        } else {
                            System.out.println("Proizvod nije prethodno kupljen");
                        }
                    } else {
                        System.out.println("Proizvod sa navedenom sifrom ne postoji");
                    }
                }

                System.out.println("Opcije:");
                System.out.println("1 - prikaz cjenovnika");
                System.out.println("2 - kupovina proizvoda");
                System.out.println("3 - pregled svih kupljenih proizvoda");
                System.out.println("4 - uklanjanje proizvoda sa racuna");
                System.out.println("5 - zavrsetak kupovine");
            } while ((opcija = scan.nextInt()) != 5);
            oos.writeObject("END");
            oos.flush();
            System.out.println("Kupljeni proizvodi:");
            double ukupno = 0;
            for (Proizvod p : racun) {
                System.out.println(p.getSifra() + " " + p.getNaziv() + " " + p.getCijena());
                ukupno += p.getCijena();
            }
            System.out.println("Ukupno za placanje " + ukupno);
            // zatvori konekciju
            ois.close();
            sock.close();

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
