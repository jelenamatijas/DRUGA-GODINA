class Kutija extends Predmet implements OtvoriInterface, ZatvoriInterface{
	String sadrzaj;
	
	Kutija(){
		sadrzaj = "Sadrzaj kutije broj " + id;
	}
	
	@Override
	public String toString(){
		return "Predmet: Kutija -> status ostecenja: " + statusOstecenja + " Tezina: " + tezina;
	}
}