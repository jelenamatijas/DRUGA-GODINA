package src.item;

public class Cable extends Item{
	public Cable(double weight) {
		super(weight);
	}
	
		@Override 
		public String toString(){
			return "Sajla " + super.toString();
		}
}