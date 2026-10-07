import java.util.*;
import java.nio.file.*;
import java.io.*;

class Main{
	public static void main(String []args){
		if(args.length != 1){
			System.out.println("GRESKA: format java Main putanja_do_foldera");
			return;
		}
		
		Path putanja = Paths.get(args[0]);
		List<String> lines = null;
		try{
			lines = Files.readAllLines(putanja);
		}catch(IOException e){
			System.out.println("GRESKA pri ucitavanja fajla.");
			return;
		}
		
		String className = lines.get(0).trim();
		lines.remove(0);
		List<List<String>> skrivenaPolja = new ArrayList<>();
		List<List<String>> javnaPolja = new ArrayList<>();
		
		for(String line : lines){
			line = line.trim();
			if(!line.contains("(") && !line.contains(")")){
				String[] parts = line.split(" ");
				String vidljivost = "";
				String naziv = "";
				String povratnaVrijednost = "";
				
				if(parts[0].equals("+")){
					vidljivost = "+";
				}else if(parts[0].equals("-")){
					vidljivost = "-";
				}else{
					vidljivost = "#";
				}
				naziv = parts[1];
				povratnaVrijednost = parts[2];
				
				ArrayList<String> osobine = new ArrayList<>();
				osobine.add(vidljivost);
				osobine.add(povratnaVrijednost);
				osobine.add(naziv);
				if(vidljivost.equals("+")){
					javnaPolja.add(osobine);
				}else{
					skrivenaPolja.add(osobine);
				}
			}
		}
		
		try(PrintWriter pw = new PrintWriter(new FileWriter(className + ".java"))){
			pw.println("public class " + className + "{");
			for(List<String> osobina : javnaPolja){
				pw.println("\tpublic " + osobina.get(1) + " " + osobina.get(2) + ";");
			}
			for(List<String> osobina : skrivenaPolja){
				if(osobina.get(0).equals("-")){
					pw.print("\tprivate ");
				}else{
					pw.print("\tprotected ");
				}
				pw.println(osobina.get(1) + " " + osobina.get(2) + ";\n");
			}
			
			pw.println("\tpublic " + className + "(){}\n");
			pw.print("\tpublic " + className + "(");
			String argumenti = "";
			for(List<String> osobina: skrivenaPolja){
				argumenti += osobina.get(1) + " " + osobina.get(2) + ", ";
			}
			
			for(List<String> osobina: javnaPolja){
				argumenti += osobina.get(1) + " " + osobina.get(2) + ", ";
			}
			
			argumenti = argumenti.substring(0, argumenti.lastIndexOf(","));
			pw.println(argumenti + "){");
			for(List<String> osobina: skrivenaPolja){
				pw.println("\t\tthis." + osobina.get(2) + " = " + osobina.get(2) + ";");
			}
			for(List<String> osobina: javnaPolja){
				pw.println("\t\tthis." + osobina.get(2) + " = " + osobina.get(2) + ";");
			}
			pw.println("\t}\n");
			
			//geteri
			for(List<String> osobina: skrivenaPolja){
				pw.println("\tpublic " + osobina.get(1) + " get" + osobina.get(2) + "(){");
				pw.println("\t\treturn this." + osobina.get(2) + ";\n\t}\n");
			}
			
			//seteri
			for(List<String> osobina: skrivenaPolja){
				pw.println("\tprivate void set" + osobina.get(2) + "(" + osobina.get(1) + " " + osobina.get(2) + "){");
				pw.println("\t\tthis." + osobina.get(2) + " = " + osobina.get(2) + ";\n\t}\n");
			}
			
			//toString
			pw.print("\tpublic String toString(){\n\t\treturn ");
			String ispis = "";
			for(List<String> osobina : javnaPolja){
				ispis += "\"" + osobina.get(2) + ":\" + " + osobina.get(2) + " + ";
			}
			for(List<String> osobina : skrivenaPolja){
				ispis += "\"" + osobina.get(2) + ":\" + " + osobina.get(2) + " + ";
			}
			ispis = ispis.substring(0, ispis.lastIndexOf("+"));
			pw.println(ispis + ";\n\t}\n");
			
			//equals
			pw.println("\t@Override");
			pw.println("\tpublic boolean equals(Object obj){");
			pw.println("\t\tif(obj == null || this.getClass() != obj.getClass()){");
			pw.println("\t\t\treturn false;\n\t\t}");
			pw.println("\t\t" + className + " " + className.toLowerCase() + " = (" + className + ")obj;"); 
			String rezultat = "";
			for(List<String> osobina: javnaPolja){
				String tip = osobina.get(1);
				if(tip.equals("int") || tip.equals("boolean") || tip.equals("char") || tip.equals("long") || tip.equals("double") || tip.equals("float")){
					rezultat += ("this." + osobina.get(2) + " == " + className.toLowerCase() + "." + osobina.get(2) + " && ");
				}else{
					rezultat += ("this." + osobina.get(2) + ".equals(" + className.toLowerCase() + "." + osobina.get(2) + ") && ");
				}
			}
			
			for(List<String> osobina: skrivenaPolja){
				String tip = osobina.get(1);
				if(tip.equals("int") || tip.equals("boolean") || tip.equals("char") || tip.equals("long") || tip.equals("double") || tip.equals("float")){
					rezultat += ("this." + osobina.get(2) + " == " + className.toLowerCase() + "." + osobina.get(2) + " && ");
				}else{
					rezultat += ("this." + osobina.get(2) + ".equals(" + className.toLowerCase() + "." + osobina.get(2) + ") && ");
				}
			}
			
			rezultat = rezultat.substring(0, rezultat.lastIndexOf(" && "));
			pw.println("\t\treturn " + rezultat + ";\n\t}\n");
			pw.println("}");
		}catch(IOException e){
			System.out.println("GRESKA pri upisivanju fajla.");
		}
		
	}
}