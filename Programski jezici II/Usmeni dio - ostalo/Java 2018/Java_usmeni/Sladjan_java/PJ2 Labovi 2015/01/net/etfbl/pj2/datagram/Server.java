package net.etfbl.pj2.datagram;

import java.io.IOException;

/**
 *
 * @author igor
 */
public class Server {

    public static void main(String[] args) throws IOException {
        new ServerThread().start();
    }
}
