import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.io.*;

class Pismo extends Posiljka implements PismoInterface{ 
	File sadrzaj;
	
	Pismo(File f){
		super(Main.rand.nextInt(1,21));
		sadrzaj = f;
	}
	
	@Override
	public String toString(){
		List<String> lines = new ArrayList<>();
		try{
			lines = Files.readAllLines(Paths.get(sadrzaj.getAbsolutePath()));
		}catch(IOException e){
			System.out.println("Greska pri citanju sadrzaja pisma.");
		}
		String s = "";
		for(String string:lines){
			s+=string.trim()+"\n";
		}
		return super.toString() + " Pismo -> " + s;
	}
}