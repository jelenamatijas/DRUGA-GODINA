
class IzvrsiAkciju extends Thread{
	String[] podaci;
	
	public IzvrsiAkciju(String akcija){
		podaci = akcija.split(";");
	}
	
	@Override
	public void run(){
		try{
			if("DODAJ".equals(podaci[0])){
				String skladiste = "";
				String kategorija = "";
				int id = 0;
				String naziv = "";
				int kolicina = 0;
				int trajanje = 0;
				try{
					if(podaci.length != 7){
						throw new NepotpunaAkcija("GRESKA: Zadana akcija nije do kraja definisana.");
					}else{
						skladiste = podaci[1];
						kategorija = podaci[2];
						id = Integer.parseInt(podaci[3]);
						naziv = podaci[4];
						kolicina = Integer.parseInt(podaci[5]);
						trajanje = Integer.parseInt(podaci[6]);
					}
				}catch(NepotpunaAkcija e){
					System.out.println(e.getMessage());
				}
				try{
					if(Main.skladista.get(skladiste) == null){
						System.out.println("Skladiste ne postoji: " + skladiste);
					}else{
						Skladiste s = Main.skladista.get(skladiste);
						synchronized(s){
							try{
								if("HRANA".equals(kategorija)){
									if(trajanje>=5 && trajanje<=10){
										s.dodaj(new Hrana(naziv, id, kolicina, trajanje));
										System.out.println("NOVI ARTIKL USPJESNO DODAN: " + (new Hrana(naziv, id, kolicina, trajanje)));
									}else{
										throw new RokTrajanjaException("GRESKA: Rok trajanja nije u granicama [5,10].");
									}
								}else if("ELEKTRONIKA".equals(kategorija)){
									if(trajanje>=1 && trajanje<=5){
										s.dodaj(new Elektronika(naziv, id, kolicina, trajanje));
										System.out.println("NOVI ARTIKL USPJESNO DODAN: " + (new Elektronika(naziv, id, kolicina, trajanje)));
									}else{
										throw new GarancijaException("GRESKA: Garancija nije u granicama [1,5].");
									}
								}
							}catch(RokTrajanjaException e){
								System.out.println(e.getMessage());
							}catch(GarancijaException e){
								System.out.println(e.getMessage());
							}
						}
					}
					
				}catch(Exception e){
					System.out.println("GRESKA prilikom dodavanja artikla u skladiste.");
				}
				
				
			}else if("PREMJESTI".equals(podaci[0])){
				String skladiste1 = "";
				String skladiste2 = "";
				int id = 0;
				int kolicina = 0;
				try{
					if(podaci.length != 5){
						throw new NepotpunaAkcija("GRESKA: Zadana akcija nije do kraja definisana.");
					}else{
						skladiste1 = podaci[1];
						skladiste2 = podaci[2];
						id = Integer.parseInt(podaci[3]);
						kolicina = Integer.parseInt(podaci[4]);
					}
				}catch(NepotpunaAkcija e){
					System.out.println(e.getMessage());
				}
				try{
					if(Main.skladista.get(skladiste1) == null){
						System.out.println("Skladiste ne postoji: " + skladiste1);
					}else if(Main.skladista.get(skladiste2) == null){
						System.out.println("Skladiste ne postoji: " + skladiste2);
					}else{
						Skladiste s1 = Main.skladista.get(skladiste1);
						Skladiste s2 = Main.skladista.get(skladiste2);
						Artikl a = s1.artikli.get(id);
						if(a != null){
							synchronized(s1){
								try{
									s1.ukloni(id, kolicina);
									System.out.println("KOLICINA " + kolicina + " artikla " + id + " USPJESNO UKLONJENA iz skladista " + s1.nazivSkladista);
								}catch(NedovoljnaKolicinaException e){
									System.err.println(e.getMessage());  
								}catch(Exception e){
									System.out.println("GRESKA: prilikom premjestanja IZ SKLADISTE.");
								}
							}
							synchronized(s2){
								try{
									s2.dodaj(a);
									System.out.println("KOLICINA " + kolicina + " artikla " + id + " USPJESNO PREMJESTENA iz skladista " + s1.nazivSkladista + " u skladiste " + s2.nazivSkladista);
								}catch(Exception e){
									System.out.println("GRESKA: prilikom premjestanja U SKLADISTE.");
								}
							}
							
						}else{
							System.out.println("Ne postoji artikl " + id + " u skladistu " + s1.nazivSkladista);
						}
						
					}
					
				}catch(Exception e){
					System.out.println("GRESKA prilikom premijestanja artikla u skladiste.");
				}
				
				
			}
		}catch(Exception e){
			System.out.println("GRESKA pri izvrsavanju akcije.");
			e.printStackTrace();
		}
	}
	
}