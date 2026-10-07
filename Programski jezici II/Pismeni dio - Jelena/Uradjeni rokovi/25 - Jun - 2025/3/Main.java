import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main{
	static void napraviFajl(){
		try(PrintWriter pw = new PrintWriter(new FileWriter("podaci.csv"))){
			pw.println("1103/23 " +  "Matematika-1 " +  9);
			pw.println("1102/24 " +  "OET-1 " +  10);
			pw.println("1111/24 " +  "ORT " +  8);
			pw.println("1124/22 " +  "Matematika-2 " +  6);
			pw.println("1103/23 " +  "Programiranje-1 " +  7);
			pw.println("1111/24 " +  "UTS " +  6);
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl.");
		}
	}
	
	public static void main(String []args){
		//napraviFajl();
		
		Map<String, Student> studenti = new HashMap<>();
		
		try(BufferedReader bf = new BufferedReader(new FileReader("podaci.csv"))){
			String linija = "";
			while((linija = bf.readLine())!=null){
				String[] podaci = linija.split(" ");
				String index = podaci[0].trim();
				String predmet = podaci[1].trim();
				double ocjena = Double.parseDouble(podaci[2]);
				
				if(studenti.containsKey(index)){
					studenti.get(index).prosjek += ocjena;
					studenti.get(index).brojPolozenih++;
					studenti.get(index).detalji += " " + predmet;
				}else{
					studenti.put(index, new Student(index, 1, ocjena, predmet));
				}
			}
		}catch(IOException e){
			System.out.println("GRESKA prilikom citanja podataka.");
		}
		List<Student> lista = new ArrayList<>();
		
		studenti.forEach((index, s) -> {
			s.prosjek = s.prosjek / s.brojPolozenih;
			lista.add(s);
		});
		
		lista.stream().sorted(Comparator.comparing(s -> s.index));
		try(PrintWriter pw = new PrintWriter(new FileWriter("studenti.txt"))){
			lista.forEach(s -> pw.println(s));
		}catch(IOException e){
			System.out.println("GRESKA pri ispisu studenata u fajl.");
		}
	}
}