import java.util.*;
import java.io.*;

public class Salter extends Thread {
	
	private static String txtFajl = "greskeTxtFajl.txt";
	
	String id;
	
	private static int redniBroj = 1;
	Random radnom = new Random();
	
	public Salter() {
		id = "ID" + redniBroj++;
	}
	
	public void serijalizuj(Osoba osoba) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(osoba.ime + "_" + id));
			oos.writeObject(osoba);
			oos.close();
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom serijalizacije");
			return;
		}
	}
	
	public void zapisiGresku(Osoba osoba) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(txtFajl, true));
			pw.println(osoba);
			pw.close();
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom pisanja u TXT fajl");
			return;
		}
	}
	
	@Override
	public void run() {
		while (Main.redOsoba.peek() != null) {
			Osoba osoba = Main.redOsoba.poll();
			if (osoba instanceof FizickoLice) {
				FizickoLice fizickoLice = (FizickoLice) osoba;
				int randomUsluga = radnom.nextInt(3);
				int taksa = 0;
				if (fizickoLice.vozilo.equals("AUTOBUS")) {
					taksa = 80;
				} else if (fizickoLice.vozilo.equals("KAMION")) {
					taksa = 100;
				}
				if (randomUsluga == 0) {
					int ukupnaCijena = taksa + 100;
					if (fizickoLice.novac >= ukupnaCijena) {
						fizickoLice.novac -= ukupnaCijena;
						System.out.println(fizickoLice + " platio USLUGU: Obicna registracija" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + fizickoLice.vozilo);
						synchronized (Main.lockObject) {
							serijalizuj(fizickoLice);
						}
					} else {
						System.out.println(fizickoLice + " NEMA para");
						synchronized (Main.lockObject) {
							zapisiGresku(fizickoLice);
						}
					}
				} else if (randomUsluga == 1) {
					int ukupnaCijena = taksa + 200;
					if (fizickoLice.novac >= ukupnaCijena) {
						fizickoLice.novac -= ukupnaCijena;
						System.out.println(fizickoLice + " platio USLUGU: Registracija plus kasko" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + fizickoLice.vozilo);
						synchronized (Main.lockObject) {
							serijalizuj(fizickoLice);
						}
					} else {
						synchronized (Main.lockObject) {
							System.out.println(fizickoLice + " NEMA para");
							zapisiGresku(fizickoLice);
						}
					}
				} else {
					int ukupnaCijena = taksa + 250;
					if (fizickoLice.novac >= ukupnaCijena) {
						fizickoLice.novac -= ukupnaCijena;
						System.out.println(fizickoLice + " platio USLUGU: Registracija plus kasko" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + fizickoLice.vozilo);
						synchronized (Main.lockObject) {
							serijalizuj(fizickoLice);
						}
					} else {
						synchronized (Main.lockObject) {
							System.out.println(fizickoLice + " NEMA para");
							zapisiGresku(fizickoLice);
						}
					}
				}
			} else {
				PravnoLice pravnoLice = (PravnoLice) osoba;
				for (int i = 0; i < pravnoLice.vozila.size(); i++) {
					int randomUsluga = radnom.nextInt(3);
					int taksa = 0;
					if (pravnoLice.vozila.get(i).equals("AUTOBUS")) {
						taksa = 80;
					} else if (pravnoLice.vozila.get(i).equals("KAMION")) {
						taksa = 100;
					}
					if (randomUsluga == 0) {
						int ukupnaCijena = taksa + 100;
						if (pravnoLice.novac >= ukupnaCijena) {
							pravnoLice.novac -= ukupnaCijena;
							System.out.println(pravnoLice + " platio USLUGU: Obicna registracija" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + pravnoLice.vozila.get(i));
							synchronized (Main.lockObject) {
								serijalizuj(pravnoLice);
							}
						} else {
							synchronized (Main.lockObject) {
								System.out.println(pravnoLice + " NEMA para");
								zapisiGresku(pravnoLice);
							}
						}
					} else if (randomUsluga == 1) {
						int ukupnaCijena = taksa + 200;
						if (pravnoLice.novac >= ukupnaCijena) {
							pravnoLice.novac -= ukupnaCijena;
							System.out.println(pravnoLice + " platio USLUGU: Registracija plus kasko" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + pravnoLice.vozila.get(i));
							synchronized (Main.lockObject) {
								serijalizuj(pravnoLice);
							}
						} else {
							synchronized (Main.lockObject) {
								System.out.println(pravnoLice + " NEMA para");
								zapisiGresku(pravnoLice);
							}
						}
					} else {
						int ukupnaCijena = taksa + 250;
						if (pravnoLice.novac >= ukupnaCijena) {
							pravnoLice.novac -= ukupnaCijena;
							System.out.println(pravnoLice + " platio USLUGU: Registracija plus kasko" + ((taksa > 0) ? (" + TAKSU: " + taksa) : " ") + "na salteru: " + id + " za vozilo: " + pravnoLice.vozila.get(i));
							synchronized (Main.lockObject) {
								serijalizuj(pravnoLice);
						}
						} else {
							synchronized (Main.lockObject) {
								System.out.println(pravnoLice + " NEMA para");
								zapisiGresku(pravnoLice);
							}
						}
					}
				}
			}
			
			try {
				sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}