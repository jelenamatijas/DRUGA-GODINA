package net.etfbl.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelExample {

	public static void main(String[] args) throws IOException {
	    RandomAccessFile raf = new RandomAccessFile("java2.txt", "rw");
	    FileChannel fileChannel = raf.getChannel();

	    ByteBuffer buf = ByteBuffer.allocate(16);

	    int bytesRead = fileChannel.read(buf);
	    while (bytesRead != -1) {

	      System.out.println("Read " + bytesRead);
	      buf.flip();

	      while(buf.hasRemaining()){
	          System.out.print((char) buf.get()+":");
	      }

	      buf.clear();
	      bytesRead = fileChannel.read(buf);
	    }
	    raf.close();
	}

}
