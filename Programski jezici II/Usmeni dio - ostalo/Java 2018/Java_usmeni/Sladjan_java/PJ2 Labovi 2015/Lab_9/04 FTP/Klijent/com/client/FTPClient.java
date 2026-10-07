package com.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FTPClient {
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
					.println("Da biste se prijavili na server unesite korisnicko ime i lozinku u obliku - USER xxxxx PASS xxxx!");
			Scanner ulaz = new Scanner(System.in);
			String clientOutput = "";
			String serverInput = "";

			clientOutput = ulaz.nextLine();
			System.out.println("[Client]: " + clientOutput);
			out.println(clientOutput);

			if ((in.readLine()).equals("230")) {
				System.out
						.println("Za pregled sadrzaja direktorijuma u kom se nalazi serverska aplikacija, posaljite komandu LIST!");
				System.out
						.println("Za kreiranje novog direktorijuma poslati serveru poruku MKD ime_dir!");
				System.out
						.println("Za citanje sadrzaja tekstualnog fajla poslati serveru poruku RETR ime_fajla!");
				System.out.println("Za odjavu unesite komandu QUIT!");
			} else {
				System.out.println("[Server]: " + serverInput);
			}

			while ((clientOutput = ulaz.nextLine()) != null) {
				if (clientOutput != null) {
					System.out.println("[Client]: " + clientOutput);
					out.println(clientOutput);
					if (clientOutput.startsWith("RETR")) {
						// ocitavamo kolika je velicina fajla
						String poruka = in.readLine();
						if (poruka.equals("550")) {
							System.out.println("Ne postoji trazeni fajl!");
						} else {
							int ocekivano = Integer.parseInt(poruka);
							// pravimo stream za ocitavanje byte-a i buffer
							InputStream is = sock.getInputStream();
							byte[] buffer = new byte[2 * 1024 * 1024];
							int stiglo = 0, ostalo = 0;
							// fajl u koji cemo upisati sadrzaj
							File zaUpis = new File("copyOf"
									+ clientOutput.split(" ")[1]);
							// pisach za fajl
							FileOutputStream fos = new FileOutputStream(zaUpis);
							System.out.println("Pocetak preuzimanja fajla");
							while ((stiglo = is.read(buffer)) > 0) {
								fos.write(buffer, 0, stiglo);
								ostalo += stiglo;
								if (ocekivano == ostalo)
									break;
							}
							fos.close();
							is.close();
							System.out.println("Kraj preuzimanja fajla");
						}
					} else {
						serverInput = in.readLine();
						if (serverInput.equals("231")) {
							System.out.println("Uspjesna odjava!");
							System.exit(0);
						} else if (serverInput.equals("257")) {
							System.out.println("Uspjesno kreiranje foldera!");
						} else if (serverInput.equals("451")) {
							System.out
									.println("Kreiranje foldera nije uspjelo!");
						} else {
							System.out.println("[Server]: " + serverInput);

						}
					}
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
