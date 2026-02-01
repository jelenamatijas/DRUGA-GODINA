public class L extends K {
	public static void main(String args[]){
		L l = new L();
		J j = new J();
		System.out.println(J.i);
		j = (K) l;
		System.out.println(j.metoda());
	}
};
class J {
	static int i;
	J() {
		++i;
	}
	private int metoda() {
		return ++i;
	}
};
class K extends J {
	int i = 0;
	K() {
		i++;
	}
	int metoda() {
		return (i+3);
	}
};