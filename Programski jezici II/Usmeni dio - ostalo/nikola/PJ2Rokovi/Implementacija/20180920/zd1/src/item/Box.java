package src.item;

public class Box extends Item {
	private boolean open;
	private Object content;
	
	public Box(double weight, Object content) {
		super(weight);
		this.open = false;
		this.content = content;
	}
	
	@Override 
		public String toString(){
			return "Kutija " + super.toString();
		}
	
}