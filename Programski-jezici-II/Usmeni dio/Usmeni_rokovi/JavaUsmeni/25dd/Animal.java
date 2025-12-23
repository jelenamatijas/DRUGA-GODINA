public class Animal {
	private int nesto = 0;
public void eat(){ System.out.println("Animal is eating"); }
public void drink(){ System.out.println("Animal is drinking");}
private void privateMethod() {
System.out.println("Animal's private method");
}
public void commonMethod() { System.out.println("Common method"); }
public static void main(String[] args) {
Animal a = new Cat();
Cat cat = new Cat();
Animal ani = new Animal();
System.out.println(ani.nesto);
a.eat();
a.drink();
a.privateMethod();
a.commonMethod();
((Cat)a).meow();
Cat c = (Cat)a;
c.eat();
c.drink();
((Animal)c).privateMethod();
c.commonMethod();
c.meow();
Animal animal = new Animal();
//Cat catty = (Cat) animal;
}
}
class Cat extends Animal {
public void eat() { System.out.println("Cat is eating"); }
public void drink() { System.out.println("Cat is drinking"); }
public void meow() { System.out.println("Cat is meowing"); }
private void moo() { System.out.println("Cat is mooo"); }
}