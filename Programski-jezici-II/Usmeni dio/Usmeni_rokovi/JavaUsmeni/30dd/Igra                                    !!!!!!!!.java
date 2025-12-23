@SuppressWarnings("unchecked")

class Igra {
	public static void main(String[] args) {
		Integer i = new Integer(5);
		Integer j = new Integer(5);
		System.out.println(i == j);
		System.out.println( i == (new Integer(5))); 
	} 
}