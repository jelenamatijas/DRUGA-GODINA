package net.etfbl.io;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintW {
	public static void main(String[] args) throws IOException {
		CharArrayWriter cw = new CharArrayWriter(); 
		PrintWriter pw = new PrintWriter(cw);	    
		pw.print("asdfghjkl");			   
		pw.println();				          
		pw.flush();					   
		pw.println(1234567890);			
		pw.println(true);			
		System.out.println(cw.toCharArray());
		pw.println("asdfghjkl");		
		pw.close();		
		System.out.println(cw.toCharArray());
	}
}
