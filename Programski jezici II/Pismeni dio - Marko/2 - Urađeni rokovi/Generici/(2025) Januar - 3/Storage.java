import java.util.*;
import java.util.function.*;

public class Storage<T> {
	
	ArrayList<T> items = new ArrayList<>();
	
	public Storage() {
		super();
	}
	
	public void addItem(T item) {
		this.items.add(item);
	}
	
	public T findFirstMatch(Predicate<T> condition) {
		for (T item : this.items) {
			if (condition.test(item)) {
				return item;
			}
		}
		
		return null;
	}
	
	public void printAll() {
		this.items.forEach(System.out::println);
	}
}