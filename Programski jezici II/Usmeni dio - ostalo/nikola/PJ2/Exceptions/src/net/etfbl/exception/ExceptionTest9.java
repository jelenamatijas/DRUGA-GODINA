package net.etfbl.exception;

public class ExceptionTest9 {

	public static void main(String[] args) {
		try {
			try {
				throw new Test1Exception("test 1");
			} 
			finally {
				System.out.println("finally in nested try");
			}
		} 
		catch (Test1Exception e) {
			System.out.println("catch in outer");
			e.printStackTrace(System.out);
		}
		finally {
			System.out.println("finally in outer try");
			try {
				throw new Test2Exception("test 2");
			} 
			catch (Test2Exception e) {
				System.out.println("try catch in finally");
				// e.printStackTrace(System.out);
				StackTraceElement elements[] = e.getStackTrace();
				for (int i = 0, n = elements.length; i < n; i++) {
					System.err.println(elements[i].getClassName() + "-" + elements[i].getFileName() + ":"
							+ elements[i].getLineNumber() + ">> " + elements[i].getMethodName() + "()");
				}
			}
		}
	}

}

class Test1Exception extends Exception {
	public Test1Exception(String s) {
		super(s);
	}
}

class Test2Exception extends Exception {
	public Test2Exception(String s) {
		super(s);
	}
}