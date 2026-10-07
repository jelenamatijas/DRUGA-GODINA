import java.util.Arrays;
import java.io.Serializable;
class A1 extends A2 {
	private static final int Const = 10;
	public static void main(String[] argumenti) {
		A4[] a = new A4[Const];
		for(int i=0;i<a.length/3;i++) {
			if(Const%2<3)
				if(a.length>5)
					a[a.length-i-1]
						= new A4();
			a[i] = new A3();
			a[i+1] = new A2(a[i]);
			a[i+2] = new A1();
		}
		Arrays.stream(a).forEach(x -> {
			x = new A4();
			x.a4 = new A4();
			if (x instanceof A3) {
				Serializable b = (Serializable) x;
				if (x.equals(b) == true)
					b = new A3();
			}
		});
	}
	A1() {
		System.out.println("A1");
	}
}
class A2 extends A3 {
	A2(){}
	void A2() {
		System.out.println("A2");
	}
	public static void main(String[] args) {
	}
	A2(A4 a4) {
		System.out.println("A2(A4)");
	}
}
class A3 extends A4 implements Serializable {
	{
		System.out.println("A3N");
	}
}
public class A4 {
	A4 a4;
	void A4() {
		System.out.println("A4");
	}
	static {
		System.out.println("AS");
	}
}