package src;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class Main{
	
	private static LinkedList<File> files = new LinkedList<>();
	private static LinkedList<String> fileStrings = new LinkedList<>();
	private static File root = new File(System.getProperty("user.home"));
	private static LinkedList<File> folders = new LinkedList<>();
	public static void main(String []args){
		folders.push(root);
		
					for(File file : root.listFiles()){
				if(file.isDirectory())
					folders.push(file);
				else
					files.push(file);	
			}
		
		while(folders.size() != 0){
			
			File currFolder = folders.poll();
			if(currFolder != null && currFolder.isDirectory()){
				File[] tmpFolder = currFolder.listFiles();
				if(tmpFolder != null){
					for(File file : tmpFolder){
				if(file != null && file.isDirectory())
					folders.push(file);
				else
					files.push(file);	
			}					
				}
		
			}

		}
		
		String command = String.join("", args);
		
		String writeFile = null;
		String[] commands = command.split("(--)");
		
		LinkedList<String> filesStrings = null;
		
		try{
			
			for(String arg : commands){
				if(arg.contains(">>")){
					String[] argSplit = arg.split("(>>)");
					evaluate(argSplit[0].trim());
					writeFile = argSplit[1].trim();
				} else{
					evaluate(arg.trim());
				}
			}			
			filesStrings = new LinkedList<>(Arrays.asList(files.stream().map((Function<File, String>)file -> {
			try{
				String toReturn = "";
				toReturn += (file).getName();
				FileInputStream tmp = new FileInputStream(file);
				double size = (double)tmp.readAllBytes().length / 1000;
				tmp.close();
				return "Ime " + toReturn + "Velicina u KB " + size;
			} catch(Exception e){
				e.printStackTrace();
			}
			return "";
			
		}).toArray(String[]::new)));
		} catch(IOException e){
			e.printStackTrace();
		}
		
		filesStrings.stream().forEach(System.out::println);
		
		if(writeFile != null){
			try(BufferedWriter out = new BufferedWriter(new FileWriter(new File(root, writeFile)))){
			
			filesStrings.stream().forEach(string -> {
				try{
					out.write(string, 0, string.length() - 1);
					out.newLine();
				} catch(Exception e){
					e.printStackTrace();
				}
				
			});
			
			} catch(IOException e){
			e.printStackTrace();
			}	 
		}

		
		
	}
	public static void evaluate(String arg) throws IOException, FileNotFoundException{
		
		
		if(arg.startsWith("starts-with")){
			String filter = arg.replace("starts-with", "");
			files = new LinkedList<>(Arrays.asList(files.stream().filter(file -> file.getName().startsWith(filter)).toArray(File[]::new)));
		} else if(arg.startsWith("ends-with")){
			String filter = arg.replace("ends-with", "");
			files = new LinkedList<>(Arrays.asList(files.stream().filter(file -> file.getName().endsWith(filter)).toArray(File[]::new)));
		} else if("sort [asc]".equals(arg)){
			files = new LinkedList<>(Arrays.asList(files.stream().sorted((Comparator<File>)(file1, file2)->{
				try{
					double file1Size = (double)(new FileInputStream(file1).readAllBytes().length)/1000;
					double file2Size = (double)(new FileInputStream(file2).readAllBytes().length)/1000;
					if(file1Size == file2Size)
						return 0;
					else if(file1Size > file2Size)
						return 1;
					else 
						return -1;
				} catch(Exception e){
					e.printStackTrace();
				}
				return 0;
			}).toArray(File[]::new)));
		} else if("sort [asc]".equals(arg)){
			files = new LinkedList<>(Arrays.asList(files.stream().sorted((Comparator<File>)(file1, file2) ->{
				try{
					double file1Size = (double)(new FileInputStream(file1).readAllBytes().length)/1000;
					double file2Size = (double)(new FileInputStream(file2).readAllBytes().length)/1000;
					if(file1Size == file2Size)
						return 0;
					else if(file1Size > file2Size)
						return -1;
					else 
						return 1;
				} catch(Exception e){
					e.printStackTrace();
				}
				return 0;
			}).toArray(File[]::new)));
		}
	}
}