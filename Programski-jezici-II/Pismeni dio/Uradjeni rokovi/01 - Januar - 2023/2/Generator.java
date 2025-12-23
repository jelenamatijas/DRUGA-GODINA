import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

class Generator {

    static String mapTo(String s){
        switch(s.toLowerCase()){
            case "string": return "String";
            case "int": return "int";
            case "void": return "void";
            case "double": return "double";
            default: return s;
        }
    }

    public static void main(String args[]) {

        if(args.length != 1){
            System.out.println("Format: java Generator <fajl.txt>");
            return;
        }

        Path path = Paths.get(args[0]);
        List<String> sadrzaj;

        try {
            sadrzaj = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Greska pri citanju fajla.");
            return;
        }

        String naziv = path.getFileName().toString().split("\\.")[0];
        naziv = naziv.substring(0,1).toUpperCase() + naziv.substring(1);

        String klasa = "import java.util.Objects;\n\npublic class " + naziv + " {\n";

        Map<String, String> atributi = new LinkedHashMap<>();

        // ATRIBUTI I METODE
        for(String s : sadrzaj){

            String[] rijeci = s.split(" ");
            String vid = rijeci[0];
            String ime = rijeci[1];
            String tip = rijeci[2];

            String vis;
            if(vid.equals("+")) vis = "public ";
            else if(vid.equals("-")) vis = "private ";
            else vis = "protected ";

            // ATRIBUT
            if(!ime.contains("(")){
                klasa += vis + mapTo(tip) + " " + ime + ";\n";

                if(!vid.equals("+")){
                    atributi.put(ime, mapTo(tip));
                }
            }
            // METODA
            else {
				String povratniTip = mapTo(rijeci[3]);

				// Spojimo dio sa zagradama
				String potpis = rijeci[1] + " " + rijeci[2];

				String imeMetode = potpis.substring(0, potpis.indexOf('('));
				String metodaArgs = potpis.substring(potpis.indexOf('(')+1, potpis.indexOf(')'));

				String[] dijelovi = metodaArgs.split(" ");
				String argIme = dijelovi[0];
				String argTip = mapTo(dijelovi[1]);

				klasa += vis + povratniTip + " " + imeMetode +
						 "(" + argTip + " " + argIme + ")";

				if(povratniTip.equals("void")){
					klasa += "{}\n";
				} else if(povratniTip.equals("String")){
					klasa += "{ return null; }\n";
				} else {
					klasa += "{ return 0; }\n";
				}
			}

        }

        // KONSTRUKTORI
        klasa += "\npublic " + naziv + "(){}\n";

        klasa += "public " + naziv + "(";
        for(String a : atributi.keySet()){
            klasa += atributi.get(a) + " " + a + ", ";
        }
        if(!atributi.isEmpty())
            klasa = klasa.substring(0, klasa.length()-2);

        klasa += "){\n";
        for(String a : atributi.keySet()){
            klasa += "this." + a + " = " + a + ";\n";
        }
        klasa += "}\n";

        // GET / SET
        for(String a : atributi.keySet()){
            String tip = atributi.get(a);
            String A = a.substring(0,1).toUpperCase() + a.substring(1);

            klasa += "public " + tip + " get" + A +
                     "(){ return " + a + "; }\n";

            klasa += "public void set" + A +
                     "(" + tip + " " + a + "){ this." +
                     a + " = " + a + "; }\n";
        }

        // toString
        klasa += "@Override\npublic String toString(){\nreturn \"" +
                 naziv + " [";

        for(String a : atributi.keySet()){
            klasa += a + "=\" + " + a + " + \", ";
        }
        if(!atributi.isEmpty())
            klasa = klasa.substring(0, klasa.length()-2);

        klasa += "]\";\n}\n";

        // equals
        klasa += "@Override\npublic boolean equals(Object o){\n" +
                 "if(this == o) return true;\n" +
                 "if(o == null || getClass() != o.getClass()) return false;\n" +
                 naziv + " that = (" + naziv + ") o;\n";

        for(String a : atributi.keySet()){
            klasa += "if(!Objects.equals(" + a + ", that." + a + ")) return false;\n";
        }

        klasa += "return true;\n}\n";

        klasa += "}";

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(naziv + ".java")))){
            pw.print(klasa);
        } catch(IOException e){
            System.out.println("Greška pri upisu u fajl.");
        }
    }
}
