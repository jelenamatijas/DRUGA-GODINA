import java.util.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		RadnaMasina varenjeMasina = new RadnaMasina();
		varenjeMasina.senzori.add(new TemperaturaSenzor());
		varenjeMasina.senzori.add(new PritisakSenzor());
		
		RadnaMasina sjecenjeMasina = new RadnaMasina();
		sjecenjeMasina.senzori.add(new TemperaturaSenzor());
		sjecenjeMasina.senzori.add(new VibracijaSenzor());
		sjecenjeMasina.senzori.add(new VlagaSenzor());
		
		varenjeMasina.start();
		sjecenjeMasina.start();
		
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		while (!"kraj".equalsIgnoreCase(unos)) {
			unos = scanner.nextLine();
		}
		
		varenjeMasina.ugasi();
		sjecenjeMasina.ugasi();
	}
}