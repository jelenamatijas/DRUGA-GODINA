package ba.icbl.concurrency.pusherpopper;

public class Stack {
	private int[] elements;
	private int topElementIndex;

	public Stack(int capacity) {
		elements = new int[capacity];
		for (int i = 0; i < elements.length; i++)
			elements[i] = -1;
		topElementIndex = -1;
	}

	public boolean push(int el) { 
		if(isFull())
			return false;
		System.out.println("push: e[" + ++topElementIndex + "] = "+el); 
		elements[topElementIndex] = el;
		System.out.println("push: " + topElementIndex);
		return true;
	}

	public int pop() {
		if(isEmpty()){		
			System.out.println("stack is empty...");
			return 0;
		}
		int el = elements[topElementIndex];	
		System.out.println("pop: e[" + topElementIndex + "] = " + el);
		elements[topElementIndex] = 0;
		topElementIndex--;				
		System.out.println("pop: " + topElementIndex);
		return el;
	}

	private boolean isEmpty() {	
		return topElementIndex < 0;
	}
	private boolean isFull() {
		return topElementIndex >= elements.length - 1;
	}
}
