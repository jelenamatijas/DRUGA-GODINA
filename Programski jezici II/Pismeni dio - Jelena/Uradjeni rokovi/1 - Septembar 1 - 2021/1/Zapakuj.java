

class Zapakuj extends Thread{
	Zapakuj(){}
	
	public void run(){
		
		synchronized(Main.lock){
			if(!Main.razvrstano){
				try{
					System.out.println("Pakovanje ceka.");
					Main.lock.wait();
				}catch(InterruptedException e){
					System.out.println("Nit za pakovanje je prekinuta u cekanju na razvrstane posiljke.");
				}
			}
			System.out.println("Pakovanje pocinje.");
			while(!Main.pisma.isEmpty() || !Main.vrijednosne.isEmpty() || !Main.razglednice.isEmpty()){
				Paket paket = new Paket();
				
				for(int i=0;i<5;i++){
					if(!Main.pisma.isEmpty()){
						paket.add(Main.pisma.remove(0));
					}
					if(!Main.vrijednosne.isEmpty()){
						paket.add(Main.vrijednosne.remove(0));
					}
					if(!Main.razglednice.isEmpty()){
						paket.add(Main.razglednice.remove(0));
					}
				}
				Main.paketi.add(paket);
			}
			Main.zapakovano = true;
			Main.lock.notifyAll();
		}
		System.out.println("Pakovanje zavrseno.");
	}
}