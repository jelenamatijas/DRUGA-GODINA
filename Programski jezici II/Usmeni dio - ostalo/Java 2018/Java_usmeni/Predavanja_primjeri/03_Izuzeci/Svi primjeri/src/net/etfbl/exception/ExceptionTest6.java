package net.etfbl.exception;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionTest6 {

	public static void main(String[] args) {
		boolean x = false;
		try{
			if(x)
				throw new IOException();
			throw new SQLException();
		}catch(SQLException | IOException e){
			e.printStackTrace();
		}
	}

}
