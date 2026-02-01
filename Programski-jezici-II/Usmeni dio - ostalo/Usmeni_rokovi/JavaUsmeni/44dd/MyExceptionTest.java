public class MyExceptionTest {
	public static void main(String[] args) {
		try {
			second();
		}catch(MyException e) {
			System.out.println("Uhvacen i obradjen izuzetak " + e);
		}
		
		try {
			int x = 3;
		} catch (Exception ex) {}
	}
	public static void second() throws MyException { first(); }
	public static void first() throws MyException {
		throw new MyException();
	}
}
class MyException extends Exception {
	MyException() {
		super();
}
	MyException(String s) {
		super(s);
		System.out.println("MyException super");
	}
}













//PRIMJETITI NACIN ISPISA EXCEPTIONA CIJI KONSTRUKTOR PRAZAN