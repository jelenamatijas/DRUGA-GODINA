import java.io.*;

public class Izuzeci{
	public static void main(String[] args) throws Exception{
		try{
			A a = new A();
			
		}catch(E1 w){
			System.out.println("E1 uhvacen");
		}catch(FileNotFoundException f){
			System.out.println("file not found");
		}catch(NullPointerException np){
			System.out.println("file not found");
		}
		catch(Exception e){
			System.out.println("Izuzetak uhvacen");
			e.printStackTrace();
		}
		//A s = new A();
	}
}
class A{
	A() throws E1,FileNotFoundException{
		String[] str  = new String[3];
		Object[] obj = new Object[3];
		try{
			//FileReader file = new FileReader(new File("sada"));
			//double d = Double.parseDouble("ad");
			//String pom = str[4];
			System.out.println(obj[2]);
		//throw new E2();
		throw new E1();
		}catch(E1 s){}
		throw new E1();
	}
}
class E1 extends Exception{
	E1() {
		//throw new E2();
		//System.out.println("E1");
	}
}
class E2 extends Exception{
	E2() throws Exception{
		
		System.out.println("E2");
	}
}