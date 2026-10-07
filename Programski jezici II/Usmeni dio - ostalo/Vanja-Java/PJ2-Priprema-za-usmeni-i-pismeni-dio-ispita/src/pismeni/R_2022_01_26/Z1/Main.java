import java.util.*;
import java.io.*;


public class Main {
	
	static boolean FULL = false;
	
	public static void main(String[] args){
		try{
			long start = System.currentTimeMillis();
		
			new SendThread();
			new ReceiveThread();
			new FileWatcher("Alisa");
			new FileWatcher("Bob");
			
		
			long end = System.currentTimeMillis();
			long duration = end - start;
			System.out.println("Vrijeme trajanja simulacije: " + duration);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}