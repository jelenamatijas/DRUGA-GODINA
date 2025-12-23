package net.etfbl.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ChannelCopy {
	private static final int BSIZE = 1024;

	public static void main(String[] args) throws Exception {
		String from, to;
		if (args.length != 2) {
			from = "data.txt";
			to = "dataCopy.txt";
		} else {
			from = args[0];
			to = args[1];
		}
		FileChannel in = new FileInputStream(from).getChannel(), out = new FileOutputStream(to).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		while (in.read(buffer) != -1) {
			buffer.flip(); // pripremi za pisanje
			out.write(buffer);
			buffer.clear(); // pripremi za citanje
		}
	}
}