package src;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {
		File exams = new File(System.getProperty("user.home") + System.getProperty("file.separator") +"Desktop", "ispiti.txt");
		if(exams.exists())
			exams.delete();
		try{
			exams.createNewFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
		File[] children = new File(System.getProperty("java.home")).listFiles();
		LinkedList<File> root = new LinkedList<>(Arrays.asList(new File(System.getProperty("java.home")).listFiles(t -> t.isDirectory())));
		System.out.println(System.getProperty("java.home"));
		try(BufferedWriter examsWriter = new BufferedWriter(new FileWriter(exams))) {
			examsWriter.write(System.getProperty("os.name") + " " + System.getProperty("os.version") + System.lineSeparator());
		for(File child : children) {
			if(child.isDirectory())
				examsWriter.write("<DIR> " + child.getName() + System.lineSeparator());
			else
				examsWriter.write(child.getName() + " "  + "size "+ Double.valueOf((double)child.length()/1000_000).toString() + " MB" + System.lineSeparator());
		}
			while(root.size() > 0) {
				children = root.pop().listFiles();
			for(File child : children) {
				if(child.isDirectory()){
						examsWriter.write("<DIR> " + child.getName() + System.lineSeparator());
						root.push(child);
				}
				else
					examsWriter.write(child.getName() + " "  + "size "+ Double.valueOf((double)child.length()/1000_000).toString() + " MB" + System.lineSeparator());
			}
		}
			examsWriter.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
			
	}
}