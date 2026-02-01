package net.etfbl.pseudosmtp.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class PseudoSMTPUtilities {
	public static boolean isThereAUser(String userAddress) {
		boolean thereIs = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"users.txt")));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.equals(userAddress)) {
					thereIs = true;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thereIs;
	}

	public static String chooseAnsware() {
		Random r = new Random();
		int ans = r.nextInt(2) + 1;
		if (ans == 1)
			return "TEMPREJECT";
		else if (ans == 2)
			return "ACCEPT";
		else
			return "REJECT";
	}

	public static boolean checkDomain(String domain) {
		if (domain.equals(domain))
			return true;
		return false;
	}

	public static boolean saveMessage(String message, String receiver) {
		boolean saved = false;
		File f = new File(receiver + System.currentTimeMillis() + ".txt");
		try {
			if (f.createNewFile()) {
				PrintWriter pw = new PrintWriter(new FileWriter(f));
				pw.print(message);
				pw.close();
				saved = true;
			} else {
				saved = false;
			}
		} catch (IOException e) {
			saved = false;
		}
		return saved;
	}

	public static void addNewUser(String user) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileWriter(new File("users.txt"), true));
			pw.println(user);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
