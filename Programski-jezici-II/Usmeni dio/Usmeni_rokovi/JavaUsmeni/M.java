public class M {
	int x = 0, y = 0;
	M() {}
	M(int a, int b) {
		x = a;
		y = b;
	}
	protected int zbir() { return x+y; }
	protected int razlika() { return x-y; }
	public static void main(String args[]) {
		M m = new M(1,2);
		N n = new N();
		System.out.println(m.razlika());
		System.out.println(n.razlika());
	}
}
class N extends M {
	N() {}
	N(int i, int j) { super(i,j); }
	public int zbir() { return y+x; }
	public int razlika() { return y-x; }
}