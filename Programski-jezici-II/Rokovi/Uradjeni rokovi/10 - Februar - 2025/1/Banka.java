import java.util.*;

class Banka{
	Map<String, List<Racun>> racuni;
	List<Transakcija> transakcije = new ArrayList<>();
	
	public Banka(){
		racuni = new HashMap<>();
	}
	
	public void dodajRacun(String vlasnik, Racun r){
		if(racuni.get(vlasnik) != null){
			racuni.get(vlasnik).add(r);
		}else{
			List<Racun> lista = new ArrayList<>();
			lista.add(r);
			racuni.put(vlasnik, lista);
		}
	}
	
	public void pregledajRacune(){
		racuni.forEach((ime, lista) -> {
			System.out.println(ime);
			lista.forEach(System.out::println);
		});
	}
	
	public void izvrsiTransakcije(){
		transakcije.forEach(tr -> tr.start());
		
		try{
			for(Transakcija t: transakcije){
				t.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju transakcija.");
		}
	}
	
	public synchronized void uplati(String ime, int i, int iznos)throws MyException{
		if(iznos<0){
			throw new MyException("Iznos je negativan: " + iznos);
		}
		racuni.get(ime).get(i).stanje += iznos;
	}
	
	public synchronized void isplati(String ime, int i, int iznos) throws MyException{
		if(iznos<0){
			throw new MyException("Iznos je negativan: " + iznos);
		}
		if(racuni.get(ime).get(i).stanje < iznos){
			throw new MyException("Vrijednost na racunu klijenta " + ime + " je manja od iznosa " + iznos);
		}
		racuni.get(ime).get(i).stanje -= iznos;
	}
	
	public synchronized void prenesi(String ime1, String ime2, int iznos)throws MyException{
		int index = Main.rand.nextInt(0, Main.klijenti.get(ime1).racuni.size());
		isplati(ime1, index, iznos);
		index = Main.rand.nextInt(0, Main.klijenti.get(ime2).racuni.size());
		uplati(ime2, index, iznos);
		System.out.println("Izvrsen prenos iznosa " + iznos + " sa racuna klijenta " + ime1 + " na racun klijenta " + ime2);
	}
}