class A1 extends A2{
	
	public static void main(String args[]){
		A1 a1=new A1();
		A2 a2=new A2();
		a1.metoda();
		}
		public void metoda(){
			super.metoda();
			}
	}
class A2 extends A3{

public A2(){
System.out.println("A2()");
}
public void metoda(){
this.metoda();         //stalno zove sama sebe i pukne kod
super.metoda();
System.out.println(a++);
}

}
class A3{
	double a;
	int b;
	float c;
	public A3(){
		System.out.println("A3()");
	a=c=b=1;
		}
		public void metoda(){
			System.out.println(a + b++);
			}
	}
	//A1.metoda() iz klase A1 i A2.metoda iz klase A1 ... stalno se pozivaju
	
	// ako ovo zanemarimo //this.metoda(); 
	//ispis je A3() A2() A3() A2() 2.0 1.0
	
		// ako ovo zanemarimo //super.metoda(); 
	//prodje kompajliranje,ali isto kao prvi slucaj