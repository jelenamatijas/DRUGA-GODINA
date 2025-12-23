public class B1 extends B2 implements BI1, BI2, BI3 {
	B1() {
		System.out.println("B1");
	}
	B1(String s) {
		this();
		System.out.println("B1("+s+")");
	}
	public void metoda() {
		System.out.println((new B2()).metoda("B1 metoda"));
	}
	public static void main(String args[]) {
		BI1[][] b[]= new BI1[SYSTEM_CONST][][];
		for(int i = 0; i < b.length; i++) {
			b[i] = new BI1[b.length / 2][];
			for (int j = 0; j <= b.length /2; j++) {
				BI1[] br = b[i][j] = new BI1[b.length * SYSTEM_CONST/2];
				for (int k = 1; k >= 0 && k < br.length; k--) {
					br[k] = (BI3) (new B1());
					k+=2;
					if (k > 0 && k < br.length) {
						br[k-1]= new B2();
						((B1) br[k-1]).metoda();
						// System.out.println(new B1(k));
						System.out.println(new B1(Integer.toString(k)).metoda("B1"));
					}
					else k=-2;
					br[k=-2].metoda();
				}
			}
		}
	}
	static protected String metoda(String x) {
		System.out.println("B1 metodaS");
		return x.replace ("B1", "B");
	}
}
class B2 implements BI2 {
	private static int c;
	public void metoda() {
		System.out.println("B2metoda");
	}
	static protected String metoda(String x) {
		c++;
		System.out.println("B2 metodaS");
		x.replace("B1","B2");
		return x;
	}
	public void metoda2(String str) {
		System.out.println("B2metoda2S" + str);
	}
}
interface BI1 {
	static int SYSTEM_CONST = 5;
	public void metoda();
}
interface BI2 extends BI1 {
	default void metoda() {
		System.out.println("BI2 metoda");
	};
}
interface BI3 extends BI1, BI2 {
	default void metoda2(String str) {
		System.out.println("BI3metoda2" + str);
	}
	void metoda();
}