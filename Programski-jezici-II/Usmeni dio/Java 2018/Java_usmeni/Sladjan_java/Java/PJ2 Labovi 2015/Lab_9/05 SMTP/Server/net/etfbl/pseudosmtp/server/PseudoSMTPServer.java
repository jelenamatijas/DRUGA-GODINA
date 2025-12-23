package net.etfbl.pseudosmtp.server;

import java.net.ServerSocket;
import java.net.Socket;


public class PseudoSMTPServer {
	// podesavamo port na kom ce server osluskivati zahtjeve od klijenata
	public static final int TCP_PORT = 9000;

	public static void main(String[] args) {
		try {
			// slušaj zahteve na datom portu
			ServerSocket ss = new ServerSocket(TCP_PORT);
			System.out.println("Server running...");
			while (true) {
				// prihvataj klijente
				Socket sock = ss.accept();
				System.out.println("Client accepted: "+sock.getInetAddress().getHostAddress());
				// startuj nit za svakog klijenta
				new PseudoSMTPServerThread(sock);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
