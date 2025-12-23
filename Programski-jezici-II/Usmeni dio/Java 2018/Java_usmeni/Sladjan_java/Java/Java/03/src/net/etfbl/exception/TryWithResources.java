package net.etfbl.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		readFirstLineFromFileWithFinally2("test1.txt");
	}

	static void readFirstLineFromFileWithFinally2(String path) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			br.readLine();
		} catch (IOException e) {
			System.out.println("catch for try");
		} finally {
				System.out.println("try in finally");
				br.close();

		}

	}

	static String readFirstLineFromFile(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			return br.readLine();
		}
	}

	static void readFirstLineFromFile2(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path));
				BufferedReader br2 = new BufferedReader(new FileReader(path))) {
			br.readLine();
			br2.readLine();
		}
	}

	static String readFirstLineFromFileWithFinally(String path)
			throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			return br.readLine();
		} finally {
			if (br != null)
				br.close();
		}

	}
}
