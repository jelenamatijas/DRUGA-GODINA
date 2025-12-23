package src;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.util.List;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main{

	public static void main(String args[]) throws IOException{
		Runtime run = Runtime.getRuntime();
		ArrayList<String> methods = new ArrayList<>(), arguments = new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Unesite ime klase. Za kraj unosa unesite end");
		String className = null;
		String s;
		do{
			s = in.readLine();
			if(!s.isEmpty() && ! "end".equals(s)) 
				className = s;
		} while(!"end".equals(s));
		System.out.println("Unesite metode. Za kraj unosa unesite end");
		do{
			s = in.readLine();
			if(!s.isEmpty() && !"end".equals(s)) 
				methods.add(s);
		} while(!"end".equals(s));
		System.out.println("Unesite argumente. Za kraj unosa unesite end");
		do{
			s = in.readLine();
			if(!s.isEmpty() && !"end".equals(s)) 
				arguments.add(s);
		} while(!"end".equals(s));	
	
	ClassGenerator cg = new ClassGenerator(arguments, methods, className);
 
	String[] classAtributes = className.split(" ");
	String classFileName = classAtributes[classAtributes.length - 1] + ".java";
	File classFile = new File(System.getProperty("user.dir"), classFileName);
	if(classFile.exists())
		classFile.delete();
	classFile.createNewFile();
	Path filePath = Paths.get(System.getProperty("user.dir"), classFileName);
	BufferedWriter out = Files.newBufferedWriter(filePath);
	PrintWriter pwout = new PrintWriter(out);
	cg.generate(pwout);
	pwout.close();
	//run.exec("cmd /c " + "javac " + classFileName);
	ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(new File(System.getProperty("user.dir"), classAtributes[classAtributes.length - 1] + ".out")));
	outFile.writeObject(cg);
}
}
// predpostavimo da se unose ispravno formatirni podaci
// primjer: argument public static int broj
// metoda: generate void (String s) public static 
/*			   ^      ^      ^			 ^
			   |      |		 |			 |
			   |      |      |			 |
			  ime    tip     argumenti   modifikatori 
	Metoda se unosi u ovom formati radi lakseg odredjivanja povratnog tipa, koji moze dolaziti posle 0, 1, 2 ili 3 modifikatora pristupa
	Predpostavimo da nema bacanja izuzetaka
*/
// ime: public class Builder

class ClassGenerator implements Serializable{
	
	static List<String> primitives = Arrays.asList("byte", "short", "int", "long", "float", "double");
	
	
	ArrayList<String> gettersAndSetters = new ArrayList<>();
	ArrayList<String> arguments,  methods;
	String className;
	
	ClassGenerator(ArrayList<String> arguments, ArrayList<String> methods, String className){
		this.arguments = arguments;
		this.methods = methods;
		this.className = className;
	}
	
	private String generateGet(String arg){
		String [] argSplit = arg.split(" ");
		String name = argSplit[argSplit.length - 1];
		String returntype = argSplit[argSplit.length - 2];
		if(!"public".equals(argSplit[0]))
			return "public " + returntype + " " + "get" + name + "()" + "{" + " return " + name + ";" + "}";
		return "";
	}
	
	private String generateSet(String arg){
		
		String [] argSplit = arg.split(" ");
		String name = argSplit[argSplit.length - 1];
		String returntype = argSplit[argSplit.length - 2];
		if(!"public".equals(argSplit[0]))
			return "public " + "void" + " " + "set" + name + "(" + returntype + " " + name + ")" +  "{" + "this." + name + "=" + name +";" + "}";
		else 
			return "";
		
	}
	
	void generate(PrintWriter out){
		out.println(className + "{");
		
		for(String argument : arguments){
			out.println(argument + ";");
			gettersAndSetters.add(generateGet(argument));
			gettersAndSetters.add(generateSet(argument));
		}
		
		out.println();
		
		for(String method : methods){
			String []split = method.split(" ");
			String name = split[0];
			String returnType = split[1];
			String arguments = method.substring(method.indexOf("("), method.indexOf(")") + 1);
			String visibilityModifiers = method.substring(method.indexOf(")") + 1, method.length());
			String methodDef = visibilityModifiers + " " + returnType + " " + name + arguments;
			if(visibilityModifiers.contains("abstract")){
				methodDef += ";";
				out.println(methodDef);
			} else{
				methodDef += "{";
				out.println(methodDef);
				if("void".equals(returnType)){
					out.println("}");
				} else if("boolean".equals(returnType)){
					out.println("return false;}");
				} else if("char".equals(returnType)){
					out.println("return '\0';}");
				} else if(primitives.contains(returnType)){
					out.println("return 0;}");
				} else{
					out.println("return null;}");
				}
			}
		}
		
		for(String getOrSet : gettersAndSetters){
			out.println(getOrSet);
		}
		
		out.println("}");
	}
}