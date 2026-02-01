import java.util.ArrayList;
import java.util.function.Predicate;

public class Storage<T> {
	
	private ArrayList<T> items = new ArrayList<>();
	
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
		for (T item : this.items) {
			System.out.println(item.toString());
		}
	}
}