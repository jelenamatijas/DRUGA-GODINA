class A1 extends A2 implements B1 {
	private A1 a1;
	class A5 {
		{
			System.out.println("A5N");
		}
		A5() {
			System.out.println("A5");
		}
		A5(A5 a5) {
			a1 = new A1();
		}
	}
	{
		System.out.println("A1N");
	}
	A1() {
		System.out.println("A1");
	}
	A1(A1 a1) {
		this.a1 = a1;
	}
	public static void main(String[] args) {
		System.out.println("start");
		A4 a1 = new A1();
		A4 a2 = new A2(new A1());
		A4 a3 = new A3(new A2(new A1()));
		A4 a4 = new A4(new A3(new A2(new A1())));
		A1.A5 a5 = ((A1) a2).new A5();
		System.out.println(a2.a1 = a1.a1);
		System.out.println(a2.a1.equals(a2));
		System.out.println(a1.a1.equals(a2.a1));
	}
	static {
		System.out.println("A1S");
	}
}
class A2 extends A4 implements B1 {
	static {
		System.out.println("A2S");
	}
	{
		System.out.println("A2N");
	}
	A2() {
		this(new A2("String constructor"));
		System.out.println("A2");
	}
	A2(String s) {
		System.out.println("A2(S)");
	}
	A2(A2 a2) {
		super();
		System.out.println("A2(A2)");
	}
}
class A3 extends A4 implements B1 {
	A3 a1 = new A3();
	A4 a4 = new A4();
	static {
		System.out.println("A3S");
	}
	{
		System.out.println("A3N");
	}
	A3() {
		System.out.println("A3");
	}
	A3(A4 a4) {
		this();
		System.out.println("A3(A4)");
		this.a1 = null;
	}
}
public class A4 implements B1 {
	A1 a1;
	A4 a2;
	static {
		System.out.println("A4S");
	}
	{
		if (!(this instanceof A3)) {
			if (0 == 1)
				a2 = new A4();
			System.out.println("A4N");
		}
	}
	A4() {
		if (!(this instanceof A3))
			System.out.println("A4");
	}
	A4(A4 a4) {
		System.out.println("A4(A4)");
	}
	public boolean equals(A4 o) throws NullPointerException {
		if (super.equals(o))
			System.out.println("false");
		else
			System.out.println(true);
		return (super.equals((A4) o));
	}
}
interface B1 {
	boolean equals(Object o);
}