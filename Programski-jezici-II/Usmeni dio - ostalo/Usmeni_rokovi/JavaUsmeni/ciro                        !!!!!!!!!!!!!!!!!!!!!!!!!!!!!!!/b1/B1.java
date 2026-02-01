import java.util.*;
abstract class B1 extends B2 implements BI, BI2 {
	B1() { System.out.println("B1"); };
	public void metoda() {
		System.out.println("B1metoda");
	}
	public static void main(String[] args) {
		BI4 b1 = (bi2) -> {
			System.out.println("lambda1"); bi2.metoda();
		};
	BI b2 = new B2() {
		public void metoda2(BI2 bi2) {
			System.out.println("B2metoda2");
		}
	};
	BI b3 = new B3();
	BI3 b4 = (BI3) (new B4());
	BI1 bi1 = new BI11();
	B5.B51 b5b51 = new B5.B51(){};
	BI5 bi5 = (bi2) -> { System.out.println("lambda2"); };
	BI21 bi21 = new BI21();
	bi21.metoda2(b4);
	BI2[] arr = {b1,(BI2) b2,(BI2) b3,b4, new B3()};
	// BI3[] arr2 = {(BI3) b1,(BI3)b2,(BI2)b3,(BI3)b4};
	bi5.metoda();
	bi1.metoda();
	for(BI2 x : arr) {
		x.metoda();
	}
	BI4[] arr2 = {b1, (BI4) b4, bi5};
	Arrays.stream(arr2).limit(5).forEach(x>
		x.metoda2(new B4()));
	}
}
abstract class B2 implements BI2, BI5 {
	abstract interface B2I {
	}
	B2(){System.out.println("B2");};
	public int metoda2(){return 1;}
}
class B3 implements BI3 {
	B3(){System.out.println("B3");};
}
class B4 extends B1 implements BI, BI2, BI3 {
	B4(){System.out.println("B4");};
	// static void metoda2(){System.out.println("B4metoda2S");}
	// public void metoda2(BI3 bi2) {
	public void metoda2(BI2 bi2) {
		System.out.println("B4metoda2");
	}
}
abstract class B5 implements BI3 {
	// abstract static class B51 extends B5 implements BI1 {
	abstract static class B51 extends B5 implements BI3 {
		B51()
		{
			System.out.println("B51");
		}
	}
	{
		System.out.println("B5");
	}
	public void metoda() {
		System.out.println("B5metoda");
	}
}
public interface BI extends BI1 {
	static class BI1 implements B2.B2I {
		void metoda() {
			System.out.println("BI1metoda");
		}
	}
}
interface BI1 {
	default void metoda() {
		System.out.print("Test");
	}
	class BI11 extends BI.BI1{
	}
}
interface BI2 extends BI{
	class BI21 {
		private static int c;
		public void metoda2(BI2 bi2) {
			System.out.println("BI21metoda2");
			c--;
			bi2.metoda();
			this.metoda();
		}
		private void metoda() {
			if (c != 0) {
				System.out.println("BI21metoda");
				new BI211().metoda();
			}
			c--;
			c--;
		}
		class BI211 implements BI {
			public void metoda()// void metoda() 
			{
				System.out.println("BI211metoda");
				c++;
				BI21.this.metoda();
			}
			BI211() {
				System.out.println("BI211");
			}
			BI11 bi11 = new BI11();
		}
	}
}
interface BI3 extends BI, BI2 {
	default void metoda() {
		System.out.println("BI3metoda");
	}
}
interface BI4 extends BI3 {
	default void metoda() {
		System.out.println("BI4metoda");
	}
	void metoda2(BI2 bi2);
}
interface BI5 extends BI3, BI4 {
// public void metoda();
}