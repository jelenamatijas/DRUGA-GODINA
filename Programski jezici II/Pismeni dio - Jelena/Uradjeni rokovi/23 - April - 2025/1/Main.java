import java.io.*;
import java.util.*;


class Main{
	public static Map<String, Skladiste> skladista= new HashMap<>();
	
	public static void main(String args[]){
		try(BufferedReader bf = new BufferedReader(new FileReader("skladista.txt"))){
			String linija = "";
			Skladiste s = null;
			while((linija = bf.readLine())!=null){
				String[] podaci = linija.split(";");
				
				if("SKLADISTE".equals(podaci[0].trim())){
					s = skladista.computeIfAbsent(podaci[1].trim(), Skladiste::new);
				}else{
					
					if("HRANA".equals(podaci[0].trim())){
						try{
							if(Integer.parseInt(podaci[4])>=5 && Integer.parseInt(podaci[4])<=10){
								s.artikli.put(Integer.parseInt(podaci[1]),new Hrana(podaci[2], Integer.parseInt(podaci[1]), Integer.parseInt(podaci[3]), Integer.parseInt(podaci[4]))); 
							}else{
								throw new RokTrajanjaException("GRESKA: Rok trajanja nije u granicama [5,10].");
							}
						}catch(RokTrajanjaException e){
								System.out.println(e.getMessage());
						}
						
					}else{
						try{
							if(Integer.parseInt(podaci[4])>=1 && Integer.parseInt(podaci[4])<=5){
								s.artikli.put(Integer.parseInt(podaci[1]),new Elektronika(podaci[2], Integer.parseInt(podaci[1]), Integer.parseInt(podaci[3]), Integer.parseInt(podaci[4])));
							}else{
								throw new GarancijaException("GRESKA: Garancija nije u granicama [1,5].");
							}
						}catch(GarancijaException e){
								System.out.println(e.getMessage());
						}
					}
					
				}
				skladista.put(s.nazivSkladista, s);
			}
			
		}catch(IOException e){
			System.out.println("GRESKA pri unosu artikala u skladista.");
		}
		
		ArrayList<IzvrsiAkciju> akcije = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader("akcije.txt"))){
			String linija = "";
			while((linija = bf.readLine())!=null){
				IzvrsiAkciju a = new IzvrsiAkciju(linija);
				akcije.add(a);
			}
		}catch(IOException e){
			System.out.println("GRESKA pri unosu akcija.");
		}
		
		try{
			for(IzvrsiAkciju a:akcije){
				a.start();
			}
		}catch(Exception e){
			System.out.println("GRESKA pri pokretanju akcija.");
		}
		
		try{
			for(IzvrsiAkciju a:akcije){
				a.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju akcija.");
		}
		System.out.println("\n=================================================================");
		
		for(Skladiste s : skladista.values()){
			System.out.println(s);
		}
	}
}