import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("GRESKA format: java Main <putanja_do_fajla>");
			return;
		}
		
		Path path = Paths.get(args[0]);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja linija fajla");
		}
		
		String className = lines.get(0).trim();
		lines.remove(0);
		
		List<List<String>> skrivenaPolja = new ArrayList<>();
		List<List<String>> javnaPolja = new ArrayList<>();
		
		for (String line : lines) {
			line = line.trim();
			if (!line.contains("(") && !line.contains(")")) {
				String[] parts = line.split(" ");
				//System.out.println(parts);
					
				String vidljivost = "";
				String ime = "";
				String tip = "";
					
				if (parts[0].equals("-")) {
					vidljivost = "-";
				} else if (parts[0].equals("#")) {
					vidljivost = "#";
				} else {
					vidljivost = "+";
				}
					
				ime = parts[1];
					
				tip = parts[2];
					
				ArrayList<String> osobine = new ArrayList<>();
				osobine.add(vidljivost);
				osobine.add(ime);
				osobine.add(tip);
					
				//skrivenaPolja.add(osobine);
				if (line.startsWith("+")) {
					javnaPolja.add(osobine);
				} else {
					skrivenaPolja.add(osobine);
				}
			}
		}
		
		generisiKod(className, javnaPolja, skrivenaPolja);
		// System.out.println(className + "\n" + javnaPolja + "\n" + skrivenaPolja);
	}
	
	public static void generisiKod(String className, List<List<String>> javnaPolja, List<List<String>> skrivenaPolja) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(className + ".java"))) {
			pw.println("public class " + className + " {\n");
			for (List<String> osobine : javnaPolja) {
				pw.println("\tpublic " + osobine.get(2) + " " + osobine.get(1) + ";\n");
			}
			for (List<String> osobine : skrivenaPolja) {
				pw.println("\t" + (osobine.get(0).equals("-") ? "private " : "protected ") + osobine.get(2) + " " + osobine.get(1) + ";\n");
			}
			
			// Konstruktor bez parametara
			pw.println("\tpublic " + className + "() {}\n");
			
			// Konstruktor sa parametrima
			pw.print("\tpublic " + className + "(");
			String sadrzaj = "";
			for (List<String> osobine : skrivenaPolja) {
				sadrzaj += (osobine.get(2) + " " + osobine.get(1) + ", ");
			}
			for (List<String> osobine : javnaPolja) {
				sadrzaj += (osobine.get(2) + " " + osobine.get(1) + ", ");
			}
			sadrzaj = sadrzaj.substring(0, sadrzaj.lastIndexOf(","));
			// System.out.println(sadrzaj);
			pw.print(sadrzaj + ") {\n");
			for (List<String> osobine : skrivenaPolja) {
				pw.println("\t\tthis." + osobine.get(1) + " = " + osobine.get(1) + ";");
			}
			for (List<String> osobine : javnaPolja) {
				pw.println("\t\tthis." + osobine.get(1) + " = " + osobine.get(1) + ";");
			}
			pw.println("\t}\n");
			
			// setteri
			for (List<String> osobine : skrivenaPolja) {
				pw.println("\tpublic void set" + osobine.get(1) + "(" + osobine.get(2) + " " + osobine.get(1) + ") {");
				pw.println("\t\tthis." + osobine.get(1) + " = " + osobine.get(1) + ";");
				pw.println("\t}\n");
			}
			
			
			
			// getteri
			for (List<String> osobine : skrivenaPolja) {
				pw.println("\tpublic " + osobine.get(2) + " get" + osobine.get(1) + "() {");
				pw.println("\t\treturn this." + osobine.get(1) + ";");
				pw.println("\t}");
			}
			
			// toString()
			pw.println("\t@Override");
			pw.println("\tpublic String toString(){");
			pw.print("\t\treturn \"" + className + ":\" + ");
			String sadrzajString = "";
			for (List<String> osobine : skrivenaPolja) {
				sadrzajString += ("\"" + osobine.get(1) + "=\" + " + "this." + osobine.get(1) + " + \", \" + ");
			}
			for (List<String> osobine : javnaPolja) {
				sadrzajString += ("\"" + osobine.get(1) + "=\" + " + "this." + osobine.get(1) + " + \", \" + ");
			}
			sadrzajString = sadrzajString.substring(0, sadrzajString.lastIndexOf(" +") - 7);
			pw.println(sadrzajString + ";");
			pw.println("\n\t}");
			
			// equals(Object object)
			pw.println("\t@Override");
			pw.println("\tpublic boolean equals(Object object) {");
			pw.println("\t\tif (this.getClass() == object.getClass()) {");
			pw.println("\t\t\t" + className + " " + className.toLowerCase() + " = " + "(" + className + ") " + "object;");
			pw.print("\t\t\treturn ");
			String sadrzajEquals = "";
			for (List<String> osobine : skrivenaPolja) {
				if (osobine.get(2).equals("boolean") || osobine.get(2).equals("int") || osobine.get(2).equals("double") || osobine.get(2).equals("float") || osobine.get(2).equals("char") || osobine.get(2).equals("short") || osobine.get(2).equals("long")) {
					sadrzajEquals += ("this." + osobine.get(1) + " == " + className.toLowerCase() + "." + osobine.get(1) + " && ");
				} else {
					sadrzajEquals += ("this." + osobine.get(1) + ".equals(" + className.toLowerCase() + "." + osobine.get(1) + ") && ");
				}
			}
			for (List<String> osobine : javnaPolja) {
				if (osobine.get(2).equals("boolean") || osobine.get(2).equals("int") || osobine.get(2).equals("double") || osobine.get(2).equals("float") || osobine.get(2).equals("char") || osobine.get(2).equals("short") || osobine.get(2).equals("long")) {
					sadrzajEquals += ("this." + osobine.get(1) + " == " + className.toLowerCase() + "." + osobine.get(1) + " && ");
				} else {
					sadrzajEquals += ("this." + osobine.get(1) + ".equals(" + className.toLowerCase() + "." + osobine.get(1) + ") && ");
				}
			}
			sadrzajEquals = sadrzajEquals.substring(0, sadrzajEquals.lastIndexOf(" &&"));
			pw.println(sadrzajEquals + ";\n\t\t}\n\t\treturn false;\n\t}");
			
			pw.print("}\n");
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom pisanja u .java fajla");
			ex.printStackTrace();
		}
	}
}