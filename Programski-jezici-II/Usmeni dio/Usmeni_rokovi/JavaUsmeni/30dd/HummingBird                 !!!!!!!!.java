class HummingBird extends Bird {
	public void fly() { s += "hover "; }
	public static void main(String[] args) {
		Bird b1 = new Bird();
		Bird b2 = new HummingBird();
		Bird b3 = (HummingBird)b2;
		HummingBird b4 = (HummingBird)b2;
		b1.fly(); b2.fly(); b3.fly(); b4.fly();
		System.out.println(s);
	}
}
class Bird {
	public static String s = "";
	public void fly() { s += "fly "; }
}