package net.etfbl.pj2.datagram;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author igor
 */
public class Client {

    public static void process(DatagramSocket socket, String msg) throws IOException {
        // slanje zahtjeva
        byte[] buf = msg.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        buf = new byte[256];
        // citanje odgovora
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // prikaz odgovora
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(received);
    }

    public static void main(String[] args) throws IOException {

        // kreiranje datagram socket-a
        DatagramSocket socket = new DatagramSocket();

        int option = 0;

        Scanner scan = new Scanner(System.in);

        while (option != 4) {
            System.out.println("Opcije:");
            System.out.println("1 - dobijanje poruke");
            System.out.println("2 - dobijanje datuma");
            System.out.println("3 - gasenje servera");
            System.out.println("4 - kraj rada");
            option = scan.nextInt();

            if (option == 1) {
                process(socket, "MSG");
            } else if (option == 2) {
                process(socket, "DATE");
            } else if (option == 3) {
                process(socket, "STOP");
            } else {
                System.err.println("Opcija ne postoji!");
            }

        }
        socket.close();
    }

}
