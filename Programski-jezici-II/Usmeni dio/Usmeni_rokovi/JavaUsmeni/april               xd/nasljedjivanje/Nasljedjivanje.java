public class Nasljedjivanje{
	public static void main(String[] args){
		A a = new A();
		B b = new B();
		//B b = new B();
		System.out.println(a.a);
		a = null;
		a = null;
	}
}
class A extends B{
	public int a;
}
class B{
	public B(){
		if(this instanceof A){
			this.a = 15;
			System.out.println("asa");
		}
	}
	public int a = 2;
}













//VALJDA OVDJE ZATO STO HIDEUJE NE MIJENJA SE THIS
//TJ NI PROSLI PUT SE KOD METODA NIJE MJENJAO THIS ALI SE ONDA RADILO ONO SA VTABELAMA