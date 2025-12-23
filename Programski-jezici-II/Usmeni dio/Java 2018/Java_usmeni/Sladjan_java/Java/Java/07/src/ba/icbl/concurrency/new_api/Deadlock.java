package ba.icbl.concurrency.new_api;

public class Deadlock {

	public static void main(String[] args) {
		final Person marko = new Person("Marko");
		final Person pero = new Person("Pero");
		new Thread(new Runnable() {
			public void run() {
				marko.sayHello(pero);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				pero.sayHello(marko);
			}
		}).start();
	}

}

class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void sayHello(Person person) {
		System.out.println("Ja " + this.name + " pozdravljam " + person.getName());
		person.sayHelloToo(this);
	}

	public synchronized void sayHelloToo(Person person) {
		System.out.println("On " + this.name + " meni (" + person.getName() + ") otpozdravlja ");
	}
}