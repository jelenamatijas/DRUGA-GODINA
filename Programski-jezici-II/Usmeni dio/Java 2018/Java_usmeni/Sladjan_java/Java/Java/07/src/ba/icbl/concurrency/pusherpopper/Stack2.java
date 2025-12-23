package ba.icbl.concurrency.pusherpopper;


public class Stack2 {
	private int[] elements;
	private int topElementIndex;

	public Stack2(int capacity) {
		elements = new int[capacity];
		for (int i = 0; i < elements.length; i++)
			elements[i] = -1;
		topElementIndex = -1;
	}

	public synchronized boolean push(int el) {
		if(isFull())
			try{
				System.out.println("Waiting for push...");
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		System.out.println("push: e[" + ++topElementIndex + "] = " + el);
		elements[topElementIndex] = el;
	    notify();
		return true;
	}
	
	public synchronized int pop() {
		if(isEmpty())
			try{
				System.out.println("Waiting for pop...");
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		int el = elements[topElementIndex];
		System.out.println("pop: e[" + topElementIndex + "] = " + el);
		elements[topElementIndex] = 0;
		topElementIndex--;
	    notify();
		return el;
	}

	private synchronized boolean isEmpty() {
		return topElementIndex < 0;
	}

	private synchronized boolean isFull() {
		return topElementIndex >= elements.length - 1;
	}
}
