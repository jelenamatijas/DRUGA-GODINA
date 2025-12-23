package net.etfbl.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFromToExample {

	public static void main(String[] args) throws IOException {
		transferTo();
	}
	
	static void transferFrom() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("ulaz.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("izlaz.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count    = fromChannel.size();

		toChannel.transferFrom(fromChannel, position, count);
		fromFile.close();
		toFile.close();
		fromChannel.close();
		toChannel.close();
	}
	
	static void transferTo() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("ulaz.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("izlaz.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count    = fromChannel.size();

		fromChannel.transferTo(position, count, toChannel);
		fromFile.close();
		toFile.close();
		fromChannel.close();
		toChannel.close();
	}

}
