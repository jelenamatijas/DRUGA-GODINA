public class Test {

    public static void main(String[] args) {
        A a = new A();
		A a1 = new A();
    }
}
class B {
	static {
		System.out.println("B static");
	}
	{
		System.out.println("B non-static");
	}
	B() {
		System.out.println("B constructor");
	}
}

class A extends B {
	static {
		System.out.println("A static");
	}
	{
		System.out.println("A non-static");
	}
	A() {
		System.out.println("A constructor");
	}
}