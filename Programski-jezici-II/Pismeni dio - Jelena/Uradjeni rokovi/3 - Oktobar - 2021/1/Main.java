import java.util.stream.*; 
import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static Drzava drzava = new Drzava();
	static List<FileWatcher> fileWatchers = new ArrayList<>();
	static Object lock = new Object();
	static Object lock1 = new Object();
	static volatile boolean pauza = false;
	static Random rand = new Random();
	static int faza = 1;
	static Student pobjednik = null;
	
	static public void main(String args[]){
		Path path = Paths.get("drzava");
		if(!path.toFile().exists()){
			try{
				Files.createDirectory(path);
			}catch(IOException e){
				System.out.println("Greska pri kreiranju foldera: "+path);
			}
		}

		Thread konzola = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			boolean radi = true;
			while(radi){
				String line;
				if(scanner.hasNext()){
					line = scanner.nextLine();
					String podaci[] = line.trim().split(" ");
					if("register".equals(podaci[0].toLowerCase())){
						FileWatcher fileWatcher = new FileWatcher(podaci[1]);
						fileWatchers.add(fileWatcher);
						fileWatcher.start();
					}else if("status".equals(podaci[0].toLowerCase())){
						synchronized(Main.lock1){
							pauza = true;
							Main.lock1.notifyAll();
							try{
								Thread.sleep(500);
							}catch(InterruptedException e){
								System.out.println("Greska pri cekanju na pauziranje file watcher-a.");
							}
							
							System.out.println(drzava);
							pauza = false;
							Main.lock1.notifyAll();
						}
						
					}else if("election".equals(podaci[0].toLowerCase())){
						synchronized(Main.lock1){
							pauza = true;
							Main.lock1.notifyAll();
						}
						try{
							Thread.sleep(500);
						}catch(InterruptedException e){
							System.out.println("Greska pri cekanju na pauziranje file watcher-a.");
						}
						//1
						synchronized(Main.lock){
							faza = 1;
							for(String u: drzava.univerziteti.keySet()){
								for(String f:drzava.univerziteti.get(u).fakulteti.keySet()){
									for(Student s: drzava.univerziteti.get(u).fakulteti.get(f).studenti){
										s.prosaoDrugu = false;
										s.prosaoPrvu = false;
										s.brojGlasova = 0;
										(new Thread(s)).start();
									}
								}
							}
							
							try{
								Thread.sleep(10000);
							}catch(InterruptedException e){
								System.out.println("Greska pri cekanju na pauziranje file watcher-a.");
							}
							
							
									
							for(String u: drzava.univerziteti.keySet()){
								for(String f:drzava.univerziteti.get(u).fakulteti.keySet()){
									int maxBr = 0;
									int index = 0;
									for(Student s: drzava.univerziteti.get(u).fakulteti.get(f).studenti){
										if(maxBr<s.brojGlasova){
											maxBr = s.brojGlasova;
											index = drzava.univerziteti.get(u).fakulteti.get(f).studenti.indexOf(s);
										}
										s.brojGlasova = 0;
									}
									drzava.univerziteti.get(u).fakulteti.get(f).studenti.get(index).prosaoPrvu = true;
								}
							}
							//2
							faza  =2;
							for(String u: drzava.univerziteti.keySet()){
								for(String f:drzava.univerziteti.get(u).fakulteti.keySet()){
									for(Student s: drzava.univerziteti.get(u).fakulteti.get(f).studenti){
										if(s.prosaoPrvu){
											(new Thread(s)).start();
										}
									}
								}
							}
							
							try{
								Thread.sleep(10000);
							}catch(InterruptedException e){
								System.out.println("Greska pri cekanju na pauziranje file watcher-a.");
							}
							for(String u: drzava.univerziteti.keySet()){
								
								List<Student> kandidati = Main.drzava.univerziteti.get(u).fakulteti.values().stream()
									.flatMap(fa -> fa.studenti.stream())
									.filter(s -> s.prosaoPrvu)
									.toList();
								
								int maxBr = 0;
								int index = 0;
								for(Student s: kandidati){
									if(s.prosaoPrvu && maxBr<s.brojGlasova){
										maxBr = s.brojGlasova;
										index = kandidati.indexOf(s);
										
									}
									s.brojGlasova = 0;
								}
								kandidati.get(index).prosaoDrugu = true;
								
							}
							
							//3
							faza = 3;
							for(String u: drzava.univerziteti.keySet()){
								for(String f:drzava.univerziteti.get(u).fakulteti.keySet()){
									for(Student s: drzava.univerziteti.get(u).fakulteti.get(f).studenti){
										if(s.prosaoDrugu){
											(new Thread(s)).start();
										}
									}
								}
							}
							
							try{
								Thread.sleep(10000);
							}catch(InterruptedException e){
								System.out.println("Greska pri cekanju na pauziranje file watcher-a.");
							}
							
							int index = 0, maxBr=0;
							String un="", fa="";
							List<Student> kandidati = Main.drzava.univerziteti.values().stream()
								.flatMap(unv -> unv.fakulteti.values().stream())
								.flatMap(fakl -> fakl.studenti.stream())
								.filter(s -> s.prosaoDrugu) 
								.toList();
								
							for(Student s: kandidati){
								if(s.prosaoDrugu && maxBr<s.brojGlasova){
									maxBr = s.brojGlasova;
									index = kandidati.indexOf(s);
									
								}
								s.brojGlasova = 0;
							}
							System.out.println("Pobjednik izbora: " + kandidati.get(index));
						}
						
						synchronized(Main.lock1){
							pauza = false;
							Main.lock1.notifyAll();
						}
						faza = 1;
					}else if("save".equals(podaci[0].toLowerCase())){
						synchronized(Main.lock1){
							pauza = true;
							Main.lock1.notifyAll();
						}
						
						if(pobjednik!=null){
							synchronized(lock){
								
								List<Student> kandidati = new ArrayList<>();
								
								for(String u: drzava.univerziteti.keySet()){
								
									kandidati.addAll(Main.drzava.univerziteti.get(u).fakulteti.values().stream()
										.flatMap(fa -> fa.studenti.stream())
										.filter(s -> s.prosaoPrvu)
										.toList());
								}
								
								try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("drzava"+File.separator+"univerzitet.ser"))){
									out.writeObject(kandidati);
								}catch(IOException e){
									System.out.println("Greska pri upisu u fajl.");
								}
								
								kandidati = Main.drzava.univerziteti.values().stream()
									.flatMap(unv -> unv.fakulteti.values().stream())
									.flatMap(fakl -> fakl.studenti.stream())
									.filter(s -> s.prosaoDrugu) 
									.toList();
								
								try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("drzava"+File.separator+"drzava.ser"))){
									out.writeObject(kandidati);
								}catch(IOException e){
									System.out.println("Greska pri upisu u fajl.");
								}
								
								try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("drzava"+File.separator+"pobjednik.ser"))){
									out.writeObject(pobjednik);
								}catch(IOException e){
									System.out.println("Greska pri upisu u fajl.");
								}
								
								
							}
						}
						
						synchronized(Main.lock1){
							pauza = false;
							Main.lock1.notifyAll();
						}
						
					}else if("stop".equals(podaci[0].toLowerCase())){
						synchronized(Main.lock1){
							pauza = false;
							Main.lock1.notifyAll();
						}
						for(FileWatcher fw:fileWatchers){
							fw.radi = false;
						}
						radi = false;
					}
				}
			}
		});
		
		konzola.start();
		
		
	}
}