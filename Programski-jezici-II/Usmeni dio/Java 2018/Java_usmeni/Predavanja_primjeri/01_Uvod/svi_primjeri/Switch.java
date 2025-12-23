
public class Switch {

	public static void main(String[] args) {
		Switch swtch = new Switch();
		char c = 'a';
		swtch.charSwitch(c);
		String s = new String("c");
		swtch.stringSwitch(s);
		swtch.intSwitch(2);

	}
	
	private void charSwitch(char c){
		switch(c) {
		  case 'a':
		    System.out.println("a");
		    break;
		  case 'b':
		    System.out.println("b");
		    break;
		  case 'c':
		    System.out.println("c");
		    break;
		  default:
		    System.out.println("default");
		}
	}
	
	private void stringSwitch(String s){
		switch(s) {
		  case "a":
		    System.out.println("a");
		    break;
		  case "b":
		    System.out.println("b");
		    break;
		  case "c":
		    System.out.println("c");
		    break;
		  default:
		    System.out.println("default");
		}
	}
	
	private void intSwitch(int i){
		switch(i) {
		  case 1:
		    System.out.println("1");
		    break;
		  case 2:
		    System.out.println("2");
		    break;
		  case 3:
		    System.out.println("3");
		    break;
		  default:
		    System.out.println("default");
		}
	}
	
}
