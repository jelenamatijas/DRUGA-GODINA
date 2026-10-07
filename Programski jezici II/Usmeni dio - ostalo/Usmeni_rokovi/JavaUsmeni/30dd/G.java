public class G{
	static int x = 3;
	public static void main(String args[]) { new G(); }
	G(){ new G(2); }
	G(int x){ System.out.println(x); }
}