class Ploca extends Predmet implements SavijInterface{
	
	Ploca(){}
	
	@Override
	public String toString(){
		return "Predmet: Ploca -> status ostecenja: " + statusOstecenja + " Tezina: " + tezina;
	}
}