class Ratnik extends Thread{
	String ime;
	double snaga;
	int x,y;
	boolean ziv = true;
	static int idZagonetke = 1;
	boolean feniksUnisten = false;
	
	Ratnik(String ime, double snaga, int x, int y){
		this.ime = ime;
		this.snaga = snaga;
		this.x = x;
		this.y = y;
	}
	
	int getNewX(){return 0;}
	int getNewY(){return 0;}
	
	boolean krajReda(int newX){return true;}
	
	synchronized void unistiSfingu(int k1, int k2, int k3, int k4){
		System.out.println("Sfinga postavlja zagonetku: ZAGONETKA_" + idZagonetke);
		if(Main.rand.nextBoolean()){
			System.out.println("Covjek je pogodio zagonetku. Sfinga je unistena.");
			Main.mapa[k1][k2].ziv = false;
			Main.mapa[k1][k2] = null;
			Main.unistenihZivotinja++;
			
		}else{
			System.out.println("Covjek nije pogodio zagonetku. Covjek je unisten.");
			Main.mapa[k3][k4].ziv = false;
			Main.mapa[k3][k4] = null;
			Main.unistenihLjudi++;
		}
		idZagonetke++;
	}
	
	synchronized void unistiAmuta(int k1, int k2, int k3, int k4, boolean dobar){
		if(dobar){
			System.out.println("Covjek je dobar. Amut je unisten.");
			Main.mapa[k1][k2].ziv = false;
			Main.mapa[k1][k2] = null;
			Main.unistenihZivotinja++;
		}else{
			System.out.println("Covjek je zao. Covjek je unisten.");
			Main.mapa[k3][k4].ziv = false;
			Main.mapa[k3][k4] = null;
			Main.unistenihLjudi++;
		}
	}
	
	synchronized void unistiPegaza(int k1, int k2, int k3, int k4, boolean imaAmajliju, double razlikaSnaga){
		if(imaAmajliju){
			System.out.println("Covjek je ima amajliju. Pegaz je unisten.");
			Main.mapa[k1][k2].ziv = false;
			Main.mapa[k1][k2] = null;
			Main.unistenihZivotinja++;
		}else{
			if(razlikaSnaga == 0){
				if(Main.rand.nextBoolean()){
					System.out.println("Covjek je izgubio od pegaza. Covjek je unisten.");
					Main.mapa[k3][k4].ziv = false;
					Main.mapa[k3][k4] = null;
					Main.unistenihLjudi++;
				}else{
					System.out.println("Covjek je pobijedio. Pegaz je unisten.");
					Main.mapa[k1][k2].ziv = false;
					Main.mapa[k1][k2] = null;
					Main.unistenihZivotinja++;
				}
			}else if(razlikaSnaga<0){
				System.out.println("Covjek ima manju snagu. Covjek je unisten.");
				Main.mapa[k3][k4].ziv = false;
				Main.mapa[k3][k4] = null;
				Main.unistenihLjudi++;
			}else{
				System.out.println("Covjek ima vecu snagu. Pegaz je unisten.");
				Main.mapa[k1][k2].ziv = false;
				Main.mapa[k1][k2] = null;
				Main.unistenihZivotinja++;
			}
		}
	}
	
	synchronized void unistiFeniksa(int k1, int k2){
		System.out.println("Feniks je unisten.");
		Main.mapa[k1][k2].ziv = false;
		Main.mapa[k1][k2].feniksUnisten = true;
		Main.mapa[k1][k2] = null;
		feniksUnisten = true;
		Main.unistenihZivotinja++;
	}
	
	
	synchronized void provjeriSusjednog(int k1, int k2){
		if(this instanceof CovjekInterface && Main.mapa[k1][k2] instanceof SfingaInterface){
			unistiSfingu(k1, k2, this.x, this.y);
		}else if(this instanceof SfingaInterface && Main.mapa[k1][k2] instanceof CovjekInterface){
			unistiSfingu(x, y, k1, k2);
		}else if(this instanceof CovjekInterface && Main.mapa[k1][k2] instanceof CudovisteInterface){
			unistiAmuta(k1, k2, x, y, ((CovjekInterface) this).getDobar());
		}else if(this instanceof CudovisteInterface && Main.mapa[k1][k2] instanceof CovjekInterface){
			unistiAmuta(x, y, k1, k2, ((CovjekInterface) Main.mapa[k1][k2]).getDobar());
		}else if(this instanceof CovjekInterface && Main.mapa[k1][k2] instanceof PegazInterface){
			unistiPegaza(k1, k2, x, y, ((CovjekInterface) this).getImaAmajliju(), snaga - ((PegazInterface) Main.mapa[k1][k2]).getSnaga());
		}else if(this instanceof PegazInterface && Main.mapa[k1][k2] instanceof CovjekInterface){
			unistiPegaza(x, y, k1, k2, ((CovjekInterface) Main.mapa[k1][k2]).getImaAmajliju(), ((CovjekInterface) Main.mapa[k1][k2]).getSnaga() - snaga);
		}else if(this instanceof CovjekInterface && Main.mapa[k1][k2] instanceof FeniksInterface){
			unistiFeniksa(k1, k2);
		}else if(this instanceof FeniksInterface && Main.mapa[k1][k2] instanceof CovjekInterface){
			unistiFeniksa(x, y);
		}
	}
	
	public void run(){
		while(Main.radi && ziv){
			int newX, newY;
			synchronized(Main.lock){
				if(!ziv || !Main.radi){
					break;
				}
				do{
					newX = this.getNewX();
					newY = this.getNewY();
					if(this.krajReda(newX)){
						Main.radi = false;
						Main.lock.notifyAll();
						System.out.println(this + " Ratnik je zavrsio.");
						break;
					}
				}while(Main.mapa[newX][newY] != null);
				if(!Main.radi || !ziv){
					break;
				}
				
				snaga = snaga - (snaga*0.07);
				
				if(snaga <= 0){
					Main.radi = false;
					Main.lock.notifyAll();
					ziv = false;
					Main.mapa[x][y] = null;
					System.out.println(this + " Ratnik je zavrsio zbog nedovoljne snage.");
					break;
				}
				
				Main.mapa[newX][newY] = this;
				Main.mapa[x][y] = null;
				
				System.out.println(this + " Ratnik pomjeren sa pozicije [" + x + "," + y + "] na poziciju [" + newX + "," + newY + "].");
				x=newX;
				y = newY;
				
				if((x-1) >=0 && Main.mapa[x-1][y] != null){
					provjeriSusjednog(x-1, y);
				}
				if((x+1) < Main.REDOVI && Main.mapa[x+1][y] != null){
					provjeriSusjednog(x+1, y);
				}
				if((y-1) >=0 && Main.mapa[x][y-1] != null){
					provjeriSusjednog(x, y-1);
				}
				if((y+1) <Main.KOLONE && Main.mapa[x][y+1] != null){
					provjeriSusjednog(x, y+1);
				}
				if(!ziv){
					break;
				}
			}
			
			if(this instanceof FeniksInterface && feniksUnisten){
				feniksUnisten = false;
				try{
					sleep(10000);
				}catch(InterruptedException e){
					System.out.println("Greska pri cekanju na kreiranje feniksa.");
				}
				
				synchronized(Main.lock){
					do{
						x = Main.rand.nextInt(0, Main.REDOVI);
						y = Main.rand.nextInt(Main.KOLONE/2, Main.KOLONE);
					}while(Main.mapa[x][y] != null);
					
					Main.mapa[x][y] = this;
					ziv = true;
					Main.radi = true;
					System.out.println("Feniks je kreiran na poziciji [" + x + "," + y + "].");
				}
			}
			
			if(!ziv){
				break;
			}
			
			try{
				sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Greska pri pauziranju ratnika.");
			}
		}
		System.out.println(this + " -> THREAD EXIT");
	}
}