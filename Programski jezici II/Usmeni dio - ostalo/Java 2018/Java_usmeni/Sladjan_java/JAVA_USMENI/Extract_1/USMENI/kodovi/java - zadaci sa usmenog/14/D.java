public class D implements I3{
	public void metoda3(){
		System.out.println(3);
	}
	public void metoda(){
		System.out.println(1);
	}
	public void metoda2(){
		System.out.println(2);
	}
	public static void main(String args[]){
		D d=new D();
		E e=new E();
		d.metoda();
		e.metoda();
	}
}