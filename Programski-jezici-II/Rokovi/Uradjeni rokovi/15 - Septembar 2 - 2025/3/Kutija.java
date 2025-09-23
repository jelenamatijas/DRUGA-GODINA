import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;

class Kutija<T extends Proizvod>{
	List<T> proizvodi;
	
	public Kutija(){
		proizvodi = new ArrayList<>();
	}
	
	public void dodaj(T t){
		proizvodi.add(t);
	}
	
	public T vrati(String id){
		for(T t: proizvodi){
			if(t.id.equals(id)){
				return t;
			}
		}
		return null;
	}
	
	public List<T> filtriraj(Predicate<T> uslov){
		return proizvodi.stream().filter(uslov).collect(Collectors.toList());
	}
}