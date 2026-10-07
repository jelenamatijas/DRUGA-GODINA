public class D1 extends DI1.D2 implements DI1,DI2{
	public D1(){
		super();
		System.out.println("D1()");
	}
	public static void main(String args[]){
		DI2 di1 = new D1();
		DI2 di2 = new D2();
		DI1 di3 = new D1();
		D1 d1 = new D1();
		D2 d2 = new DI1.D2();
		System.out.println(((DI2)new D1()).metoda());
		System.out.println(((DI2)d2).metoda());
	}
	//public int metoda(){
	public void metoda(int i){
		System.out.println("D1 metoda");
		//return 1;
	}
}
interface DI1{
	class D2 implements DI2{
		D2(){ System.out.println("D2()"); }
		public int metoda(){
			System.out.println("D2 metoda");
			return 0;
		}
	}
}
interface DI2{ int metoda(); }