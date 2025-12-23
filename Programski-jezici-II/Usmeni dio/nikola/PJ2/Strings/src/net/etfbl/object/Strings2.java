package net.etfbl.object;

public class Strings2 {
	public static void main(String[] args) {
		String x = new String("abšШ");
		change(x);
		System.out.println(x);
	}
	 
	public static void change(String a) {
		a = "cd";
	}
}