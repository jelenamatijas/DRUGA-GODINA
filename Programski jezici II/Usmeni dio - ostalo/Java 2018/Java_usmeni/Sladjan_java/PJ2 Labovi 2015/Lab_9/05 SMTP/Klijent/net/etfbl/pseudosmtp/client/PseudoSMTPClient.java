package net.etfbl.pseudosmtp.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PseudoSMTPClient {
	// podesavamo port na kom cemo osluskivati
	public static final int TCP_PORT = 9000;

	public static void main(String[] args) {
		try {
			// odredi adresu racunara sa kojim se povezujemo
			// (povezujemo se sa nasim racunarom)
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			// otvori socket prema drugom racunaru
			Socket sock = new Socket(addr, TCP_PORT);
			// inicijalizuj ulazni stream
			BufferedReader in = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
			// inicijalizuj izlazni stream
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(sock.getOutputStream())), true);
			/*
			 * klijent salje zahtjeve serveru u vidu poruka i ocitava njegove
			 * odgovore; same akcije zavise od protokola komunikacije
			 */
			// salji poruku
			/* npr. System.out.println("[Client]: HELLO"); */
			System.out
					.println("Da biste se prijavili na server unesite korisnicko ime");
			Scanner ulaz = new Scanner(System.in);
			String clientOutput = "";
			String serverInput = "";

			clientOutput = ulaz.nextLine();
			System.out.println("[Client]: " + clientOutput);
			out.println(clientOutput);
			// provjera ispravnosti
			serverInput = in.readLine();
			if (serverInput.equals("ACCEPT")) {
				do {
					System.out.println("Posaljite poruku HELLO ime_domena");
					out.println(ulaz.nextLine());
					serverInput = in.readLine();
					if (serverInput.equals("250 OK")) {
						do {
							System.out
									.println("Unesite MAIL FROM: vasa_mail_adresa");
							out.println(ulaz.nextLine());
							serverInput = in.readLine();
							if (serverInput.equals("250 OK")) {
								do {
									System.out
											.println("Unesite RCPT TO: adresa primaoca");
									out.println(ulaz.nextLine());
									System.out.println(serverInput = in
											.readLine());
									if (serverInput.equals("250 OK")) {
										System.out.println("Saljem DATA");
										out.println("DATA");
										System.out.println(serverInput = in
												.readLine());
										if (serverInput.equals("340 OK")) {
											System.out
													.println("Unesite tekst poruke za slanje");
											String zaSlanje = ulaz.nextLine();
											zaSlanje += "\\015\\012";
											out.println(zaSlanje);
											serverInput = in.readLine();
											if (serverInput.equals("250 OK")) {
												System.out
														.println("Uspjesno slanje");
												out.println("QUIT");
												serverInput = in.readLine();
												System.exit(0);
											} else {
												System.out
														.println("Neuspjesno slanje");
											}
										} else {
											System.out
													.println("Ne mozete poslati poruku, server nije spreman...");
											System.exit(0);
										}
									} else {
										System.out.println("Ne valja primalac");
									}
								} while (serverInput.equals("550 WRONG"));
							}
						} while (serverInput.equals("550 WRONG"));
					}
				} while (serverInput.equals("550 WRONG"));

			} else if (serverInput.equals("REJECT")) {
				System.out.println("Nije vam dozvoljena prijava!!!");
				System.exit(0);
			} else {
				System.out.println("Pokusajte ponovo za 5 min!");
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
