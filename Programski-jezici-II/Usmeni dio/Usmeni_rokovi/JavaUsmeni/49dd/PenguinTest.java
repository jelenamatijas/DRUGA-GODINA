public class PenguinTest {
	public static void main(String []args) {
		Penguin pingu = new Penguin();
		pingu.walk();
		pingu.fly();
	}
}
class CannotFlyException extends Exception {}
interface Birdie {
	public abstract void fly() throws CannotFlyException;
}
interface Biped { void walk(); }
abstract class NonFlyer {
	private void fly() { System.out.print("cannot fly "); }
}
class Penguin extends NonFlyer implements Birdie, Biped {
	public void walk() { System.out.print("walk\n"); }
}