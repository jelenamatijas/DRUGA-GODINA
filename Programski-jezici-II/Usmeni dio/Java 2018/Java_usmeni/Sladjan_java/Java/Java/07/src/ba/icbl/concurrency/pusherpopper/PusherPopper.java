package ba.icbl.concurrency.pusherpopper;


public class PusherPopper extends Thread {
	private Stack stack;
	private int number;
	private String type;

	public PusherPopper(Stack stack, int number, String type) {
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
		Stack stack = new Stack(20);			
		PusherPopper pusher = new PusherPopper(stack, 30, "PUSH"); 						
		PusherPopper popper = new PusherPopper(stack, 30, "POP");  					
		pusher.start();					
		popper.start();				
	}
}
