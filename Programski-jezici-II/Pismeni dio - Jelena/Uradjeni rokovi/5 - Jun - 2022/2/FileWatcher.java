import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWatcher extends Thread{
	static Map<Integer, Predmet> predmeti = new HashMap<>();
	static Map<Integer, Student> studenti = new HashMap<>();
	static Map<Integer, Profesor> profesori = new HashMap<>();
	
	FileWatcher(){}
	
	public void run(){
		while(Main.radi){
			try{
				Files.walk(Main.directoryPath).forEach(file -> {
					String fileName = file.toString().toLowerCase();
					if(fileName.contains("predmet")){
						
						try{
							String predmetPodaci = Files.readAllLines(file).get(0);
							String podaci[] = predmetPodaci.trim().split(" ");
							if(predmeti.get(Integer.parseInt(podaci[1]))==null){
								predmeti.put(Integer.parseInt(podaci[1]), new Predmet(podaci[0], Integer.parseInt(podaci[1])));
								Path path = Main.directoryPath.resolve(podaci[0] + "_" + podaci[1]);
								try{
									Files.createDirectory(path);
									Files.createDirectory(path.resolve("studenti"));
									Files.createDirectory(path.resolve("profesori"));
									System.out.println(path + " kreiran.");
									try(PrintWriter pw = new PrintWriter(new FileWriter(path.toString() + "\\" + podaci[0] + ".txt"))){
										pw.println(predmetPodaci);
									}catch(Exception e){
										System.out.println("GRESKA pri upisu fajla " + path.toString() + "\\" + podaci[0] + ".txt");
									} 
									
								}catch(Exception e){
									System.out.println("GRESKA pri kreiranju fajla " + path + e);
								}
							}
						}catch(IOException e){
							System.out.println("GRESKA pri citanju fajla " + file);
						}
						
					}else if(fileName.contains("student") && fileName.contains(".txt")){
						
						try{
							String studentiPodaci = Files.readAllLines(file).get(0);
							String podaci[] = studentiPodaci.trim().split(" ");
							List<Integer> identifikatori = new ArrayList<>();
							for(int i=3;i<podaci.length;i++){
								identifikatori.add(Integer.parseInt(podaci[i]));
							}
							if(studenti.get(Integer.parseInt(podaci[2])) == null){
								studenti.put(Integer.parseInt(podaci[2]), new Student(podaci[0], podaci[1], Integer.parseInt(podaci[2]), identifikatori));
								Path path = Main.directoryPath.resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2]);
								try{
									Files.createDirectory(path);
									System.out.println(path + " kreiran.");
									path = path.resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2] + ".txt");
									try(ObjectOutputStream pw = new ObjectOutputStream(new FileOutputStream(path.toFile()))){
										pw.writeObject(studenti.get(Integer.parseInt(podaci[2])));
									}catch(Exception e){
										System.out.println("GRESKA pri upisu fajla " + path);
										e.printStackTrace();
									} 
									
								}catch(Exception e){
									System.out.println("GRESKA pri kreiranju fajla " + path + e);
								}
							}
							for(Integer id : identifikatori){
								if(predmeti.get(id) != null){
									Predmet p = predmeti.get(id);
									Path path = Main.directoryPath.resolve(p.naziv + "_" + p.id).resolve("studenti").resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2] + ".txt");
									File f = path.toFile();
									try{
										if(!f.exists()){
											Files.createFile(path);
											System.out.println(path + " kreiran.");
											try(PrintWriter pw = new PrintWriter(new FileWriter(path.toString()))){
												pw.println(studentiPodaci);
											}catch(Exception e){
												System.out.println("GRESKA pri upisu fajla " + path);
											} 
										}
									}catch(Exception e){
										System.out.println("GRESKA pri kreiranju fajla " + path + e);
									}
								}
							}
							
						}catch(IOException e){
							System.out.println("GRESKA pri citanju fajla " + file);
						}
						
					}else if(fileName.contains("profesor") && fileName.contains(".txt")){
						
						try{
							String profesorPodaci = Files.readAllLines(file).get(0);
							String podaci[] = profesorPodaci.trim().split(" ");
							List<Integer> identifikatori = new ArrayList<>();
							for(int i=3;i<podaci.length;i++){
								identifikatori.add(Integer.parseInt(podaci[i]));
							}
							if(profesori.get(Integer.parseInt(podaci[2])) == null){
								profesori.put(Integer.parseInt(podaci[2]), new Profesor(podaci[0], podaci[1], Integer.parseInt(podaci[2]), identifikatori));
								Path path = Main.directoryPath.resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2]);
								try{
									Files.createDirectory(path);
									System.out.println(path + " kreiran.");
									path = path.resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2] + ".txt");
									try(ObjectOutputStream pw = new ObjectOutputStream(new FileOutputStream(path.toFile()))){
										pw.writeObject(profesori.get(Integer.parseInt(podaci[2])));
									}catch(Exception e){
										System.out.println("GRESKA pri upisu fajla " + path);
										e.printStackTrace();
									} 
									
								}catch(Exception e){
									System.out.println("GRESKA pri kreiranju fajla " + path + e);
								}
							}
							for(Integer id : identifikatori){
								if(predmeti.get(id) != null){
									Predmet p = predmeti.get(id);
									Path path = Main.directoryPath.resolve(p.naziv + "_" + p.id).resolve("profesori").resolve(podaci[0] + "_" + podaci[1] + "_" + podaci[2] + ".txt");
									File f = path.toFile();
									try{
										if(!f.exists()){
											Files.createFile(path);
											System.out.println(path + " kreiran.");
											try(PrintWriter pw = new PrintWriter(new FileWriter(path.toString()))){
												pw.println(profesorPodaci);
											}catch(Exception e){
												System.out.println("GRESKA pri upisu fajla " + path);
											} 
										}
									}catch(Exception e){
										System.out.println("GRESKA pri kreiranju fajla " + path + e);
									}
								}
							}
							
						}catch(IOException e){
							System.out.println("GRESKA pri citanju fajla " + file);
						}
					}
				});
			}catch(IOException e){
				System.out.println("GRESKA pri prolasku kroz direktorijum.");
			}
		}
		System.out.println("SIMULACIJA ZAVRSENA.");
	}
}