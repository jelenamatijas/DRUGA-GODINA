package net.etfbl.exception;

public class ExceptionTest4 {

	public static void main(String[] args) {
		try{
			throw new Exception();
		}catch(Exception e){
			throw new NullPointerException();
		}finally{
			System.out.println("finally");
		}
	}

}
