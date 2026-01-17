public class Main {
	
	public static void main(String[] args) {
		if (args.length != 4 || !"-d".equals(args[0]) || !"-l".equals(args[2])) {
			System.out.println("GRESKA! Pogresan unos, format mora biti: -d putanja_do_foldera -l duzina");
			return;
		}
		
		String putanja = args[1];
		int duzina;
		try {
			duzina = Integer.parseInt(args[3]);
		} catch (NumberFormatException ex) {
			System.out.println("GRESKA! Duzina rijeci mora biti int!");
			return;
		}
		
		FileSearcher fileSearcher = new FileSearcher(putanja, duzina);
		
		fileSearcher.pretraziFajlove();
		fileSearcher.zapisiRezultateUFajl();
		fileSearcher.ispisiRezultate();
	}
}