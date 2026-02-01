package net.etfbl.pj2.multicast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Igor
 */
public class Server {

    public static void main(String[] args) {
        MulticastSocket socket = null;
        byte[] buf = new byte[6];
        try {
            socket = new MulticastSocket();
            InetAddress address = InetAddress.getByName("224.0.0.11");
            socket.joinGroup(address);

            BufferedReader in = new BufferedReader(new FileReader("poruke.txt"));

            String msg = "";
            while ((msg = in.readLine()) != null) {
                buf = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 20000);
                socket.send(packet);
                Thread.sleep(1000);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
}
