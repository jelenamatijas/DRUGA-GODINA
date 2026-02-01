class C1 extends C2 {
	static int c = 0;
	C1() throws CE2, CE3 {
		System.out.println("C1");
		throw new CE1();
	}
	public static void main(String[] args) throws CE2, CE3 {
		System.out.println("main");
		if (c++==0) {
			try {
				C1 c1 = new C1();
			} catch (Exception ex) {
				C2 c2 = new C2();
			}
		} else {
			throw new CE2();
		}
	}
}
class C2 {
	C2() throws CE3 {
		System.out.println("C2");
		this.main();
	}
	public void main() throws CE1, CE3 {
		System.out.println("C2main");
		if (C1.c==0) {
			try {
				C1 c1 = new C1();
			} catch (Exception ex) {
				C2 c2 = new C2();
			}
		} else {
			throw new CE3();
		}
	}
}
class CE1 extends CE3 {
	CE1(String msg) {
		super(msg);
		System.out.println("CE1(S)");
	}
	CE1() {
		this("CE1");
		System.out.println("CE1");
	}
}
class CE2 extends Exception {
	CE2(String msg) {
		super(msg);
		System.out.println("CE2(S)");
	}
	CE2() throws CE1 {
		this("CE2");
		try {
			if (C1.c<0)
				throw new CE3();
			else throw new CE1();
		} finally {
			System.out.println("CE2");
			return;
		}
	}
}
class CE3 extends Exception {
	CE3(String msg) {
		super(msg);
		System.out.println("CE3(S)");
	}
	CE3() {
		this("CE3");
		System.out.println("CE3");
	}
}
