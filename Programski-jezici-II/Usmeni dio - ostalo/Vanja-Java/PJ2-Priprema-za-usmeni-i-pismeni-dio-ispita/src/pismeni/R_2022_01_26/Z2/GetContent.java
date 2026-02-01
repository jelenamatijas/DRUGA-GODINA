package pismeni.R_2022_01_26.Z2;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.time.LocalDate;

public class GetContent extends Thread{

	String putanja;

	public GetContent(String putanja){
		this.putanja = putanja;
		start();
	}

	@Override
	public void run(){

		// smješta binarno serijalizovan fajl u folder sa današnjim datumom
		String date = LocalDate.now().toString();
		new File(date).mkdir();
		String bin = "." + File.separator + date + File.separator + new File(putanja).getName().split("\\.")[0]  + ".bin";
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bin))){
			List<String> lines = Files.readAllLines(Path.of(putanja));
			oos.writeObject(lines);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			new Database(putanja).join();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}