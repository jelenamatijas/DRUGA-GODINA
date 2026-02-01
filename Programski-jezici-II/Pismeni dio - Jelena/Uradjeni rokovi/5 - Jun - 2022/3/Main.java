import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Main{
	public static void processingData(List<Predicate<Data>> uslovi, Consumer<Data> consumer, List<? extends Data>... liste){
		List<Data> list = new ArrayList<>();
		for(List<? extends Data> t : liste){
			list.addAll(t);
		}
		
		list.stream().filter(e -> {
			for(Predicate<Data> p : uslovi){
				if(!p.test(e)){
					return false;
				}
			}
			return true;
		}).sorted((a,b) -> Integer.compare(a.hashCode(), b.hashCode())).collect(Collectors.toList()).forEach(consumer);
		
		
	}
	
	static public void main(String args[]){
		List<ExampleData1> l1 = Arrays.asList(
								new ExampleData1("Tip1", 100, "RED"),
								new ExampleData1("Tip2", 200, "BLUE"),
								new ExampleData1("Tip3", 300, "GREEN")
								);
		List<ExampleData2> l2 = Arrays.asList(
								new ExampleData2("Tip1", 100, 1),
								new ExampleData2("Tip2", 200, 2),
								new ExampleData2("Tip3", 300, 3)
								);
		
		Predicate<Data> uslov1 = data -> data.getType().equals("Tip1");
		Predicate<Data> uslov2 = data -> (Integer)data.getValue()>=100;
		Predicate<Data> uslov3 = data -> {
			if(data instanceof ExampleData1){
				return true;
			}else{
				return false;
			}
			
		};
		
		List<Predicate<Data>> uslovi = Arrays.asList(uslov1, uslov2, uslov3);
		Consumer<Data> consumer = data -> System.out.println(data);
		processingData(uslovi, consumer, l1, l2);
	}
}