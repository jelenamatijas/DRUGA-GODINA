import java.util.*;

class BufferPoruka{
	List<Poruka> poruke;
	
	BufferPoruka(){
		poruke = new ArrayList<>();
	}
	
	public synchronized void dodajPoruku(Poruka p){
		poruke.add(p);
		notifyAll();
	}
	
	public synchronized List<Poruka> getAll(){
		return new ArrayList<>(poruke);
	}
}