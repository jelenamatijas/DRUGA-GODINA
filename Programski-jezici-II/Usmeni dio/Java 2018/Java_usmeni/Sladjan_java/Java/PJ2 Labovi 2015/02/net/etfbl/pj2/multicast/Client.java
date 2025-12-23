package net.etfbl.pj2.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Igor
 */
public class Client {

    public static void main(String[] args) {
        MulticastSocket socket = null;
        byte[] buf = new byte[256];
        try {
            socket = new MulticastSocket(20000);
            InetAddress address = InetAddress.getByName("224.0.0.11");
            socket.joinGroup(address);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
