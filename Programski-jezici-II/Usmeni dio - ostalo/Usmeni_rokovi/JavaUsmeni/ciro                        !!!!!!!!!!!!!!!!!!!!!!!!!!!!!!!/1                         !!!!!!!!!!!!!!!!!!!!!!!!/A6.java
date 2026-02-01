import java.io.Serializable;
class A1 implements I2 {
	static String a1s;
	A1 a1;
	{
		a1s = "a1";
		System.out.println("A1N1");
	}
	public static void main(String[] args) {
		System.out.println("Pocetak!");
		A1 a1 = new A1();
		A2 a2 = new A2(new A1(new A1(new A1("A2"))));
		A3 a3 = new A3(a2);
		Serializable a4 = new A4();
		I2 a5 = new A5();
		I1 a6 = new A6((A1) a5);
		System.out.println(a1 == a2);
		System.out.println(a2 == a3);
		System.out.println(a3.equals(a1));
		System.out.println(((A1) a4).equals(a2));
		System.out.println(((Serializable) a5).equals(a6));
		System.out.println(((A2) a3).a1s = ((A5) a6).a1s);
		System.out.println(((A1) a4).a1s = ((A3) a2).a1s);
		System.out.println(((A2) a3).a1s = ((A2) a6).a1s);
		System.out.println(((A4) a6).a1s = ((A5) a6).a1s);
		System.out.println(a1 = a2);
		System.out.println(a6 = (I1) a1);
		System.out.println(a6.equals(a5));
		System.out.println(((A3) a4).equals(a2));
		System.out.println(((Serializable) a6).equals(a1));
		System.out.println("kraj!");
	}
	A1(String x) {
		x.replace("A", "B");
		System.out.println("A(\"" + x + "\")");
	}
	A1(A1 a1) {
		this.a1 = a1;
		System.out.println("A1(A1)");
	}
	A1() {
		this(new A1("A1"));
		System.out.println("A1");
	}
	public void A1() {
		System.out.println("A1");
	}
	static
	{
		System.out.println("A1S");
	}
	{
		System.out.println("A1N2");
	}
}
class A2 extends A1 {
	{
		this.a1 = null;
		System.out.println("A2N");
	}
	protected A2() {
		// A3 a3 = new A3(new A2());
		I2 a1 = new A1(new A1("A2A1"));
		System.out.println("A2");
	}
	static {
		System.out.println("A2S2");
	}
	A2(A1 a1) {
		super(new A1());
	}
	static {
		System.out.println("A2S1");
	}
}
class A3 extends A2 implements Serializable {
	A2 a2;
	public void ma1n(String[] args) {
		System.out.println("main");
	}
	private A3()
	{
		if (this.a1 instanceof A1)
		{
			this.a1 = new A2();
			this.a2 = new A3();
		}
		System.out.println("A3");
	}
	private void A3(){
		System.out.println("A3");
	}
	A3(A2 a2)
	{
		this();
		if (a2 instanceof I1)
		{
			this.a2 = new A6(new A6(a2));
		}
		this.a1 = a2;
	}
	{
		System.out.println("A3N");
	}
	static {
		System.out.println("A3S");
	}
}
class A4 extends A3 {
	static {
		System.out.println("A4S");
	}
	A4()
	{
		super(new A2());
		System.out.println("A4");
	}
	{
		if (this.a1 instanceof I1)
			this.a1 = new A6();
		System.out.println("A4N");
	}
}
class A5 extends A4 {
	public static int c;
	{
		System.out.println("A5N");
	}
	public A5()
	{
		super();
		this.a2 = new A2();
		this.a1 = a2;
		System.out.println("A5");
	}
}
public class A6 extends A5 implements I1 {
	{
		System.out.println("A6N");
	}
	static {
		System.out.println("A6S");
	}
	public A6(int c)
	{
		this.c += c;
		System.out.println("A6(" + c + ")");
	}
	public A6()
	{
		this(0);
		this.a1 = this;
		System.out.println("A6");
	}
	public A6(A1 a1)
	{
		this(1);
		this.a1 = a1;
		System.out.println("A6(A1)");
	}
}
abstract interface I1 {
	void ma1n(String[] s);
}
interface I2{}