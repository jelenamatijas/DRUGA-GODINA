import java.io.*;
import java.util.*;

class Main{
	static List<Kutija> neutovareneKutije = new ArrayList<>();
	static Map<Kamion.TipKamiona, List<Kamion>> kamioni = new HashMap<>();
	
	private static void obradiKutiju(Kutija kutija){
		double zapremina = kutija.visina * kutija.sirina * kutija.duzina;
		List<Kamion> lista = null;
		if(kutija.tip == Kutija.Tip.STANDARD){
			lista = kamioni.get(Kamion.TipKamiona.STANDARD);
		}else if(kutija.tip == Kutija.Tip.REFRIGERATED){
			lista = kamioni.get(Kamion.TipKamiona.REFRIGERATED);
		}
		boolean ubacena = false;
		if(lista != null){
			//System.out.println(lista);
			for(int i =0; i<lista.size();i++){
				Kamion k = lista.get(i);
				if((k.popunjenostZapremine + zapremina <= k.zapremina) && (k.popunjenostTezine + kutija.tezina <= k.maxTezina)){
					lista.get(i).kutije.add(kutija);
					lista.get(i).popunjenostTezine += kutija.tezina;
					lista.get(i).popunjenostZapremine += zapremina;
					ubacena = true;
					break;
				}
			}
		}
		
		if(!ubacena){
			neutovareneKutije.add(kutija);
		}
	}
	
	public static void main(String []args){
		
		try(BufferedReader bf = new BufferedReader(new FileReader("trucks.txt"))){
			String linija = "";
			bf.readLine();
			while((linija = bf.readLine())!=null){
				String[] podaci = linija.split("#");
				String id = podaci[0];
				int tezina = Integer.parseInt(podaci[1]);
				double zapremina = Double.parseDouble(podaci[2]);
				Kamion.TipKamiona tip;
				if("STANDARD".equals(podaci[3].trim())){
					tip = Kamion.TipKamiona.STANDARD;
				}else{
					tip = Kamion.TipKamiona.REFRIGERATED;
				}
				if(kamioni.get(tip) == null){
					List<Kamion> lista = new ArrayList<>();
					lista.add(new Kamion(id, tezina, zapremina, tip));
					kamioni.put(tip, lista);
				}else{
					kamioni.get(tip).add(new Kamion(id, tezina, zapremina, tip));
				}
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju podataka o kamionima.");
		}
		
		/*kamioni.forEach((tip, lista) -> {
			System.out.println(tip);
			lista.forEach(System.out::println);
		});*/
		
		try(BufferedReader bf = new BufferedReader(new FileReader("boxes.txt"))){
			String linija = "";
			bf.readLine();
			while((linija = bf.readLine())!=null){
				String[] podaci = linija.split("#");
				String id = podaci[0];
				double visina = Double.parseDouble(podaci[1]);
				double sirina = Double.parseDouble(podaci[2]);
				double duzina = Double.parseDouble(podaci[3]);
				int tezina = Integer.parseInt(podaci[4]);
				Kutija.Tip tip;
				if("STANDARD".equals(podaci[5].trim())){
					tip = Kutija.Tip.STANDARD;
				}else if("FRAGILE".equals(podaci[5].trim())){
					tip = Kutija.Tip.FRAGILE;
				}else{
					tip = Kutija.Tip.REFRIGERATED;
				}
				Kutija kutija = new Kutija(id, visina, sirina, duzina, tezina, tip);
				
				obradiKutiju(kutija);
			
			}	
		}catch(IOException e){
			System.out.println("GRESKA pri citanju podataka o kutijama.");
		}
		
		kamioni.forEach((tip, lista) -> {
			lista.forEach(kamion -> {
				double poTezini = (double)kamion.popunjenostTezine/kamion.maxTezina*100;
				double poZapremini = (double)kamion.popunjenostZapremine/kamion.zapremina*100;
				System.out.println(kamion + "\nProcenat popunjenosti po tezini: " + poTezini + "%\nProcenat popunjenost po zapremini: " + poZapremini + "%\n");
			});
		});
		
		System.out.println("\nNEUTOVARENE KUTIJE: ");
		neutovareneKutije.forEach(System.out::println);
	}
}