package src.item;

public class Sheet extends Item implements IBendable {
	public Sheet(double weight) {
		super(weight);
	}
	
	@Override 
		public String toString(){
			return "Ploca " + super.toString();
		}
}