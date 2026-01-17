import java.util.*;

public class Main {
    static volatile boolean pauzirano = false;
	static volatile boolean kraj = false;
    static long vrijemePauze = 0;
    static long pocetakPauze = 0;
    
    public static void main(String[] args) {
        
        
        // Kreiranje buffer-a i teleekrana
        BufferPoruka buffer = new BufferPoruka();
        Teleekran teleekran = new Teleekran();
        Thread teleekranThread = new Thread(teleekran);
        teleekranThread.start();
        
        
        List<Radnik> radnici = new ArrayList<>();
        radnici.add(new Radnik("Marko", "Markovic", "001", 5000, buffer, teleekran));
        radnici.add(new Radnik("Ana", "Anic", "002", 4500, buffer, teleekran));
        radnici.add(new Radnik("Petar", "Petrovic", "003", 5500, buffer, teleekran));
        
        
        Nadzornik nadzornik = new Nadzornik("Nikola", "Nikolic", "100", buffer, teleekran, radnici);
        Prepravljac prepravljac = new Prepravljac("Milan", "Milankovic", "200", buffer, teleekran);
        
        
        teleekran.prikaz("=== POKRETANJE RADNIKA ===");
        List<Thread> radniciThreads = new ArrayList<>();
        for (Radnik r : radnici) {
            Thread t = new Thread(r);
            radniciThreads.add(t);
            t.start();
            teleekran.prikaz("Pokrenut radnik: " + r.ime + " " + r.prezime);
        }
        
        teleekran.prikaz("=== POKRETANJE NADZORNIKA I PREPRAVLJACA ===");
        Thread nadzornikThread = new Thread(nadzornik);
        nadzornikThread.start();
        
        Thread prepravljacThread = new Thread(prepravljac);
        prepravljacThread.start();
        
        long pocetakSimulacije = System.currentTimeMillis();
        
        System.out.println("\n=== BIG BROTHER SIMULACIJA POKRENUTA ===");
        System.out.println("Komande: PAUZA, NASTAVAK, ZAUSTAVI\n");
        
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        
        
        Thread ekran = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.print("> ");
				String unos = scanner.nextLine().trim();
				
				if (unos.equalsIgnoreCase("ZAUSTAVI")) {
					kraj = true;
					break;
				} else if (unos.equalsIgnoreCase("PAUZA")) {
					if (!pauzirano) {
						pauzirano = true;
						pocetakPauze = System.currentTimeMillis();
						System.out.println("*** SIMULACIJA PAUZIRANA ***");
						
						
						new Thread(() -> {
							try {
								Thread.sleep(60000); 
								if (pauzirano) {
									System.out.println("\n*** PAUZA PREDUGA - SIMULACIJA ZAUSTAVLJENA ***");
									System.exit(0);
								}
							} catch (InterruptedException e) {
							}
						}).start();
					}
				} else if (unos.equalsIgnoreCase("NASTAVAK")) {
					if (pauzirano) {
						pauzirano = false;
						vrijemePauze += System.currentTimeMillis() - pocetakPauze;
						System.out.println("*** SIMULACIJA NASTAVLJENA ***");
					}
				}
			}
			scanner.close();
		});
        
		ekran.start();
		while(!kraj){
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){
				
			}
		}
       
        System.out.println("\n*** ZAUSTAVLJAM SIMULACIJU... ***");
        for (Radnik r : radnici) {
            r.zaustavi();
        }
        nadzornik.zaustavi();
        prepravljac.zaustavi();
        
        try {
            for (Thread t : radniciThreads) {
                t.join();
            }
            nadzornikThread.join();
            prepravljacThread.join();
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
        }
        
        teleekran.zaustavi();
        try {
            teleekranThread.join();
        } catch (InterruptedException e) {}
        
        
        long trajanje = (System.currentTimeMillis() - pocetakSimulacije - vrijemePauze) / 1000;
        
        System.out.println("\n=== KRAJ SIMULACIJE ===");
        System.out.println("Trajanje simulacije: " + trajanje + " sekundi");
        System.out.println("\nSpisak radnika:");
        for (Radnik r : radnici) {
            System.out.println(r);
        }
        
        
    }
}