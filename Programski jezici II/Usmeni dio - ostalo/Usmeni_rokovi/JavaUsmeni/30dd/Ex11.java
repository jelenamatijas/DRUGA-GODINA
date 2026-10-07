public class Ex11 {
	public static void main(String[] args) {
		Test t = new Test();
		t.f().say("hi");
		((Inner)t.f()).say("hello");
	}
}
interface Ex11Interface {
	void say(String s);
}
class Test {
	private class Inner {
		public void say(String s) {
			System.out.println(s);
		}
	}
	Ex11Interface f() {
		return new Inner();
	}
}