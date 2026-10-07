import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Osoba extends Thread {
	
	public String ime;
	public String prezime;
	public int godineStarosti;
	public String oblastInteresovanja;
	public String biografija;
	
	public int red;
	
	private static int redniBroj = 1;
	
	public Osoba(int red) {
		this.ime = "Ime" + redniBroj;
		this.prezime = "Prezime" + redniBroj;
		ucitajGodineStarostiIBiografiju();
		this.oblastInteresovanja = "OblastInteresovanja" + redniBroj;
		redniBroj++;
		
		this.red = red;
	}
	
	private void ucitajGodineStarostiIBiografiju() {
		File folder = new File(".");
		
		File[] txtFiles = folder.listFiles((dir, name) -> name.contains(this.prezime + "_" + this.ime) && name.endsWith(".txt"));
		
		for (File file : txtFiles) {
			this.godineStarosti = Integer.parseInt(file.getName().split("_")[2].split("\\.")[0]);
			//System.out.println(this.godineStarosti);
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				this.biografija = br.readLine();
				//System.out.println(this.biografija);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	// TODO: Staviti pravo vrijeme spavanja, trenutna vremena su za brzi test
	@Override
	public void run() {
		boolean dobilaPosao = false;
		// Programer
		if (this instanceof StraniJezikInterface && this instanceof DobraOrganizacijaInterface) {
			for (int i = 0; i < 30; i++) {
				boolean ispisan = false;
				if (Main.mapa[this.red][i] instanceof Oglas) {
					try {
						if (this.godineStarosti < 18) {
							throw new MojIzuzetak("Na oglas naisla osoba mladja od 18 godina!\n");
						}
					} catch (MojIzuzetak ex) {
						ex.printStackTrace();
					}
					
					Oglas oglas = (Oglas) Main.mapa[this.red][i];
					//System.out.println("KLJUCNE RIJECI: " + oglas.kljucneRijeci + "\n");
					//System.out.println("BIOGRAFIJA: " + this.biografija + "\n");
					for (String kljucnaRijec : oglas.kljucneRijeci) {
						if (this.biografija.contains(kljucnaRijec)) {
							dobilaPosao = true;
							break;
						}
					}
					if (dobilaPosao) {
						Main.brojOsobaKojeSuDobilePosao++;
						System.out.println(this + " je DOBIO POSAO.\n");
						break;
					}
					System.out.println(this + " NE ISPUNJAVA KVALIFIKACIJE i nastavlja kretanje. [" + this.red + "][" + i + "]\n");
					ispisan = true;
				}
				if (!ispisan) {
					System.out.println(this + " nastavlja NORMALANO kretanje. [" + this.red + "][" + i + "]\n");
				}
				try {
					sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
		// Istoricar
		else if (this instanceof MaternjiJezikInterface && this instanceof VjestoPisanjeInterface && this instanceof DobraOrganizacijaInterface) {
			for (int i = 0; i < 30; i += 5) {
				boolean ispisan = false;
				if (Main.mapa[this.red][i] instanceof Oglas) {
					try {
						if (this.godineStarosti < 18) {
							throw new MojIzuzetak("Na oglas naisla osoba mladja od 18 godina!\n");
						}
					} catch (MojIzuzetak ex) {
						ex.printStackTrace();
					}
					Oglas oglas = (Oglas) Main.mapa[this.red][i];
					for (String kljucnaRijec : oglas.kljucneRijeci) {
						if (this.biografija.contains(kljucnaRijec)) {
							dobilaPosao = true;
							break;
						}
					}
					if (dobilaPosao) {
						Main.brojOsobaKojeSuDobilePosao++;
						System.out.println(this + " je DOBILA POSAO.\n");
						break;
					}
					System.out.println(this + " NE ISPUNJAVA KVALIFIKACIJE i nastavlja kretanje. [" + this.red + "][" + i + "]\n");
					ispisan = true;
				}
				if (!ispisan) {
					System.out.println(this + " nastavlja NORMALANO kretanje. [" + this.red + "][" + i + "]\n");
				}
				try {
					sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
		// Novinar
		else if (this instanceof MaternjiJezikInterface && this instanceof VjestoPisanjeInterface) {
			for (int i = 0; i < 30; i += 5) {
				boolean ispisan = false;
				if (Main.mapa[this.red][i] instanceof Oglas) {
					try {
						if (this.godineStarosti < 18) {
							throw new MojIzuzetak("Na oglas naisla osoba mladja od 18 godina!\n");
						}
					} catch (MojIzuzetak ex) {
						ex.printStackTrace();
					}
					Oglas oglas = (Oglas) Main.mapa[this.red][i];
					for (String kljucnaRijec : oglas.kljucneRijeci) {
						if (this.biografija.contains(kljucnaRijec)) {
							dobilaPosao = true;
							break;
						}
					}
					if (dobilaPosao) {
						Main.brojOsobaKojeSuDobilePosao++;
						System.out.println(this + " je DOBILA POSAO.\n");
						break;
					}
					System.out.println(this + " NE ISPUNJAVA KVALIFIKACIJE i nastavlja kretanje. [" + this.red + "][" + i + "]\n");
					ispisan = true;
				}
				if (!ispisan) {
					System.out.println(this + " nastavlja NORMALANO kretanje. [" + this.red + "][" + i + "]\n");
				}
				try {
					sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
		}
		// TuristickiVodic
		else if (this instanceof StraniJezikInterface) {
			for (int i = 0; i < 30; i += 3) {
				boolean ispisan = false;
				if (Main.mapa[this.red][i] instanceof Oglas) {
					try {
						if (this.godineStarosti < 18) {
							throw new MojIzuzetak("Na oglas naisla osoba mladja od 18 godina!\n");
						}
					} catch (MojIzuzetak ex) {
						ex.printStackTrace();
					}
					Oglas oglas = (Oglas) Main.mapa[this.red][i];
					for (String kljucnaRijec : oglas.kljucneRijeci) {
						if (this.biografija.contains(kljucnaRijec)) {
							dobilaPosao = true;
							break;
						}
					}
					if (dobilaPosao) {
						Main.brojOsobaKojeSuDobilePosao++;
						System.out.println(this + " je DOBILA POSAO.\n");
						break;
					}
					System.out.println(this + " NE ISPUNJAVA KVALIFIKACIJE i nastavlja kretanje. [" + this.red + "][" + i + "]\n");
					ispisan = true;
				}
				if (!ispisan) {
					System.out.println(this + " nastavlja NORMALANO kretanje. [" + this.red + "][" + i + "]\n");
				}
				try {
					sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
		}
		else {
			System.out.println("GRESKA");
		}
		if (!dobilaPosao) {
			Main.osobeKojeNisuDobilePosao.add(this);
		}
	}
}