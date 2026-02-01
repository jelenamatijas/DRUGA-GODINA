import java.util.*;
import java.io.*;

class Main{
	static Map<Character, Character> mapaSlova = new HashMap<>();
	private static void dodajSlova(){
		mapaSlova.put('A', 'А');
		mapaSlova.put('B', 'Б');
		mapaSlova.put('V', 'В');
		mapaSlova.put('G', 'Г');
		mapaSlova.put('D', 'Д');
		mapaSlova.put('Đ', 'Ђ');
		mapaSlova.put('E', 'Е');
		mapaSlova.put('Ž', 'Ж');
		mapaSlova.put('Z', 'З');
		mapaSlova.put('I', 'И');
		mapaSlova.put('J', 'Ј');
		mapaSlova.put('K', 'К');
		mapaSlova.put('L', 'Љ');
		mapaSlova.put('M', 'М');
		mapaSlova.put('N', 'Н');
		mapaSlova.put('O', 'О');
		mapaSlova.put('P', 'П');
		mapaSlova.put('R', 'Р');
		mapaSlova.put('S', 'С');
		mapaSlova.put('T', 'Т');
		mapaSlova.put('Ć', 'Ћ');
		mapaSlova.put('U', 'У');
		mapaSlova.put('F', 'Ф');
		mapaSlova.put('H', 'Х');
		mapaSlova.put('C', 'Ц');
		mapaSlova.put('Č', 'Ч');
		mapaSlova.put('Š', 'Ш');
		mapaSlova.put('a', 'а');
		mapaSlova.put('b', 'б');
		mapaSlova.put('v', 'в');
		mapaSlova.put('g', 'г');
		mapaSlova.put('d', 'д');
		mapaSlova.put('đ', 'ђ');
		mapaSlova.put('e', 'е');
		mapaSlova.put('ž', 'ж');
		mapaSlova.put('z', 'з');
		mapaSlova.put('i', 'и');
		mapaSlova.put('j', 'ј');
		mapaSlova.put('k', 'к');
		mapaSlova.put('l', 'л');
		mapaSlova.put('m', 'м');
		mapaSlova.put('n', 'н');
		mapaSlova.put('o', 'о');
		mapaSlova.put('p', 'п');
		mapaSlova.put('r', 'р');
		mapaSlova.put('s', 'с');
		mapaSlova.put('t', 'т');
		mapaSlova.put('ć', 'ћ');
		mapaSlova.put('u', 'у');
		mapaSlova.put('f', 'ф');
		mapaSlova.put('h', 'х');
		mapaSlova.put('c', 'ц');
		mapaSlova.put('č', 'ч');
		mapaSlova.put('š', 'ш');
	}
	
	public static void main(String []args){
		if(args.length < 1){
			System.out.println("GRESKA: format java Main <pozicija_1 pozicija_2 ... pozicija_n>");
			return;
		}
		dodajSlova();
		
		ArrayList<Integer> pozicije = new ArrayList<>();
		for (String s : args) {
			pozicije.add(Integer.parseInt(s));
		}
		
		try(RandomAccessFile r = new RandomAccessFile("tekst.txt", "r")){
			for(int i=0; i<pozicije.size();i++){
				try{
					if(pozicije.get(i) >= r.length()){
						throw new MojIzuzetak("Pozicija " + pozicije.get(i) + " je veca od duzine fajla.");
					}
					r.seek(pozicije.get(i));
					char c = (char)r.read();
					if(!Character.isLetterOrDigit(c)){
						throw new MojIzuzetak("Na poziciji " + pozicije.get(i) + " se ne nalazi rijec.");
					}else{
						StringBuilder latRijec = new StringBuilder();
						while(Character.isLetterOrDigit(c)){
							latRijec.append(c);
							c = (char)r.read();
						}
						
						StringBuilder cirRijec = new StringBuilder();
						for(int j=0;j<latRijec.length();j++){
							char temp = latRijec.charAt(j);
							cirRijec.append(mapaSlova.get(temp));
						}
						
						System.out.println("Cirilicna rijec: " + cirRijec);
						
					}
				}catch(MojIzuzetak e){
					System.out.println(e.getMessage());
				}
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju fajla.");
		}
		
		
	}
}