public class Cycles {
	public static void rideCycle(CycleFactory factory) {
		Cycle c = factory.getCycle();
		c.ride();
	}
	public static void main(String [] args) {
		rideCycle(new UnicycleFactory());
		rideCycle(new BicycleFactory());
		rideCycle(new TricycleFactory());
	}
}
interface Cycle { void ride(); }
interface CycleFactory { Cycle getCycle(); }
class Unicycle implements Cycle {
	public void ride() {
		System.out.println("Ride Unicycle");
	}
}
class UnicycleFactory implements CycleFactory {
	public Cycle getCycle() {
		return new Unicycle();
	}
}
class Bicycle implements Cycle {
	public void ride() { System.out.println("Ride Bicycle"); }
}
class BicycleFactory implements CycleFactory {
	public Cycle getCycle() { return new Bicycle(); }
}
class Tricycle implements Cycle {
	Tricycle() { System.out.println("Tricycle()"); }
	public void ride() { System.out.println("Ride Tricycle"); }
}
class TricycleFactory implements CycleFactory {
	public Cycle getCycle() {
		return new Tricycle();
	}
}