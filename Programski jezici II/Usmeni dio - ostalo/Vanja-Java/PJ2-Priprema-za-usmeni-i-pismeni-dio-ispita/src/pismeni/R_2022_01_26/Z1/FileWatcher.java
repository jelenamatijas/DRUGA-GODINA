import java.util.*;
import java.io.*;
import java.nio.*;

public class FileWatcher extends Thread{
	
	String folder;
	
	public FileWatcher(String folder){
		this.folder = folder;
	}
	
	@Override
	public void run(){
	}
}