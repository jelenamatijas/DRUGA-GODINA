package net.etfbl.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class CompareIO {
	private static int numOfInts = 4_000_000;
	private static int numOfUbuffInts = 200_000;

	private abstract static class Tester {
		private String name;

		public Tester(String name) {
			this.name = name;
		}

		public long runTest() {
			System.out.print(name + ": ");
			try {
				long startTime = System.currentTimeMillis();
				test();
				long endTime = System.currentTimeMillis();
				return (endTime - startTime);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public abstract void test() throws IOException;
	}

	private static Tester[] tests = { 
		new Tester("Stream Write") {
		public void test() throws IOException {
			DataOutputStream dos = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
			for (int i = 0; i < numOfInts; i++)
				dos.writeInt(i);
			dos.close();
		}
	}, new Tester("Mapped Write") {
		public void test() throws IOException {
			FileChannel fc = new RandomAccessFile("temp.tmp", "rw")
					.getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
			for (int i = 0; i < numOfInts; i++)
				ib.put(i);
			fc.close();
		}
	}, new Tester("Stream Read") {
		public void test() throws IOException {
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
			for (int i = 0; i < numOfInts; i++)
				dis.readInt();
			dis.close();
		}
	}, new Tester("Mapped Read") {
		public void test() throws IOException {
			FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
			while (ib.hasRemaining())
				ib.get();
			fc.close();
		}
	}, new Tester("RAF Read/Write") {
		public void test() throws IOException {
			RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw");
			raf.writeInt(1);
			for (int i = 0; i < numOfUbuffInts; i++) {
				raf.seek(raf.length() - 4);
				raf.writeInt(raf.readInt());
			}
			raf.close();
		}
	}, new Tester("RAF Mapped Read/Write") {
		public void test() throws IOException {
			FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel();
			IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
			ib.put(0);
			for (int i = 1; i < numOfUbuffInts; i++)
				ib.put(ib.get(i - 1));
			fc.close();
		}
	},new Tester("FIS Copy - Read Write") {
		public void test() throws IOException {
			FileInputStream fis = new FileInputStream("temp.tmp");
			FileOutputStream fos = new FileOutputStream("temp2.tmp");
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
		}
	}, new Tester("FileChannel FIS Copy -> Read Write") {
		public void test() throws IOException {
			FileInputStream fis = new FileInputStream("temp.tmp");
			FileOutputStream fos = new FileOutputStream("temp2.tmp");
			FileChannel fcIn = fis.getChannel();
			FileChannel fcOnut = fos.getChannel();

			long position = 0;
			long count    = fcIn.size();

			fcOnut.transferFrom(fcIn, position, count);

			fis.close();
			fos.close();
			fcIn.close();
			fcOnut.close();
		}
	} };

	public static void main(String[] args) {
		for (int i = 0; i < tests.length; i++)
			System.out.println(tests[i].runTest());
	}
}