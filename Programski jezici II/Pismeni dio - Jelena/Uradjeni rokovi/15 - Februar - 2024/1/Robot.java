import java.io.*;

class Robot extends Thread{
	Senzor senzor;
	Motor motor;
	Baterija baterija;
	String serijskiBroj;
	String tip;
	int snaga;
	int autonomija;
	int visina;
	static int robotID = 1;
	int kraj;

	
	public Robot(String tip, Senzor vrstaSenzora){
		senzor = vrstaSenzora;
		motor = new Motor();
		baterija = new Baterija();
		serijskiBroj = "Serijski broj: " + robotID;
		this.tip = tip;
		snaga = Main.rand.nextInt(20)+1;
		autonomija = 100;
		visina = Main.rand.nextInt(2)+1;
		kraj = Main.rand.nextInt(10, 31);
	}
	
	private void upisiFajl(){
		try(PrintWriter pw = new PrintWriter(new FileWriter("ostecenja.txt", true))){
			pw.println(this + " je OSTECEN.");
		}catch(IOException e){
			System.out.println("Desila se greska pri upisu u fajl.");
		}
	}
	
	@Override
	public void run(){
		int pocetak = 0;
		while(pocetak < kraj){
			if(tip.equals("MONTAZNI")){
				System.out.println(this + " je u fazi MONTIRANJA.");
			}else if(tip.equals("CISTACKI")){
				System.out.println(this + " je u fazi CISCENJA.");
			}else if(tip.equals("ISTRAZIVACKI")){
				System.out.println(this + " je u fazi ISTRAZIVANJA.");
			}
			
			try{
				sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Desila se greska pri radu robota: " + this);
			}
			pocetak++;
			
			synchronized (Main.rand){
				if(Main.rand.nextInt(1, 101) < 6){
					upisiFajl();
				}
			}
			
			synchronized (Main.lock){
				if(Main.cekaj == true){
					try{
						Main.lock.wait();
					}catch(InterruptedException e){
						System.out.println("Greska pri pokretanju alarma!");
					}
				}
			}
		}
		
		if(pocetak == kraj){
			System.out.println(this + " je zavrsio sa radom.");
		}
	}
	
	@Override
	public String toString(){
		return "Robot{ID: " + serijskiBroj + " Tip: " + tip + " Snaga: " + snaga + " Autonomija: " + autonomija + " Visina: " + visina + senzor + motor + baterija + "}";
	}
}