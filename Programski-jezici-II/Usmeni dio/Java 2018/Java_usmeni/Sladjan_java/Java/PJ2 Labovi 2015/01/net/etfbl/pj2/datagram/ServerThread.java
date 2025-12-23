package net.etfbl.pj2.datagram;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author igor
 */
public class ServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    private boolean stop = false;

    public ServerThread() throws IOException {
        super();
        socket = new DatagramSocket(4445);

        try {
            in = new BufferedReader(new FileReader("poruke.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Ne postoji datoteka sa citatima.");
        }
    }

    public void run() {

        while (!stop) {
            try {
                byte[] buf = new byte[256];

                // prihvatanje zahtjeva
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String temp = new String(buf);
                String request = new String(buf, 0, temp.indexOf(0));

                //obrada zahtjeva
                System.out.println("Zahtjev: " + request + " od korisnika " + packet.getAddress());

                if ("DATE".equals(request)) {
                    buf = (new Date()).toString().getBytes();
                } else if ("MSG".equals(request)) {
                    buf = this.getNextMessage().getBytes();
                } else if ("STOP".equals(request)) {
                    stop = true;
                }

                // slanje odgovora
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
        System.out.println("Kraj rada servera");

    }

    protected String getNextMessage() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                returnValue = "Nema vise poruka.";
            }
        } catch (IOException e) {
            System.err.println("IOException - citanje poruka");
            returnValue = "Nema vise poruka.";
        }
        return returnValue;
    }
}
