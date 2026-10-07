import java.util.*;
import java.io.*;

abstract class DataAdapter implements Runnable{
	String filePath;
	
	DataAdapter(String filePath){
		this.filePath = filePath;
	}
	
	abstract public void run();
	
	abstract List<Element> importData();
}