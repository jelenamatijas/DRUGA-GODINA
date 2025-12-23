class One {
	void go1() { System.out.print("1 "); }
	final void go2() { System.out.print("2 "); }
	private void go3() { System.out.print("3 "); }
}
public class OneB extends One {
	void go1() { System.out.print("1b "); }
	void go3() { System.out.print("3b "); }
	public static void main(String[] args) {
		new OneB().go1();
		new One().go1();
		new OneB().go2();
		new OneB().go3();
		new One().go3();
	}
}

















//MOZE SE U INNER KLASAMA OVAKO PRISTUPATI PREKO OBJEKTA, ALI
//NE KOD NASLEDJIVANJA!!