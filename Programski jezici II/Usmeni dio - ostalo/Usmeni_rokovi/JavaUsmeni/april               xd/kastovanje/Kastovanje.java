public class Kastovanje{
	public static void main(String... args){
		I1[] b = new I1[1];
		b[0] = new B();
		A pom = (A)b[0];
	}
}
class A extends B{
	public void metoda(){}
	public void metoda3(){}
}
class B implements I1{
	public void metoda(){}
	public void metoda2(){}
}
interface I1{
	public void metoda();
}