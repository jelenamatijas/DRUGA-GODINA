public class C1{
C1(){
System.out.println("C1()");
}
public static void main(String args[]){
C1 c1=new C1();
try{
c1.metoda();
System.out.println("main 1");
}catch(CE2 e){
	System.out.println("main 2:"+e);
}catch(CE1 e){
	System.out.println("main 3:"+e);
}catch(Throwable e){
		System.out.println("main 4:"+e);
}
}
void metoda() throws Throwable{
	C2 c2=new C2();
	try{
		c2.metoda();
		System.out.println("C1 : metoda()");
	}finally{
			System.out.println("finally");
		}
	}
}

class C2{
	C2(){
		System.out.println("C2()");
		}
		void metoda() throws CE1{
			C3 c3=new C3();
					System.out.println("C2 : metoda()");
			c3.metoda();
			}
	}
	class C3{
		C3(){
				System.out.println("C3()");
			}
			protected void metoda()throws CE1{
				System.out.println("C3 : metoda()");
				throw new CE2("CCCCEEEE2");
				}
		}
		class CE1 extends Throwable{
			CE1(String s){
				super(s);   //klasa Throwable se moze pozvati sa super() i sa super(s),ako izostavimo ovu liniju na kraju imamo main 2: CE2
				System.out.println("CE1:"+s);
				}
			}
			class CE2 extends CE1{
			CE2(String s){
				super(s);
				System.out.println("ce2:"+s);
				}
			}
