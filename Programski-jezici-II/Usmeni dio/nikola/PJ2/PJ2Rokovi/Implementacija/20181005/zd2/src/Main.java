package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.*;
import java.util.stream.Collectors;
import java.lang.reflect.*;

public class Main {
	
	private static final int COLUMN_WIDTH = 12;
	static final String EMPTY_STRING = "            ";
	static final String BREAK_SEGMENT = "_______________";
	
	public static void main(String []args) {
		
		ArrayList<Field> allFields = new ArrayList<>();
		allFields.addAll(Arrays.asList(Car.class.getDeclaredFields()));
		try{
			allFields.add(Phone.class.getDeclaredField("name"));
			allFields.add(Phone.class.getDeclaredField("OS"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<Object> objects = new ArrayList<>(Arrays.asList(
			new Phone("Samsung Note 9", "Android", 999),
			new Phone("Samsung Galaxy S9", "Android", 799),
			new Phone("Apple IPhone X", "iOS", 999),
			new Phone("Nokia 8810", "KaiOS", 140),
			new Phone("Huawei P20", "Android", 799),
			new Car("Ford", "Pero", 13000, 12332131),
			new Car("Peugeot", "Filip", 24000, 1231234),
			new Car("BMW", "David", 42000, 12345456),
			new Car("Toyota", "Mirko", 26000, 1235445)
		));
		Predicate<Phone> isAndroid = t -> "Android".equals(t.OS);
		Predicate<Car> isExpensive = t -> t.value > 25000;
		Predicate<Phone> isCheap = t -> t.value < 800;
		BiFunction<Object, ArrayList<Field>, String> linePreparationFunction = (t, u) -> {
			String toReturn = "";
			ArrayList<Field> fields = new ArrayList<>(Arrays.asList(t.getClass().getDeclaredFields()));
			for(int counter = 0; counter < u.size(); counter++){
				if(fields.contains(u.get(counter))){
					try{
						String stringOfField = t.getClass().getDeclaredField(u.get(counter).getName()).get(t).toString();
						for(int innerCounter = 0; innerCounter < COLUMN_WIDTH; innerCounter++) {
							if(innerCounter < stringOfField.length())
								toReturn += String.valueOf(stringOfField.charAt(innerCounter));
							else 
								toReturn += " ";
						}
					} catch(NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
						e.printStackTrace();
					}
				} else {
					toReturn += EMPTY_STRING;
				}
				toReturn += EMPTY_STRING;
			}
			return toReturn;
		};
		ConsoleTable console = new ConsoleTable(allFields, linePreparationFunction, COLUMN_WIDTH, objects);
		//Treba dodati petlju za citanje, ako unese print, da nacrta tabelu, ako exit da izadje, posle prvog crtanja da pise skupa auta, zatim Android telefone
		//Ispis sugav, popravi
		try {
		console.print(null, null);
		System.out.println("Unesite enter da pregledate auta skuplja od 25000");
		while(!"enter".equals(System.console().readLine()));
		console.print(isExpensive, Car.class);
		System.out.println("Unesite enter da pregledate Android telefone");
		while(!"enter".equals(System.console().readLine()));
		console.print(isAndroid, Phone.class);
		System.out.println("Dok ne uneste exit ispisvace se telefoni jeftiniji od 800");
		while(!"exit".equals(System.console().readLine()))
			console.print(isCheap, Phone.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}