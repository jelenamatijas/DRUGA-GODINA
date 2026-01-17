import java.util.*;
import java.util.Date;

class Main{
	
	static List<Poruka> poruke = new ArrayList<>();
	static public Random rand = new Random();
	static public Object lockKomunikacija = new Object();
	static volatile boolean programRadi = true; 
	
	static public String time(){
		Date date = new Date();
		return date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
	}
	
	static public void main(String args[]){		
		Letjelica l = new Letjelica();
		l.start();
		
		Thread input = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while(programRadi && l.leti){
				try{
					if(scanner.hasNextLine()){
						String rijec = scanner.nextLine();
						if(rijec.toLowerCase().equals("stop")){
							l.pogon.ugasen = true;
							l.nav.modulRadi = false;
							l.kom.modulRadi = false;
							l.leti = false;
							l.nav.interrupt();
							l.kom.interrupt();
							programRadi = false;
							System.out.println("Simulacija je prekinuta.");
							break;
						}
					}
				}catch(Exception e){
					break;
				}
				
			}
		});
		
		input.setDaemon(true);
		input.start();
		
		try{
			l.join();
		}catch(InterruptedException e){
			System.out.println("Greska: " + e);
		}
		programRadi = false;
		
		System.out.println("Simulacija je zavrsena.");
		
		
	}
}