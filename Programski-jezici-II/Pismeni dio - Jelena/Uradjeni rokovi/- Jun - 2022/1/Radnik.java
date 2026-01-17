import java.util.Random;
import java.time.LocalDateTime;

class Radnik extends Stanovnik implements Runnable{
	double plata;
	BufferPoruka buffer;
	volatile boolean radi = true;
	Teleekran teleekran;
	
	Radnik(String ime, String prezime, String mBroj, double plata, BufferPoruka buffer, Teleekran t){
		super(ime, prezime, mBroj);
		this.plata = plata;
		this.buffer = buffer;
		teleekran = t;
	}
	
	public synchronized void umanjiPlatu(double x){
		plata -= x;
		teleekran.prikaz("Radnik " + ime + " " + prezime + ": Plata umanjena za " + x + ". Nova plata: " + plata);
    }
	
	public void zaustavi(){
		radi = false;
	}
	
	@Override
	public void run(){
		Random rand  = new Random();
		for(int i=0;true;i++){
			while(Main.pauzirano && !Main.kraj){
				try{ 
					Thread.sleep(100); 
				} catch(InterruptedException e){}
			}
			if(Main.kraj){
				break;
			}
			String[] rijeci = {"sloboda", "istina", "mir", "znanje", "ljubav", "misao", "narod"};
			String randomRijec = rijeci[rand.nextInt(rijeci.length)];
			String tekstPoruke = "Poruka broj " + i + " od radnika " + ime + " " + randomRijec;

			Poruka p = new Poruka("NEPOZNATO", mBroj, LocalDateTime.now(), tekstPoruke);
			buffer.dodajPoruku(p);
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){
				System.out.println("Greska pri pauziranju radnika.");
			}
	}
}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " Maticni broj:" + mBroj + " Plata: " + plata;
	}
}