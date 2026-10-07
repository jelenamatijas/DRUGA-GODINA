package src;

import java.io.File;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Arrays;


public class Main{
	
	public static void main(String args[]){
		
	System.out.println(args.length);
		
	LinkedList<File> directories = new LinkedList<>();
	File root = new File(args[0]);
	
	if(args.length != 2 || args[1].isEmpty() || !root.isDirectory()){
		System.out.println("Greska u argumentima komandne linije");
		return;
	}
	
	directories.addAll(Arrays.asList(root.listFiles(t -> t.isDirectory())));
	File[] rootFiles = root.listFiles(t -> !t.isDirectory());
	for(File file : rootFiles){
		if(file.getName().endsWith(".txt")){
			try{
					count(file, args[1]);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
	}
	
	while(directories.size() != 0){
		File currDirectory = directories.pop();
		directories.addAll(Arrays.asList(currDirectory.listFiles(t -> t.isDirectory())));
		File[] files = currDirectory.listFiles(t -> !t.isDirectory());
		for(File file : files){
			if(file.getName().endsWith(".txt")){
			try{
					count(file, args[1]);
				} catch(Exception e){
					e.printStackTrace();
				}			
		}
		
		}
	}
	}
	private static void count(File toOpen, String toCount) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(toOpen));
		int toReturn = 0;
		String[] lines = in.lines().filter(t -> t.contains(toCount)).toArray(String[]::new);
		for(String line : lines){
			toReturn += line.split("(" + toCount + ")").length;
			if(toCount.equals(line))
				toReturn++;
		}
		
		if(toReturn != 0){
			System.out.println(toOpen + " broj ponavljanja " + toCount + " " + toReturn);
		}
	}
	
}