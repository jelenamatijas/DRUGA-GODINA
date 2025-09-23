import java.nio.file.Files;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

class Main{
	public static void main(String []args){
		if(args.length != 6){
			System.out.println("GRESKA pogresna format.");
			return;
		}
		
		if(!args[0].equals("-d") || !args[2].equals("-w") || !args[4].equals("-o")){
			System.out.println("GRESKA pogresna format.");
		}
		
		Path root = Paths.get(args[1]);
		if(!Files.exists(root)){
			System.out.println("GRESKA: root folder nije ispravan.");
			return;
		}
		
		String rijec = args[3];
		String opcija = args[5];
		Map<String, Integer> map = new HashMap<>();
		try{
			Files.walk(root).forEach(path -> {
				if(Files.isRegularFile(path)){
					int brojPonavljanja = 0;
					List<String> linije = null;
					try{
						linije = Files.readAllLines(path);
					}catch(IOException e){
						e.printStackTrace();
					}
					for(int j=0;j<linije.size();j++){
						String linija = linije.get(j);
						String[] rijeci = linija.split("\\s+");
						if("MATCH_CASE".equals(opcija)){
							for(int i=0; i<rijeci.length;i++){
								if(rijec.equals(rijeci[i])){
									brojPonavljanja++;
								}
							}
						}else if("IGNORE_CASE".equals(opcija)){
							for(int i=0; i<rijeci.length;i++){
								if(rijec.equalsIgnoreCase(rijeci[i])){
									brojPonavljanja++;
								}
							}
						}else if("LIKE".equals(opcija)){
							for(int i=0; i<rijeci.length;i++){
								if(rijeci[i].contains(rijec)){
									int pocetak = 0, index;
									while((index =rijeci[i].indexOf(rijec, pocetak))!= -1){
										brojPonavljanja++;
										pocetak = index+1;
									}
								}
							}
						}
					}
					if(brojPonavljanja != 0){
						map.put(path.toString(), brojPonavljanja);
					}
				}
			});
		}catch(IOException e){
			e.printStackTrace();
		}
		
		map.forEach((putanja, broj) -> System.out.println(putanja  + " -> " + broj));
	}
}