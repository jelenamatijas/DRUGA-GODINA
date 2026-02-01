class PassA {
	public static void main(String [] args) {
		PassA p = new PassA();
		p.start();
	}
	void start() {
		long [] a1 = {3,4,5};
		fix(a1);
		System.out.println(a1[0] + " " + a1[1] + " " + a1[2]);
		String[] a = {"nesto"};
		asa(a);
		System.out.println(a[0]);
	}
	long [] fix(long [] a3) {
	a3[1] = 7;
	return a3;
	}
	String[] asa(String[] a){
		a[0] = "novo";
		return a;
	}
}