public class Exercise7 {
	private int x;
	private void metoda() {
		System.out.println("Exercise7.metoda()");
	}
	private void metoda2() {
		Exercise7Inner e7i = new Exercise7Inner();
		e7i.metodaModify();
		System.out.println(x);
		System.out.println(e7i.y);
	}
	private class Exercise7Inner {
		private int y;
		private void metodaModify() { x = 5; }
	}
	public static void main(String[] args) {
		Exercise7 e7 = new Exercise7();
		e7.metoda2();
	}
}