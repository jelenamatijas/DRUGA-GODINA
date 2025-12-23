package net.etfbl.exception;

public class ExceptionTest7 {

	public static void main(String[] args) {
		System.out.println(method());
	}

	public static int method(){
		try{
			return 1;
		}finally{
			return 2;
		}
		
	}
}
