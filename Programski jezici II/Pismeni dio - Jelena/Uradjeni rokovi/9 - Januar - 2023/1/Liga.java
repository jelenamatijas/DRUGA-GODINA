import java.util.*;

class Liga extends Thread{
	Tim timovi[] = new Tim[8];
	List<Kolo> kola = new ArrayList<>();
	List<Partija> partije = new ArrayList<>(); // SVE PARTIJE UNUTAR LIGE
	String nazivLige;
	static int id = 1;
	int brojac = 0;
	
	Liga(){
		for(int i=0;i<8;i++){
			timovi[i] = new Tim();
		}
		setLiga();
	}
	
	Liga(Tim[] timovi){
		this.timovi = timovi;
		setLiga();
	}
	
	void setLiga(){
		nazivLige = "Liga_" + id;
		id++;
		for(int i=0;i<8;i++){
			for(int j=i+1;j<8;j++){
				partije.add(new Partija(timovi[i], timovi[j]));
			}
		}
		
		Iterator<Partija> it = partije.iterator();
		while(it.hasNext()){
			brojac = 0;
			Kolo k = new Kolo(nazivLige);
			while(brojac<4 && it.hasNext()){
				k.partije.add(it.next());
				brojac++;
			}
			kola.add(k);
		}
	}
	
	public void run(){
		System.out.println(nazivLige + " je zapocela.");
		for(Kolo p : kola){
			p.start();
		}
		try{
			for(Kolo k : kola){
				k.join();
			}
		}catch(Exception e){
			System.out.println(e);
		}
			
		Arrays.sort(timovi, (t1, t2) -> {
			return Integer.compare(t1.osvojeniBodovi, t2.osvojeniBodovi);
		});
		System.out.println(nazivLige + " je zavrsila.");
		
	}
	
	public void rezultat(){
		for(int i=0;i<8;i++){
			System.out.println(timovi[i]);
		}
		System.out.println("POBJEDNIK LIGE " + nazivLige + " JE: " + timovi[7]);
		System.out.println("--------------------------------------------------");
	}
}