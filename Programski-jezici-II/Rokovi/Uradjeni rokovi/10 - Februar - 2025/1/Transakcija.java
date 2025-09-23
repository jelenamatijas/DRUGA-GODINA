import java.util.*;

class Transakcija extends Thread{
	long pocetak;
	String vrsta;
	String imeKorisnika1;
	int iznos;
	String imeKorisnika2;
	
	public Transakcija(String vrsta, String imeKorisnika1, int iznos, String imeKorisnika2){
		this.vrsta = vrsta;
		this.imeKorisnika1 = imeKorisnika1;
		this.iznos = iznos;
		this.imeKorisnika2 = imeKorisnika2;
		pocetak = 0;
	}
	
	@Override
	public void run(){
		pocetak = System.currentTimeMillis();
		if(vrsta.equals("u")){
			int index = Main.rand.nextInt(0, Main.klijenti.get(imeKorisnika1).racuni.size());
			try{
				Main.banka.uplati(imeKorisnika1, index, iznos);
				System.out.println("Izvrsena uplata od " + iznos + " na racun klijenta " + imeKorisnika1);
			}catch(MyException e){
				System.out.println(e.getMessage());
			}
		}else if(vrsta.equals("i")){
			for(int i=0; i<Main.klijenti.get(imeKorisnika1).racuni.size();i++){
				try{
					Main.banka.isplati(imeKorisnika1, i, iznos);
					System.out.println("Izvrsena isplata od " + iznos + " sa racuna klijenta " + imeKorisnika1);
				}catch(MyException e){
					System.out.println(e.getMessage());
				}
				}
		}else if(vrsta.equals("p")){
			try{
				Main.banka.prenesi(imeKorisnika1,imeKorisnika2, iznos);
			}catch(MyException e){
				System.out.println(e.getMessage());
			}
		}
		
	}
}