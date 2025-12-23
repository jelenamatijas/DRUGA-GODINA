package src;

import java.util.Random;
import java.io.*;

public class Stack implements Serializable {
	int topElementIndex;
	int []elements;
	
	public static volatile Boolean lock = true;
	
	Stack(int cap){
		elements = new int[cap];
	}
	
	private boolean isFull(){ return topElementIndex == elements.length; }
	
	private boolean isEmpty(){ return topElementIndex == 0; }
	
	void push(int toPush) throws StackException{
		if(isFull())
			throw new StackException(this.toString() + " is full");
		else
			elements[topElementIndex++] = toPush;
	}
	
	int pop() throws StackException{
		if(isEmpty())
			throw new StackException(this.toString() + " is full");
		else 
			return elements[--topElementIndex];
	}
	
	
	public static void main(String args[]){
		
		File stackFile = new File(System.getProperty("user.dir"), "stack.ser");
		Stack stack = new Stack(100);
		if(stackFile.exists()){
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(stackFile))){
				stack = (Stack)in.readObject();
			} catch(IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		} 
		
		
		PusherThread push = new PusherThread(50, stack);
		PopperThread pop = new PopperThread(30, stack);
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
			
			System.out.println("Unesite start za pocetak");
			
			while(!"start".equals(in.readLine()));
			long startTime = System.currentTimeMillis();
			push.start();
			pop.start();
			push.join();
			pop.join();
			while(System.currentTimeMillis() - startTime < 60_000);
			
			System.out.println("Runtime " + ((System.currentTimeMillis() - startTime)/1000));
			
		} catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(stackFile))){
			if(stackFile.exists())
				stackFile.delete();
			stackFile.createNewFile();
			
			out.writeObject(stack);
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}

class PusherThread extends Thread{
	int number;
	Stack stack;
	
	
	PusherThread(int number, Stack stack){
		this.number = number;
		this.stack = stack;
	}
	
	public void run(){
		long startTime = System.currentTimeMillis();
		Random rand = new Random();
		while(System.currentTimeMillis() - startTime < 60_000){
			if(number-- > 0){
				try{
					
					synchronized(stack){
						int toPush = rand.nextInt(90) + 10;
						System.out.println("Pushed " + toPush);
						stack.push(toPush);
					}
					Thread.sleep(1000);
					
				} catch(InterruptedException | StackException e){
					e.printStackTrace();
					number = 0;
				}
			}
		}
	}
}

class PopperThread extends Thread{
	int number;
	Stack stack;
	
	
	PopperThread(int number, Stack stack){
		this.number = number;
		this.stack = stack;
	}
	
	public void run(){
		long startTime = System.currentTimeMillis();
		Random rand = new Random();
		while(System.currentTimeMillis() - startTime < 60_000){
			if(number-- > 0){
				try{
					synchronized(stack){
						System.out.println("Popped " + stack.pop());
					}
					Thread.sleep(1000);
					
				} catch(InterruptedException | StackException e){
					e.printStackTrace();
					number = 0;
				}
			}
		}
	}
}

class StackException extends Exception{

	StackException() {}
	
	StackException(String message) { super(message); }

}
