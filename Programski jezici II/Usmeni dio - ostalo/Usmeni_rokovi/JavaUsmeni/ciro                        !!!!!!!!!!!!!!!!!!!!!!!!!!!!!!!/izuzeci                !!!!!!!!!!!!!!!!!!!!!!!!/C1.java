public class C1 extends C2 {
	static int c;
	public static void main(String [] args) {
		try {
			C1 c1 = new C1();
			System.out.println(c);
			switch(c) {
			case 0:
				try{
					System.out.println("Case 0");
					C2 c2= new C2();
					c2.metoda();
				} catch (CE2 ex) {
					System.out.println("CE2");
				} catch (CE1 ex) {
					System.out.println("CE1");
				} catch (RuntimeException ex) {
					System.out.println("RE");
				} catch (Exception ex) {
					System.out.println("E");
				} catch (Throwable ex) {
					System.out.println("T");
				}
			case 1:
				c1.metoda();
				throw new Error();
			default: {
				System.out.println("default");
				throw new CE2();
			}}
		} catch (CE2 ex) {
			System.out.println("Catch blok 1");
			try {
				(new C3()).metoda();
			} catch(Exception exc) {
				System.out.println("fail");
			}
			try{
				try(C2 c22 = new C2()){}
			} catch(Exception baks) { System.out.println("baks"); }
			System.out.println("exCE2");
		} catch (CE1 ex) {
			System.out.println("exCE1");
		} catch (RuntimeException | Error ex) {
			System.out.println("exRE");
		} catch (Throwable ex) {
			System.out.println("exT");
		} finally { try { (new C1()).metoda(); } catch(Exception ex) {} };
	}
	public void metoda() throws Exception {
		System.out.println("C1 metoda");
		if(c>0)	{
			main(new String[5]);
			c = 65535;
		} else throw new CE1();
		throw new CE2();
	}
	C1() {
		c+=++c+c++;
		System.out.println("C1");
	}
}
class C2 extends C3 {
	public void metoda() throws Exception {
		System.out.println("C2metoda");
		throw new CE1("Greska!");
	}
	public void close() throws CE1 {
		System.out.println("C2close");
	}
}
class C3 implements AutoCloseable, I1 {
	public void close() throws Exception {
		System.out.println("C3close");
	}
	public void metoda() throws Exception {
		try {
			throw new CE2();
		} catch(Exception ex) {}
	}
}
//class CE1 extends Throwable {
class CE1 extends Exception {
	CE1() {
		this("CE1");
		System.out.println("CE1");
	}
	CE1(String str) {
		super(str);
		System.out.println("CE1(S)");
	}
}
class CE2 extends CE1 {
	CE2() {
		this("CE2");
		System.out.println("CE2");
	}
	CE2(String str) {
		super(str);
		System.out.println("CE2(S)");
	}
}
interface I1 {
	void metoda() throws Exception;
}