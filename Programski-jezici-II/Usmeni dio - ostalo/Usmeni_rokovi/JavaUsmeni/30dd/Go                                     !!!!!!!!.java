class Go extends Game {
	Go() { super(s2); }
	{
		s += "i ";
	}
	public static void main(String[] args) {
		System.out.println("Ovo se drugo ispise0");
		new Go();
		System.out.println(s);
		
		System.out.println("---------------------------------------");
		new New();
		
	}
	static { s += "sb "; }
	}
class Game {
	static String s = "-";
	static String s2 = "s2";
	static{
		System.out.println("Ovo se prvo ispise");
	}
	Game(String arg) { s += arg; }
}

class New extends New2 {
	{
		System.out.println("New non-static");
	}
	static {
		System.out.println("New static");
	}
	public New() {
		System.out.println("New cons");
	}
}
class New2 {
	{
		System.out.println("New2 non-static");
	}
	static {
		System.out.println("New2 static");
	}
	public New2() {
		System.out.println("New2 cons");
	}
}
