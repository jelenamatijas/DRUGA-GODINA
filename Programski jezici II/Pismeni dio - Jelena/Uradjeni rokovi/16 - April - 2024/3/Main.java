import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

class Main{
	static int brojSkrivenihFajlova=0;
	static int ukupanBrojFajlova;
	private static File txtFajl;
	
	public static void main(String args[]){
		if(args.length != 1){
			System.out.println("GRESKA pri unosu argumenata");
			return;
		}
		
		String putanjaFoldera = args[0];
		Path rootPath = Paths.get(putanjaFoldera);
		
		if(!Files.isDirectory(rootPath) || !Files.exists(rootPath)){
			System.out.println("Putanja do direktorijuma je pogresna ili direktorijum ne postoji.");
			return;
		}
		
		txtFajl = new File(rootPath + File.separator + "txtFajl.txt");
		try{
			if(!txtFajl.exists()){
				if(txtFajl.createNewFile()){
					System.out.println("Fajl uspjesno kreiran.");
				}
			}else{
				System.out.println("fajl vec postoji.");
			}
			
			obradiDirektorijum(rootPath);
			upisiStatistiku();
		}catch(IOException e){
			System.out.println("GRESKA pri kreiranju fajla: " + txtFajl);
			return;
		}
		
	}
	
	private static void obradiDirektorijum(Path rootPath){
		try{
			Files.walk(rootPath).forEach(path -> {
				try{
					if(Files.isHidden(path)){
						brojSkrivenihFajlova++;
						upisi(path);
					}
					if(Files.isRegularFile(path)){
						ukupanBrojFajlova++;
					}
				}catch(IOException e){
					System.out.println("GRESKA pri provjeri fajla.");
				}
			});
		}catch(IOException e){
			System.out.println("GRESKA pri prolasku kroz direktorijum.");
		}
		
	}
	
	private static void upisi(Path path){
		try(PrintWriter pw = new PrintWriter(new FileWriter(txtFajl, true))){
			pw.println(path);
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl.");
		}
	}
	
	private static void upisiStatistiku(){
		try(PrintWriter pw = new PrintWriter(new FileWriter(txtFajl, true))){
			pw.println("Broj skrivenih fajlova: " + brojSkrivenihFajlova);
			pw.println("Ukupan broj fajlova: " + ukupanBrojFajlova);
			pw.println("Procenat: " + ((double)brojSkrivenihFajlova/ukupanBrojFajlova*100) + "%");
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl.");
		}
	}
}