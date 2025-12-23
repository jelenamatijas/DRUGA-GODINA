package ba.icbl.concurrency.pusherpopper;

public class PusherPopper2 extends Thread {
	private Stack2 stack;
	private int number;
	private String type;

	public PusherPopper2(Stack2 stack, int number, String type) {
		this.type = type;
		this.stack = stack;
		this.number = number;
		setName(type);
	}

	public void run() {
		if(type.equals("PUSH"))
			for (int i = 0; i < number; i++){	
				stack.push(i);
			}
		else
			for (int i = 0; i < number; i++)	
				stack.pop();
	}
	
	public static void main(String args[]){
		Stack2 stack = new Stack2(20);			
		PusherPopper2 pusher = new PusherPopper2(stack, 30, "PUSH"); 						
		PusherPopper2 popper = new PusherPopper2(stack, 30, "POP");  					
		pusher.start();					
		popper.start();				
	}
}
