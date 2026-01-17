import java.util.*;
import java.util.stream.*;

class Main{
	public static Random rand = new Random();
	
	static public void main(String []args){
		if(args.length != 2){
			System.out.println("Pogresan format.");
			return;
		}
		
		int N = Integer.parseInt(args[0]); // duzina; broj redova
		int M = Integer.parseInt(args[1]); // sirina; broj kolona
		if(N > 15 || N < 3 || M > 15 || M < 3){
			System.out.println("Dimenzije ploce ne odgovaraju.");
			return;
		}
		
		String ploca[][] = new String[N][M];
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				ploca[i][j] = null;
			}
		}
		
		List<Kockica> kockice = new ArrayList<>();
		for(int i=0;i<20;i++){
			kockice.add(new Pravougaona(rand.nextInt(2,8), rand.nextInt(2,8)));
			kockice.add(new Kvadratna(rand.nextInt(2,8)));
			kockice.add(new Cilindricna(rand.nextInt(2,8)));
		}
		//kockice.stream().forEach((Kockica k) -> System.out.println(k));
		
		int nepostavljene = 0;
		for(Kockica k : kockice){
			if(k instanceof GradjenaInterface && k instanceof RastavljenaInterface && k instanceof RotiranaInterface){
				//pravougaonik
				int sirina = k.getSirina();
				int duzina = k.getDuzina();
				if((sirina >M && sirina >N) || (duzina>M && duzina>N)){
					System.out.println(k + " nije moguce postaviti na plocu.");
					nepostavljene++;
				}else{
					boolean postavljena = false;
					for(int i=0;i<N && !postavljena;i++){
						for(int j=0;j<M && !postavljena;j++){
							
							if(ploca[i][j] == null){
								int x0 = i; //donji lijevi
								int y0 = j;
								
								int x1 = i+duzina; //gornji desni
								int y1 = j+sirina;
								
								boolean prazno = true;
								if(x1<=N && y1 <= M){
									for(int kk = x0;kk<x1 && prazno;kk++){
										for(int m = y0;m<y1 && prazno;m++){
											if(ploca[kk][m] != null){
												prazno = false;
											}
										}
									}
									if(!prazno){
										int rx1 = i+sirina;
										int ry1 = j+duzina;
										if(rx1<=N && ry1 <= M){
											prazno = true;
											for(int kk = x0;kk<rx1 && prazno;kk++){
												for(int m = y0;m<ry1 && prazno;m++){
													if(ploca[kk][m] != null){
														prazno = false;
													}
												}
											}
											
											if(prazno){
												for(int kk = x0;kk<rx1;kk++){
													for(int m = y0;m<ry1;m++){
														ploca[kk][m] = new String("P");
													}
												}
												System.out.println(k + " postavljena na plocu");
												postavljena = true;
											}
										}
									}else{
										for(int kk = x0;kk<x1;kk++){
											for(int m = y0;m<y1;m++){
												ploca[kk][m] = new String("P");
											}
										}
										System.out.println(k + " postavljena na plocu");
										postavljena = true;
									}
								}
							}
						}
					}
					if(!postavljena){
						System.out.println(k + " nije moguce postaviti na plocu");
						nepostavljene++;
					}
				}
				
			}else{
				//kvadrat i cilindar
				int duzina = 0;
				if( k instanceof GradjenaInterface && k instanceof RastavljenaInterface){
					duzina = k.getDuzina();
				}else{
					duzina = k.getPoluprecnik();
				}
				
				if(duzina>M || duzina>N){
					System.out.println(k + " nije moguce postaviti na plocu.");
					nepostavljene++;
				}else{
					boolean postavljena = false;
					for(int i=0;i<N && !postavljena;i++){
						for(int j=0;j<M && !postavljena;j++){
							
							if(ploca[i][j] == null){
								int x0 = i; //donji lijevi
								int y0 = j;
								
								int x1 = i+duzina; //gornji desni
								int y1 = j+duzina;
								
								boolean prazno = true;
								if(x1<=N && y1 <= M){
									for(int kk = x0;kk<x1 && prazno;kk++){
										for(int m = y0;m<y1 && prazno;m++){
											if(ploca[kk][m] != null){
												prazno = false;
											}
										}
									}
									if(prazno){
										for(int kk = y0;kk<y1;kk++){
											for(int m = x0;m<x1;m++){
												if(k instanceof GradjenaInterface && k instanceof RastavljenaInterface){
													ploca[kk][m] = new String("K");
												}else{
													ploca[kk][m] = new String("C");
												}
											}
										}
										System.out.println(k + " postavljena na plocu");
										postavljena = true;
									}
								}
							}
						}
					}
					if(!postavljena){
						System.out.println(k + " nije moguce postaviti na plocu");
						nepostavljene++;
					}
				}
				
			}
		}
		
		System.out.println("Broj postavljenih kockica: " + (60-nepostavljene) + " [" + (nepostavljene*100.0/60) + "% nepostavljenih]");
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				System.out.print((ploca[i][j] == null ? "N" : ploca[i][j]) + " ");
			}
			System.out.println();
		}
	}
}