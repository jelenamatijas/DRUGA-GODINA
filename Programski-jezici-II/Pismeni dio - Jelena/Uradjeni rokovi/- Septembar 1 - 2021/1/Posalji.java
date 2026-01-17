

class Posalji extends Thread{ 
	Posalji(){}
	
	public void run(){
		synchronized(Main.lock){
			if(!Main.zapakovano){
				try{
					System.out.println("Slanje ceka.");
					Main.lock.wait();
				}catch(InterruptedException e){
					System.out.println("Nit za slanje je prekinuta pri cekanju na pakete.");
				}
			}
			
			System.out.println("Slanje pocinje sa radom.");
			for(Paket paket : Main.paketi){
				for(int i=1;i<=paket.posiljke.size();i++){
					if(paket.tezina > 15){
						if(i%5==0){
							try{
								sleep(3000);
							}catch(InterruptedException e){
								System.out.println("Prekid pri cekanju na slanje paketa.");
							}
						}
					}
					Posiljka posiljka = (Posiljka) paket.posiljke.get(i-1);
					String sadrzaj = posiljka.toString();
					if(posiljka.tezina>15){
						int x = (int)(sadrzaj.length() / 3.0);
						String[] print = new String[3];
						print[0] = sadrzaj.substring(0, x);
						print[1] = sadrzaj.substring(x+1, 2*x);
						print[2] = sadrzaj.substring(2*x+1);
						for(int j=0;j<3;j++){
							System.out.println(print[j]);
							try{
								sleep(3000);
							}catch(InterruptedException e){
								System.out.println("Prekid pri cekanju na slanje paketa.");
							}
						}
					}else{
						System.out.println(posiljka);
					}
				}
			}
		}
	}
}