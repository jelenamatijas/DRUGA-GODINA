package net.etfbl.exception2;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
	public static void main(String[] args) {
		try {
//			Handler handler = new FileHandler("OutFile.log");
//			Logger.getLogger("").addHandler(handler);
			throw new Exception();
		} catch (Exception e) {
			Logger logger = Logger.getLogger("package.name");
			logger.log(Level.WARNING, "err");
//			StackTraceElement elements[] = e.getStackTrace();
//			for (int i = 0, n = elements.length; i < n; i++) {
//				logger.log(Level.WARNING, elements[i].getMethodName());
//			}
		}
	}
}
