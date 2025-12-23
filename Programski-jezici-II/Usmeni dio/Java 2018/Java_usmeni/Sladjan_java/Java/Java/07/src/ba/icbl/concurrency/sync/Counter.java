package ba.icbl.concurrency.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter { 
	private long c1 = 0; 
//	private Lock lock1 = new ReentrantLock();
//	private Object lock1 = new Object(); 
	public synchronized void increment1() { 
		long tmp = c1;
		c1++;
		System.out.println("Inkrementiram c1 sa " + tmp + " na " + c1);
	} 
//	
//	public synchronized void increment1() { 
//		long tmp = c1;
//		c1++;
//		System.out.println("Inkrementiram c1 sa " + tmp + " na " + c1);
//	} 

//	public void increment1() { 
//		synchronized(lock1) { 
//			long tmp = c1;
//			c1++;
//			System.out.println("Inkrementiram c1 sa " + tmp + " na " + c1);
//		} 
//	} 

//
//	public void increment1() {
//		lock1.lock();
//		try {
//			long tmp = c1;
//			c1++;
//			System.out.println("Inkrementiram c1 sa " + tmp + " na " + c1);
//		} finally {
//			lock1.unlock();
//		}
//	}

} 
