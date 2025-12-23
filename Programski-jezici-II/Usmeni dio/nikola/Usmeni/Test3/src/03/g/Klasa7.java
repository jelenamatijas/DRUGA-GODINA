import java.util.*;
import static java.util.stream.Collectors.*;

public class Klasa7 {
    public static List<Person> createPeople() {
        return Arrays.asList(
            new Person("Bob", 37),
            new Person("Alice", 25),
            new Person("Jake", 25),
            new Person("Ryan", 37),
            new Person("Jill", 24)
        );
    }
    
    public static void main(String[] args) {
        List<Person> people = createPeople();
        
        // format: {key1=[values...], key2=[values...], ...}
        System.out.println(
            people.stream()
                  .collect(groupingBy(Person::getAge)));
    }
}

class Person {
    final String name;
    final int age;
    
    public Person(String theName, int theAge) {
        name = theName;
        age = theAge;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String toString() {
        return String.format("%s -- %d", name, age);
    }
}