package net.etfbl.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class FileChannelExample2 {
  private static final int BSIZE = 12;
  public static void main(String[] args) throws Exception {
    // Upis u datoteku
	FileOutputStream fos = new FileOutputStream("data.txt");
    FileChannel fc = fos.getChannel();
    fc.write(ByteBuffer.wrap("Tekst za upis...".getBytes()));
    fc.close();
    fos.close();
    // Upis na kraj datoteke
    RandomAccessFile raf = new RandomAccessFile("data.txt", "rw");
    fc = raf.getChannel();
    fc.position(fc.size()); 
    fc.write(ByteBuffer.wrap("\nTekst za upis...".getBytes()));
    fc.close();
    raf.close();
    // Citanje
    FileInputStream fis = new FileInputStream("data.txt");
    fc = fis.getChannel();
    ByteBuffer buff = ByteBuffer.allocate(BSIZE);
    int bytesReaad = fc.read(buff);
    while(bytesReaad!=-1){
	    buff.flip();
	    while(buff.hasRemaining())
	      System.out.print((char)buff.get());
	    buff.clear();
	    fc.read(buff);
    }
    fis.close();
    fc.close();
  }
}