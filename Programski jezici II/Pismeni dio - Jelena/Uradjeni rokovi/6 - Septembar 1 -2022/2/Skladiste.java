import java.util.*;
import java.util.function.Predicate;

class Skladiste{
	private PriorityQueue<AkcijaInterface> red;
	
	Skladiste(){
		red = new PriorityQueue<>();
	}
	
	public void add(AkcijaInterface e){
		red.add(e);
	}
	
	public AkcijaInterface getFirst(){
		return red.poll();
	}
	
	public void print(){
		for(AkcijaInterface e : red){
			System.out.println(e);
		}
	}
	
	public void runActions(){
		for(AkcijaInterface e: red){
			e.runAction();
		}
	}
	
	public void search(List<Predicate<AkcijaInterface>> p){
		List<AkcijaInterface> result = new ArrayList<>();
		for(AkcijaInterface e: red){
			if(e.check(p)){
				result.add(e);
			}
		}
		
		if(result.size()>0){
			for(AkcijaInterface e : result){
				System.out.println(e);
			}
		}else{
			System.out.println("Nema rezultata pretrage.");
		}
	}
	
}